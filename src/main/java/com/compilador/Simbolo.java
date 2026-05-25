package com.compilador;

import java.util.List;

public class Simbolo {
    private String nome;
    private String tipo;          // "int", "qbd", "txt", "ltr", "lgc", "vazio", "func"
    private String tipoBase;
    private boolean nativa;
    private Categoria categoria;  // enum
    private int dimensoes;        // 0 = variável simples, 1+ = vetor (número de colchetes)
    private boolean dinamico;     // true se vetor dinâmico ([] sem tamanho)
    private List<Integer> tamanhos; // tamanhos das dimensões fixas, se houver
    private List<String> tiposParametros; // para funções
    private String tipoRetorno;   // para funções

    public enum Categoria {
        VARIAVEL, VETOR, FUNCAO
    }

    // Construtor para variável simples
    public Simbolo(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
        this.tipoBase = tipo;   // ← para escalares, tipoBase == tipo
        this.categoria = Categoria.VARIAVEL;
        this.dimensoes = 0;
    }

    // Construtor para vetor – recebe o tipo base e as dimensões
    public Simbolo(String nome, String tipoBase, int dimensoes, boolean dinamico, List<Integer> tamanhos) {
        this.nome = nome;
        this.tipoBase = tipoBase;
        this.tipo = tipoBase + "[]".repeat(dimensoes);   // ex.: "int[][]"
        this.categoria = Categoria.VETOR;
        this.dimensoes = dimensoes;
        this.dinamico = dinamico;
        this.tamanhos = tamanhos;
    }

    // Construtor para função
    public Simbolo(String nome, String tipoRetorno, List<String> tiposParametros, boolean nativa) {
        this.nome = nome;
        this.tipo = "func";
        this.categoria = Categoria.FUNCAO;
        this.tipoRetorno = tipoRetorno;
        this.tiposParametros = tiposParametros;
        this.nativa = nativa;
    }


    // Getters e setters (apenas os necessários)
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public String getTipoBase() { return tipoBase; }
    public boolean isNativa() { return nativa; }
    public Categoria getCategoria() { return categoria; }
    public int getDimensoes() { return dimensoes; }
    public boolean isDinamico() { return dinamico; }
    public List<Integer> getTamanhos() { return tamanhos; }
    public List<String> getTiposParametros() { return tiposParametros; }
    public String getTipoRetorno() { return tipoRetorno; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Simbolo outro = (Simbolo) o;
        if (categoria != outro.categoria) return false;
        if (!nome.equals(outro.nome)) return false;
        // Para funções, comparar nome + tipos de parâmetros (sobrecarga)
        if (categoria == Categoria.FUNCAO) {
            return tiposParametros != null ? tiposParametros.equals(outro.tiposParametros) : outro.tiposParametros == null;
        }
        // Para variáveis/vetores, comparar nome (já garantiu que é o mesmo símbolo no escopo)
        return true;
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}