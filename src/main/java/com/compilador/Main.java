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
        // tamanho
        tabela.adicionar(new Simbolo("tamanho", "int", List.of("int[]"), false));
        tabela.adicionar(new Simbolo("tamanho", "int", List.of("qbd[]"), false));
        tabela.adicionar(new Simbolo("tamanho", "int", List.of("txt[]"), false));
        tabela.adicionar(new Simbolo("tamanho", "int", List.of("ltr[]"), false));
        tabela.adicionar(new Simbolo("tamanho", "int", List.of("lgc[]"), false));
        tabela.adicionar(new Simbolo("tamanho", "int", List.of("int[][]"), false));
        tabela.adicionar(new Simbolo("tamanho", "int", List.of("qbd[][]"), false));
        tabela.adicionar(new Simbolo("tamanho", "int", List.of("txt[][]"), false));
        tabela.adicionar(new Simbolo("tamanho", "int", List.of("ltr[][]"), false));
        tabela.adicionar(new Simbolo("tamanho", "int", List.of("lgc[][]"), false));

        // ordenar
        tabela.adicionar(new Simbolo("ordenar", "vazio", List.of("int[]"), false));
        tabela.adicionar(new Simbolo("ordenar", "vazio", List.of("qbd[]"), false));
        tabela.adicionar(new Simbolo("ordenar", "vazio", List.of("txt[]"), false));
        tabela.adicionar(new Simbolo("ordenar", "vazio", List.of("ltr[]"), false));

        // inverter
        tabela.adicionar(new Simbolo("inverter", "vazio", List.of("int[]"), false));
        tabela.adicionar(new Simbolo("inverter", "vazio", List.of("qbd[]"), false));
        tabela.adicionar(new Simbolo("inverter", "vazio", List.of("txt[]"), false));
        tabela.adicionar(new Simbolo("inverter", "vazio", List.of("ltr[]"), false));
        tabela.adicionar(new Simbolo("inverter", "vazio", List.of("lgc[]"), false));

        // anexar
        tabela.adicionar(new Simbolo("anexar", "int[]", List.of("int[]", "int"), false));
        tabela.adicionar(new Simbolo("anexar", "qbd[]", List.of("qbd[]", "qbd"), false));
        tabela.adicionar(new Simbolo("anexar", "txt[]", List.of("txt[]", "txt"), false));
        tabela.adicionar(new Simbolo("anexar", "ltr[]", List.of("ltr[]", "ltr"), false));
        tabela.adicionar(new Simbolo("anexar", "lgc[]", List.of("lgc[]", "lgc"), false));

        // inserir
        tabela.adicionar(new Simbolo("inserir", "int[]", List.of("int[]", "int", "int"), false));
        tabela.adicionar(new Simbolo("inserir", "qbd[]", List.of("qbd[]", "int", "qbd"), false));
        tabela.adicionar(new Simbolo("inserir", "txt[]", List.of("txt[]", "int", "txt"), false));
        tabela.adicionar(new Simbolo("inserir", "ltr[]", List.of("ltr[]", "int", "ltr"), false));
        tabela.adicionar(new Simbolo("inserir", "lgc[]", List.of("lgc[]", "int", "lgc"), false));

        // remover
        tabela.adicionar(new Simbolo("remover", "int[]", List.of("int[]", "int"), false));
        tabela.adicionar(new Simbolo("remover", "qbd[]", List.of("qbd[]", "int"), false));
        tabela.adicionar(new Simbolo("remover", "txt[]", List.of("txt[]", "int"), false));
        tabela.adicionar(new Simbolo("remover", "ltr[]", List.of("ltr[]", "int"), false));
        tabela.adicionar(new Simbolo("remover", "lgc[]", List.of("lgc[]", "int"), false));

        // redim
        tabela.adicionar(new Simbolo("redim", "int[]", List.of("int[]", "int"), false));
        tabela.adicionar(new Simbolo("redim", "qbd[]", List.of("qbd[]", "int"), false));
        tabela.adicionar(new Simbolo("redim", "txt[]", List.of("txt[]", "int"), false));
        tabela.adicionar(new Simbolo("redim", "ltr[]", List.of("ltr[]", "int"), false));
        tabela.adicionar(new Simbolo("redim", "lgc[]", List.of("lgc[]", "int"), false));

        // slice1d
        tabela.adicionar(new Simbolo("slice1d", "int[]", List.of("int[]", "int", "int"), false));
        tabela.adicionar(new Simbolo("slice1d", "qbd[]", List.of("qbd[]", "int", "int"), false));
        tabela.adicionar(new Simbolo("slice1d", "txt[]", List.of("txt[]", "int", "int"), false));
        tabela.adicionar(new Simbolo("slice1d", "ltr[]", List.of("ltr[]", "int", "int"), false));
        tabela.adicionar(new Simbolo("slice1d", "lgc[]", List.of("lgc[]", "int", "int"), false));
    }
}