package com.compilador;

import com.compilador.tarbalo.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 1. Parse do programa principal
        TarbaloParser.ProgramaContext userTree = parseFile("tst.tarbalo");

        // 2. Extrair e processar diretivas de inclusão (parse UMA vez por arquivo)
        List<TarbaloParser.DiretivaContext> diretivas = userTree.diretiva();
        List<TarbaloParser.BlocoContext> todosBlocos = new ArrayList<>();
        List<TarbaloParser.ProgramaContext> libTrees = new ArrayList<>();

        for (TarbaloParser.DiretivaContext dir : diretivas) {
            String caminho = dir.STRING().getText();
            caminho = caminho.substring(1, caminho.length() - 1);
            TarbaloParser.ProgramaContext libTree = parseFile(caminho);
            if (!libTree.diretiva().isEmpty()) {
                throw new RuntimeException("Diretivas 'usar' não são permitidas em arquivos incluídos.");
            }
            libTrees.add(libTree);
            todosBlocos.addAll(libTree.bloco());
        }
        todosBlocos.addAll(userTree.bloco());

        // 3. Tabela de símbolos e built‑ins
        TabelaSimbolos tabela = new TabelaSimbolos();
        adicionarBuiltins(tabela);

        // 4. Análise semântica — reusa as mesmas árvores já parseadas
        AnalisadorSemantico analisador = new AnalisadorSemantico(tabela);
        ParseTreeWalker walker = new ParseTreeWalker();
        for (TarbaloParser.ProgramaContext libTree : libTrees) {
            walker.walk(analisador, libTree);
        }
        walker.walk(analisador, userTree);

        if (analisador.houveErros()) {
            System.err.println("Erros semânticos encontrados. Transpilação abortada.");
            return;
        }

        // 6. Transpilação
        TarbaloTranspilador transpilador = new TarbaloTranspilador(tabela, analisador);
        // Agora o transpilador precisa visitar a lista combinada de blocos.
        // Transpila todos os blocos (bibliotecas + programa principal).
        String codigoJava = transpilador.transpilar(todosBlocos);

        if (codigoJava != null) {
            Files.writeString(Path.of("ProgramaSaida.java"), codigoJava);
            System.out.println("Transpilação concluída! Código gerado em ProgramaSaida.java");
        }

        if (transpilador.houveErros()) {
            System.err.println("Atenção: foram encontrados erros durante a geração do código.");
        }
    }

    private static TarbaloParser.ProgramaContext parseFile(String path) throws IOException {
        String source = Files.readString(Path.of(path));
        return parse(source);
    }

    private static TarbaloParser.ProgramaContext parse(String source) {
        CharStream input = CharStreams.fromString(source);
        TarbaloLexer lexer = new TarbaloLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TarbaloParser parser = new TarbaloParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                    int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new RuntimeException("Erro de sintaxe na linha " + line + ":" + charPositionInLine + " - " + msg);
            }
        });
        return parser.programa();
    }

    private static void adicionarBuiltins(TabelaSimbolos tabela) {
        tabela.adicionar(new Simbolo("tamanho", "int", List.of("int[]"), false));
        tabela.adicionar(new Simbolo("tamanho", "int", List.of("qbd[]"), false));
        tabela.adicionar(new Simbolo("tamanho", "int", List.of("txt[]"), false));
        tabela.adicionar(new Simbolo("tamanho", "int", List.of("ltr[]"), false));
        tabela.adicionar(new Simbolo("tamanho", "int", List.of("lgc[]"), false));
    }
}