package com.compilador;

import com.compilador.tarbalo.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Ler código fonte Tarbalo
            CharStream input = CharStreams.fromFileName("teste.tarbalo");

            // 2. Lexer e parser
            TarbaloLexer lexer = new TarbaloLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            TarbaloParser parser = new TarbaloParser(tokens);

            // 3. Árvore sintática
            ParseTree tree = parser.programa();

            // 4. Análise Semântica (Passagem 1)
            ParseTreeWalker walker = new ParseTreeWalker();
            AnalisadorSemantico analisador = new AnalisadorSemantico();
            walker.walk(analisador, tree); // Preenche a tabela de símbolos

            // 5. Transpilador (Passagem 2)
            // Passamos a tabela preenchida para o transpilador
            TarbaloTranspilador transpilador = new TarbaloTranspilador(analisador.getTabela());
            String codigoJava = transpilador.visit(tree);

            // 6. Escrever o código Java gerado num ficheiro
            if (codigoJava != null) {
                try (PrintWriter pw = new PrintWriter("ProgramaSaida.java")) {
                    pw.print(codigoJava);
                }
                System.out.println("Transpilação concluída! Código gerado em ProgramaSaida.java");
            }

            // 7. Se houve erros semânticos na transpilação
            if (transpilador.houveErros()) {
                System.err.println("Atenção: foram encontrados erros durante a geração do código.");
            }

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}