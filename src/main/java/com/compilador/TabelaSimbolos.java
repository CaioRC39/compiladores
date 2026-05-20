package com.compilador;

import java.util.*;

public class TabelaSimbolos {
    // Pilha de escopos: cada escopo é um mapa de nome -> Símbolo
    private Deque<Map<String, Simbolo>> pilhaEscopos;

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

    // Adicionar um símbolo no escopo atual (verifica duplicação no mesmo escopo)
    public void adicionar(Simbolo simbolo) {
        Map<String, Simbolo> atual = escopoAtual();
        if (atual.containsKey(simbolo.getNome())) {
            // Erro: variável já declarada no mesmo escopo
            throw new RuntimeException("Erro semântico: '" + simbolo.getNome() + "' já declarado neste escopo.");
        }
        atual.put(simbolo.getNome(), simbolo);
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