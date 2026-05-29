package com.compilador;

import java.util.*;

public class TabelaSimbolos {
    // Pilha de escopos: cada escopo é um mapa de nome -> Símbolo
    private Deque<Map<String, Simbolo>> pilhaEscopos;

    // Mapa global para funções sobrecarregadas (chave = nome, valor = lista de funções)
    private Map<String, List<Simbolo>> funcoes = new HashMap<>();

    // Memória de todos os símbolos já registrados (para consulta pelo transpilador após fechamento de escopos)
    private Map<String, Simbolo> memoria = new LinkedHashMap<>();

    public TabelaSimbolos() {
        pilhaEscopos = new ArrayDeque<>();
        // Escopo global
        abrirEscopo();
    }

    // Gerir escopos
    public void abrirEscopo() {
        pilhaEscopos.push(new HashMap<>());
    }

    public void fecharEscopo() {
        if (pilhaEscopos.size() > 1) {  // não permite fechar o global
            pilhaEscopos.pop();
        }
    }

    // Obter o escopo atual
    private Map<String, Simbolo> escopoAtual() {
        return pilhaEscopos.peek();
    }

    public String escopoAtualNome() {
        return "escopo[" + pilhaEscopos.size() + "]=" + escopoAtual().keySet();
    }

    // Método adicionar modificado para suportar sobrecarga de funções
    public boolean adicionar(Simbolo simbolo) {
        Map<String, Simbolo> atual = escopoAtual();
        // Sempre registrar na memória (para consulta do transpilador)
        // Para campos de struct, usar chave composta para evitar sobrescrita
        String memoriaKey = simbolo.getNome();
        if (simbolo.getCategoria() == Simbolo.Categoria.REGS_CAMPO && simbolo.getNomeRegs() != null) {
            memoriaKey = simbolo.getNomeRegs() + "." + simbolo.getNome();
        }
        memoria.put(memoriaKey, simbolo);
        if (atual.containsKey(simbolo.getNome())) {
            Simbolo existente = atual.get(simbolo.getNome());
            // Se ambos forem funções, permite sobrecarga
            if (existente.getCategoria() == Simbolo.Categoria.FUNCAO &&
                simbolo.getCategoria() == Simbolo.Categoria.FUNCAO) {
                // Adiciona à lista de sobrecarga (a lista contém também o existente)
                funcoes.computeIfAbsent(simbolo.getNome(), k -> new ArrayList<>());
                // Se o existente ainda não foi adicionado, adiciona agora
                if (funcoes.get(simbolo.getNome()).stream().noneMatch(f -> f.equals(existente))) {
                    funcoes.get(simbolo.getNome()).add(existente);
                }
                funcoes.get(simbolo.getNome()).add(simbolo);
                return true; // sobrecarga aceita
            }
            return false; // duplicata de variável/função
        } else {
            atual.put(simbolo.getNome(), simbolo);
            if (simbolo.getCategoria() == Simbolo.Categoria.FUNCAO) {
                funcoes.computeIfAbsent(simbolo.getNome(), k -> new ArrayList<>()).add(simbolo);
            }
            return true;
        }
    }

    // Busca funções sobrecarregadas
    public List<Simbolo> buscarFuncoes(String nome) {
        return funcoes.getOrDefault(nome, Collections.emptyList());
    }

    // Resolução de sobrecarga: retorna o símbolo compatível ou null
    public Simbolo resolverSobrecarga(String nome, List<String> tiposArgs) {
        List<Simbolo> candidatos = buscarFuncoes(nome);
        Simbolo escolhido = null;
        for (Simbolo cand : candidatos) {
            if (compativel(cand.getTiposParametros(), tiposArgs)) {
                if (escolhido != null) return null; // ambiguidade
                escolhido = cand;
            }
        }
        return escolhido;
    }

    private boolean compativel(List<String> paramTipos, List<String> argTipos) {
        if (paramTipos.size() != argTipos.size()) return false;
        for (int i = 0; i < paramTipos.size(); i++) {
            if (!tipoCompatible(paramTipos.get(i), argTipos.get(i))) return false;
        }
        return true;
    }

    private boolean tipoCompatible(String tipoParam, String tipoArg) {
        if (tipoParam.equals(tipoArg)) return true;
        // int pode ser passado para qbd (decimal)
        if (tipoParam.equals("qbd") && tipoArg.equals("int")) return true;
        // ltr pode ser passado para int (char é numericamente compatível)
        if (tipoParam.equals("int") && tipoArg.equals("ltr")) return true;
        return false;
    }

    // Buscar símbolo em todos os escopos ativos (SEM fallback de memória — para análise semântica)
    public Simbolo buscar(String nome) {
        for (Map<String, Simbolo> escopo : pilhaEscopos) {
            Simbolo s = escopo.get(nome);
            if (s != null) return s;
        }
        return null;
    }

    // Buscar símbolo com fallback na memória (para transpilador — funciona após fechamento de escopos)
    public Simbolo buscarComMemoria(String nome) {
        Simbolo s = buscar(nome);
        if (s != null) return s;
        return memoria.get(nome);
    }

    // Buscar campo de struct por nome do struct e nome do campo
    public Simbolo buscarCampo(String nomeStruct, String nomeCampo) {
        // Buscar em todos os escopos ativos
        for (Map<String, Simbolo> escopo : pilhaEscopos) {
            Simbolo s = escopo.get(nomeCampo);
            if (s != null && s.getCategoria() == Simbolo.Categoria.REGS_CAMPO
                && s.getNomeRegs().equals(nomeStruct)) {
                return s;
            }
        }
        // Fallback na memória (chave composta: nomeStruct.nomeCampo)
        String memoriaKey = nomeStruct + "." + nomeCampo;
        Simbolo s = memoria.get(memoriaKey);
        if (s != null && s.getCategoria() == Simbolo.Categoria.REGS_CAMPO
            && s.getNomeRegs().equals(nomeStruct)) {
            return s;
        }
        return null;
    }

    // Retornar todos os símbolos (escopos ativos + memória, sem duplicatas)
    public List<Simbolo> buscarTodos() {
        Map<String, Simbolo> todos = new LinkedHashMap<>();
        for (Map<String, Simbolo> escopo : pilhaEscopos) {
            todos.putAll(escopo);
        }
        for (Map.Entry<String, Simbolo> entry : memoria.entrySet()) {
            todos.putIfAbsent(entry.getKey(), entry.getValue());
        }
        return new ArrayList<>(todos.values());
    }

    // Verificar se existe em algum escopo ativo (SEM fallback — para análise semântica)
    public boolean existe(String nome) {
        return buscar(nome) != null;
    }

    // Verificar se existe no escopo atual (para duplicações no mesmo escopo)
    public boolean existeNoEscopoAtual(String nome) {
        return escopoAtual().containsKey(nome);
    }

    // Obter tipo de um símbolo nos escopos ativos (SEM fallback — para análise semântica)
    public String obterTipo(String nome) {
        Simbolo s = buscar(nome);
        return s != null ? s.getTipo() : null;
    }

    // Obter tipo com fallback na memória (para transpilador)
    public String obterTipoComMemoria(String nome) {
        Simbolo s = buscarComMemoria(nome);
        return s != null ? s.getTipo() : null;
    }
}