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

            // 4. Transpilador (Visitor)
            TarbaloTranspilador transpilador = new TarbaloTranspilador();
            String codigoJava = transpilador.visit(tree);

            // 5. Escrever o código Java gerado num ficheiro
            try (PrintWriter pw = new PrintWriter("ProgramaSaida.java")) {
                pw.print(codigoJava);
            }
            System.out.println("Transpilação concluída! Código gerado em ProgramaSaida.java");

            // 6. Se houve erros semânticos, são exibidos durante a visita
            if (transpilador.houveErros()) {
                System.err.println("Atenção: foram encontrados erros semânticos.");
            }

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}