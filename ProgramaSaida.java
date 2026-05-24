import java.util.Scanner;
import java.util.Locale;
import java.util.Arrays;

public class ProgramaSaida {
    static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    public static int dobrar(int x) {
        return x * 2;

    }


    public static void imprimirSoma(int y, int z) {
        System.out.println(String.valueOf("Soma:") + " " + String.valueOf(y + z));

    }


    public static void main(String[] args) {
        {
            int a;
            double b = 3.14;
            String c = "Olá";
            char d = 'T';
            boolean f = true;
            int[] v = {1, 2, 3};
            double[] w = {0.0, 1.1, 2.2};
            String[] x = {"casa", "carro"};
            a++;
            a--;
            System.out.println(String.valueOf("Valor de a:") + " " + String.valueOf(a) + " " + String.valueOf(" b:") + " " + String.valueOf(b));

        }
        {
            int a;
            double b = 3.14;
            String c = "Olá";
            char d = 'T';
            boolean f = true;
            int[] v = {1, 2, 3};
            double[] w = {0.0, 1.1, 2.2};
            String[] x = {"casa", "carro"};
            int cont = 0;
            int i = 0;
            if (a > 10) {
                System.out.println(String.valueOf("a é maior que 10"));

            } else {
                System.out.println(String.valueOf("a é menor ou igual a 10"));

            }
            while (cont < 3) {
                System.out.println(String.valueOf("Contador:") + " " + String.valueOf(cont));
                cont++;

            }
            do {
                System.out.println(String.valueOf("Faca-enquanto cont:") + " " + String.valueOf(cont));
                cont++;

            } while (cont < 5);
            for (i = 0; i < 3; i++) {
                System.out.println(String.valueOf("Para i:") + " " + String.valueOf(i));

            }
            System.out.println(String.valueOf("Elementos de v:"));
            for (int elem : v) {
                System.out.println(String.valueOf(elem));

            }
            imprimirSoma(3, 4);
            System.out.println(String.valueOf("Tamanho de v:") + " " + String.valueOf(tamanho_int(v)));
            v = anexar_int(v, 99);
            v = inserir_int(v, 2, 50);
            v = remover_int(v, 1);
            ordenar_int(v);
            inverter_int(v);
            System.out.println(String.valueOf("Vetor v modificado:"));
            for (int elem : v) {
                System.out.println(String.valueOf(elem));

            }
            int k = 0;
            while (true) {
                k++;
                if (k > 2) {
                    break;

                }

            }
            int m = 0;
            while (m < 5) {
                m++;
                if (m == 3) {
                    continue;

                }
                System.out.println(String.valueOf("m:") + " " + String.valueOf(m));

            }
            System.out.println(String.valueOf("Fatia de v[0..1]:") + " " + String.valueOf(java.util.Arrays.copyOfRange(v, 0, 1)));
            System.out.println(String.valueOf("Fim do programa."));

        }

    }
}
