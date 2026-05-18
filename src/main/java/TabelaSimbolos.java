import java.util.HashMap;
import java.util.Map;

public class TabelaSimbolos {

    private Map<String, String> tabela;

    public TabelaSimbolos() {
        this.tabela = new HashMap<>();
    }

    public void adicionar(String nome, String tipo) {
        tabela.put(nome, tipo);
    }

    public boolean existe(String nome) {
        return tabela.containsKey(nome);
    }

    public String obterTipo(String nome) {
        return tabela.get(nome);
    }
}