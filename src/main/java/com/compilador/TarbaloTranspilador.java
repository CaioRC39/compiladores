package com.compilador;

import com.compilador.tarbalo.*;
import java.util.*;

public class TarbaloTranspilador extends TarbaloBaseVisitor<String> {

    // Tabela de símbolos (já existente)
    private TabelaSimbolos tabela = new TabelaSimbolos();
    private boolean erros = false;

    public boolean houveErros() { return erros; }

    private void erro(int linha, String msg) {
        System.err.println("Erro semântico (linha " + linha + "): " + msg);
        erros = true;
    }

    // Mapeamento de tipos Tarbalo → Java
    private String mapearTipo(String tipoTarbalo) {
        return switch (tipoTarbalo) {
            case "int"    -> "int";
            case "dec"    -> "double";
            case "texto"  -> "String";
            case "car"    -> "char";
            case "logico" -> "boolean";
            default       -> "Object";
        };
    }

    // ======================================================================
    // 4. visitBloco
    // ======================================================================
    @Override
    public String visitBloco(TarbaloParser.BlocoContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (TarbaloParser.ComandoContext cmdCtx : ctx.comando()) {
            String linha = visit(cmdCtx);
            if (linha != null && !linha.isBlank()) {
                sb.append("        ").append(linha).append("\n");
            }
        }
        return sb.toString();
    }

    // ======================================================================
    // 5. visitDeclaracao (despacha para o tipo correto de declaração)
    // ======================================================================
    @Override
    public String visitDeclaracao(TarbaloParser.DeclaracaoContext ctx) {
        if (ctx.declaracaoVariavel() != null) {
            return visit(ctx.declaracaoVariavel());
        } else if (ctx.declaracaoVetor() != null) {
            return visit(ctx.declaracaoVetor());
        } else if (ctx.declaracaoFuncao() != null) {
            return visit(ctx.declaracaoFuncao());
        }
        return ""; // nunca acontece
    }
}