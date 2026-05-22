package com.compilador;

import java.util.List;

public class Simbolo {
    private String nome;
    private String tipo;          // "int", "dec", "texto", "car", "logico", "vazio", "func"
    private Categoria categoria;  // enum
    private int dimensoes;        // 0 = variável simples, 1+ = vetor (número de colchetes)
    private boolean dinamico;     // true se vetor dinâmico ([] sem tamanho)
    private List<Integer> tamanhos; // tamanhos das dimensões fixas, se houver
    private List<String> tiposParametros; // para funções
    private String tipoRetorno;   // para funções

    public enum Categoria {
        VARIAVEL, VETOR, FUNCAO, PARAMETRO
    }

    // Construtor para variável simples
    public Simbolo(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = Categoria.VARIAVEL;
        this.dimensoes = 0;
    }

    // Construtor para vetor (informando dimensão e se é dinâmico)
    public Simbolo(String nome, String tipo, int dimensoes, boolean dinamico, List<Integer> tamanhos) {
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = Categoria.VETOR;
        this.dimensoes = dimensoes;
        this.dinamico = dinamico;
        this.tamanhos = tamanhos;
    }

    // Construtor para função
    public Simbolo(String nome, String tipoRetorno, List<String> tiposParametros) {
        this.nome = nome;
        this.tipo = "func";
        this.categoria = Categoria.FUNCAO;
        this.tipoRetorno = tipoRetorno;
        this.tiposParametros = tiposParametros;
    }

    // Getters e setters (apenas os necessários)
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public Categoria getCategoria() { return categoria; }
    public int getDimensoes() { return dimensoes; }
    public boolean isDinamico() { return dinamico; }
    public List<Integer> getTamanhos() { return tamanhos; }
    public List<String> getTiposParametros() { return tiposParametros; }
    public String getTipoRetorno() { return tipoRetorno; }
}