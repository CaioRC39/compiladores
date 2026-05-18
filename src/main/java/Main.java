import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Lê o código fonte que criamos no Passo 1
            CharStream input = CharStreams.fromFileName("teste.tarbalo");

            // 2. Análise Léxica (Tokenização)
            TarbaloLexer lexer = new TarbaloLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // 3. Análise Sintática (Parser)
            TarbaloParser parser = new TarbaloParser(tokens);
            ParseTree tree = parser.programa(); // Inicia pela regra 'programa'

            // 4. Análise Semântica (O seu Listener)
            AnalisadorSemantico semantico = new AnalisadorSemantico();
            ParseTreeWalker walker = new ParseTreeWalker();

            System.out.println("Iniciando analise do codigo...");
            walker.walk(semantico, tree);
            System.out.println("Analise concluida!");

        } catch (Exception e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}