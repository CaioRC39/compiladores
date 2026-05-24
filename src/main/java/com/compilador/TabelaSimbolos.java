package com.compilador;

import java.util.*;

public class TabelaSimbolos {
    // Pilha de escopos: cada escopo é um mapa de nome -> Símbolo
    private Deque<Map<String, Simbolo>> pilhaEscopos;

    // Mapa global para funções sobrecarregadas (chave = nome, valor = lista de funções)
    private Map<String, List<Simbolo>> funcoes = new HashMap<>();

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

    // Método adicionar modificado para suportar sobrecarga de funções
    public boolean adicionar(Simbolo simbolo) {
        Map<String, Simbolo> atual = escopoAtual();
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
        // int pode ser passado para dec
        if (tipoParam.equals("dec") && tipoArg.equals("int")) return true;
        return false;
    }

    // Buscar símbolo em todos os escopos, do mais interno ao global
    public Simbolo buscar(String nome) {
        for (Map<String, Simbolo> escopo : pilhaEscopos) {
            Simbolo s = escopo.get(nome);
            if (s != null) return s;
        }
        return null; // não encontrado
    }

    // Verificar se existe em algum escopo
    public boolean existe(String nome) {
        return buscar(nome) != null;
    }

    // Verificar se existe no escopo atual (para duplicações no mesmo escopo)
    public boolean existeNoEscopoAtual(String nome) {
        return escopoAtual().containsKey(nome);
    }

    // Obter tipo de um símbolo (se existir)
    public String obterTipo(String nome) {
        Simbolo s = buscar(nome);
        return s != null ? s.getTipo() : null;
    }
}