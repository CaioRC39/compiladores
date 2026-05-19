import java.util.Scanner;

public class ProgramaSaida {
    static Scanner scanner = new Scanner(System.in);

    public static int somar(int a, int b) {
        int res = a + b;
        return res;
    }

    public static void main(String[] args) {
        int x = 10;
        int y = 20;
        int resultado;
        System.out.println("Calculando a soma via Funcao:");
        resultado = somar(x, y);
        System.out.println(resultado);
    }
}
