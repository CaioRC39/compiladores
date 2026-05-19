package com.compilador;
import java.util.HashMap;
import java.util.Map;

public class TabelaSimbolos {
    private Map<String, Simbolo> tabela;

    public TabelaSimbolos() {
        this.tabela = new HashMap<>();
    }

    public void adicionar(String nome, String tipo) {
        tabela.put(nome, new Simbolo(nome, tipo));
    }

    public boolean existe(String nome) {
        return tabela.containsKey(nome);
    }

    public String obterTipo(String nome) {
        Simbolo s = tabela.get(nome);
        return s != null ? s.getTipo() : null;
    }
}