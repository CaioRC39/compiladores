import java.util.Scanner;
import java.util.Locale;

public class ProgramaSaida {
    static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    public static int[] slice1d_int(int[] arr, int ini, int fimExcl) {
        int tam = fimExcl - ini;
        if (tam <= 0) {
            int[] arrVazio = new int[0];
            return arrVazio;

        }
        int[] res = new int[tam];
        for (int i = 0; i < tam; i = i + 1) {
            res[i] = arr[ini + i];

        }
        return res;

    }


    public static int[][] slice2d_int(int[][] arr, int ini0, int fimExcl0, int ini1, int fimExcl1) {
        int lin = fimExcl0 - ini0;
        int col = fimExcl1 - ini1;
        if (lin <= 0 || col <= 0) {
            int[][] arrVazio = new int[0][0];
            return arrVazio;

        }
        int[][] res = new int[lin][col];
        for (int i = 0; i < lin; i = i + 1) {
            for (int j = 0; j < col; j = j + 1) {
                res[i][j] = arr[ini0 + i][ini1 + j];

            }

        }
        return res;

    }


    public static int[] anexar_int(int[] arr, int elem) {
        int tam = arr.length;
        int[] novo = new int[tam + 1];
        for (int i = 0; i < tam; i = i + 1) {
            novo[i] = arr[i];

        }
        novo[tam] = elem;
        return novo;

    }


    public static int[] inserir_int(int[] arr, int pos, int elem) {
        int tam = arr.length;
        int[] novo = new int[tam + 1];
        for (int i = 0; i < pos; i = i + 1) {
            novo[i] = arr[i];

        }
        novo[pos] = elem;
        for (int i = pos; i < tam; i = i + 1) {
            novo[i + 1] = arr[i];

        }
        return novo;

    }


    public static int[] remover_int(int[] arr, int pos) {
        int tam = arr.length;
        int[] novo = new int[tam - 1];
        for (int i = 0; i < pos; i = i + 1) {
            novo[i] = arr[i];

        }
        for (int i = pos + 1; i < tam; i = i + 1) {
            novo[i - 1] = arr[i];

        }
        return novo;

    }


    public static void ordenar_int(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i = i + 1) {
            for (int j = 0; j < n - i - 1; j = j + 1) {
                if (arr[j] > arr[j + 1]) {
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;

                }

            }

        }

    }


    public static void inverter_int(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n / 2; i = i + 1) {
            int t = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = t;

        }

    }


    public static int[] redim_int(int[] arr, int novoTam) {
        int tam = arr.length;
        int[] novo = new int[novoTam];
        int lim = novoTam;
        if (tam < lim) {
            lim = tam;

        }
        for (int i = 0; i < lim; i = i + 1) {
            novo[i] = arr[i];

        }
        return novo;

    }


    public static double[] slice1d_qbd(double[] arr, int ini, int fimExcl) {
        int tam = fimExcl - ini;
        if (tam <= 0) {
            double[] arrVazio = new double[0];
            return arrVazio;

        }
        double[] res = new double[tam];
        for (int i = 0; i < tam; i = i + 1) {
            res[i] = arr[ini + i];

        }
        return res;

    }


    public static double[][] slice2d_qbd(double[][] arr, int ini0, int fimExcl0, int ini1, int fimExcl1) {
        int lin = fimExcl0 - ini0;
        int col = fimExcl1 - ini1;
        if (lin <= 0 || col <= 0) {
            double[][] arrVazio = new double[0][0];
            return arrVazio;

        }
        double[][] res = new double[lin][col];
        for (int i = 0; i < lin; i = i + 1) {
            for (int j = 0; j < col; j = j + 1) {
                res[i][j] = arr[ini0 + i][ini1 + j];

            }

        }
        return res;

    }


    public static double[] anexar_qbd(double[] arr, double elem) {
        int tam = arr.length;
        double[] novo = new double[tam + 1];
        for (int i = 0; i < tam; i = i + 1) {
            novo[i] = arr[i];

        }
        novo[tam] = elem;
        return novo;

    }


    public static double[] inserir_qbd(double[] arr, int pos, double elem) {
        int tam = arr.length;
        double[] novo = new double[tam + 1];
        for (int i = 0; i < pos; i = i + 1) {
            novo[i] = arr[i];

        }
        novo[pos] = elem;
        for (int i = pos; i < tam; i = i + 1) {
            novo[i + 1] = arr[i];

        }
        return novo;

    }


    public static double[] remover_qbd(double[] arr, int pos) {
        int tam = arr.length;
        double[] novo = new double[tam - 1];
        for (int i = 0; i < pos; i = i + 1) {
            novo[i] = arr[i];

        }
        for (int i = pos + 1; i < tam; i = i + 1) {
            novo[i - 1] = arr[i];

        }
        return novo;

    }


    public static void ordenar_qbd(double[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i = i + 1) {
            for (int j = 0; j < n - i - 1; j = j + 1) {
                if (arr[j] > arr[j + 1]) {
                    double t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;

                }

            }

        }

    }


    public static void inverter_qbd(double[] arr) {
        int n = arr.length;
        for (int i = 0; i < n / 2; i = i + 1) {
            double t = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = t;

        }

    }


    public static double[] redim_qbd(double[] arr, int novoTam) {
        int tam = arr.length;
        double[] novo = new double[novoTam];
        int lim = novoTam;
        if (tam < lim) {
            lim = tam;

        }
        for (int i = 0; i < lim; i = i + 1) {
            novo[i] = arr[i];

        }
        return novo;

    }


    public static String[] slice1d_txt(String[] arr, int ini, int fimExcl) {
        int tam = fimExcl - ini;
        if (tam <= 0) {
            String[] arrVazio = new String[0];
            return arrVazio;

        }
        String[] res = new String[tam];
        for (int i = 0; i < tam; i = i + 1) {
            res[i] = arr[ini + i];

        }
        return res;

    }


    public static String[][] slice2d_txt(String[][] arr, int ini0, int fimExcl0, int ini1, int fimExcl1) {
        int lin = fimExcl0 - ini0;
        int col = fimExcl1 - ini1;
        if (lin <= 0 || col <= 0) {
            String[][] arrVazio = new String[0][0];
            return arrVazio;

        }
        String[][] res = new String[lin][col];
        for (int i = 0; i < lin; i = i + 1) {
            for (int j = 0; j < col; j = j + 1) {
                res[i][j] = arr[ini0 + i][ini1 + j];

            }

        }
        return res;

    }


    public static String[] anexar_txt(String[] arr, String elem) {
        int tam = arr.length;
        String[] novo = new String[tam + 1];
        for (int i = 0; i < tam; i = i + 1) {
            novo[i] = arr[i];

        }
        novo[tam] = elem;
        return novo;

    }


    public static String[] inserir_txt(String[] arr, int pos, String elem) {
        int tam = arr.length;
        String[] novo = new String[tam + 1];
        for (int i = 0; i < pos; i = i + 1) {
            novo[i] = arr[i];

        }
        novo[pos] = elem;
        for (int i = pos; i < tam; i = i + 1) {
            novo[i + 1] = arr[i];

        }
        return novo;

    }


    public static String[] remover_txt(String[] arr, int pos) {
        int tam = arr.length;
        String[] novo = new String[tam - 1];
        for (int i = 0; i < pos; i = i + 1) {
            novo[i] = arr[i];

        }
        for (int i = pos + 1; i < tam; i = i + 1) {
            novo[i - 1] = arr[i];

        }
        return novo;

    }


    public static void ordenar_txt(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i = i + 1) {
            for (int j = 0; j < n - i - 1; j = j + 1) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    String t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;

                }

            }

        }

    }


    public static void inverter_txt(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n / 2; i = i + 1) {
            String t = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = t;

        }

    }


    public static String[] redim_txt(String[] arr, int novoTam) {
        int tam = arr.length;
        String[] novo = new String[novoTam];
        int lim = novoTam;
        if (tam < lim) {
            lim = tam;

        }
        for (int i = 0; i < lim; i = i + 1) {
            novo[i] = arr[i];

        }
        return novo;

    }


    public static char[] slice1d_ltr(char[] arr, int ini, int fimExcl) {
        int tam = fimExcl - ini;
        if (tam <= 0) {
            char[] arrVazio = new char[0];
            return arrVazio;

        }
        char[] res = new char[tam];
        for (int i = 0; i < tam; i = i + 1) {
            res[i] = arr[ini + i];

        }
        return res;

    }


    public static char[][] slice2d_ltr(char[][] arr, int ini0, int fimExcl0, int ini1, int fimExcl1) {
        int lin = fimExcl0 - ini0;
        int col = fimExcl1 - ini1;
        if (lin <= 0 || col <= 0) {
            char[][] arrVazio = new char[0][0];
            return arrVazio;

        }
        char[][] res = new char[lin][col];
        for (int i = 0; i < lin; i = i + 1) {
            for (int j = 0; j < col; j = j + 1) {
                res[i][j] = arr[ini0 + i][ini1 + j];

            }

        }
        return res;

    }


    public static char[] anexar_ltr(char[] arr, char elem) {
        int tam = arr.length;
        char[] novo = new char[tam + 1];
        for (int i = 0; i < tam; i = i + 1) {
            novo[i] = arr[i];

        }
        novo[tam] = elem;
        return novo;

    }


    public static char[] inserir_ltr(char[] arr, int pos, char elem) {
        int tam = arr.length;
        char[] novo = new char[tam + 1];
        for (int i = 0; i < pos; i = i + 1) {
            novo[i] = arr[i];

        }
        novo[pos] = elem;
        for (int i = pos; i < tam; i = i + 1) {
            novo[i + 1] = arr[i];

        }
        return novo;

    }


    public static char[] remover_ltr(char[] arr, int pos) {
        int tam = arr.length;
        char[] novo = new char[tam - 1];
        for (int i = 0; i < pos; i = i + 1) {
            novo[i] = arr[i];

        }
        for (int i = pos + 1; i < tam; i = i + 1) {
            novo[i - 1] = arr[i];

        }
        return novo;

    }


    public static void ordenar_ltr(char[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i = i + 1) {
            for (int j = 0; j < n - i - 1; j = j + 1) {
                if (arr[j] > arr[j + 1]) {
                    char t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;

                }

            }

        }

    }


    public static void inverter_ltr(char[] arr) {
        int n = arr.length;
        for (int i = 0; i < n / 2; i = i + 1) {
            char t = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = t;

        }

    }


    public static char[] redim_ltr(char[] arr, int novoTam) {
        int tam = arr.length;
        char[] novo = new char[novoTam];
        int lim = novoTam;
        if (tam < lim) {
            lim = tam;

        }
        for (int i = 0; i < lim; i = i + 1) {
            novo[i] = arr[i];

        }
        return novo;

    }


    public static boolean[] slice1d_lgc(boolean[] arr, int ini, int fimExcl) {
        int tam = fimExcl - ini;
        if (tam <= 0) {
            boolean[] arrVazio = new boolean[0];
            return arrVazio;

        }
        boolean[] res = new boolean[tam];
        for (int i = 0; i < tam; i = i + 1) {
            res[i] = arr[ini + i];

        }
        return res;

    }


    public static boolean[][] slice2d_lgc(boolean[][] arr, int ini0, int fimExcl0, int ini1, int fimExcl1) {
        int lin = fimExcl0 - ini0;
        int col = fimExcl1 - ini1;
        if (lin <= 0 || col <= 0) {
            boolean[][] arrVazio = new boolean[0][0];
            return arrVazio;

        }
        boolean[][] res = new boolean[lin][col];
        for (int i = 0; i < lin; i = i + 1) {
            for (int j = 0; j < col; j = j + 1) {
                res[i][j] = arr[ini0 + i][ini1 + j];

            }

        }
        return res;

    }


    public static boolean[] anexar_lgc(boolean[] arr, boolean elem) {
        int tam = arr.length;
        boolean[] novo = new boolean[tam + 1];
        for (int i = 0; i < tam; i = i + 1) {
            novo[i] = arr[i];

        }
        novo[tam] = elem;
        return novo;

    }


    public static boolean[] inserir_lgc(boolean[] arr, int pos, boolean elem) {
        int tam = arr.length;
        boolean[] novo = new boolean[tam + 1];
        for (int i = 0; i < pos; i = i + 1) {
            novo[i] = arr[i];

        }
        novo[pos] = elem;
        for (int i = pos; i < tam; i = i + 1) {
            novo[i + 1] = arr[i];

        }
        return novo;

    }


    public static boolean[] remover_lgc(boolean[] arr, int pos) {
        int tam = arr.length;
        boolean[] novo = new boolean[tam - 1];
        for (int i = 0; i < pos; i = i + 1) {
            novo[i] = arr[i];

        }
        for (int i = pos + 1; i < tam; i = i + 1) {
            novo[i - 1] = arr[i];

        }
        return novo;

    }


    public static void inverter_lgc(boolean[] arr) {
        int n = arr.length;
        for (int i = 0; i < n / 2; i = i + 1) {
            boolean t = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = t;

        }

    }


    public static boolean[] redim_lgc(boolean[] arr, int novoTam) {
        int tam = arr.length;
        boolean[] novo = new boolean[novoTam];
        int lim = novoTam;
        if (tam < lim) {
            lim = tam;

        }
        for (int i = 0; i < lim; i = i + 1) {
            novo[i] = arr[i];

        }
        return novo;

    }


    public static int dobrar(int valor) {
        return valor * 2;

    }


    public static void imprimirSoma(int x, int y) {
        System.out.println(String.valueOf("Soma:") + " " + String.valueOf(x + y));

    }


    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;

        }
        return fibonacci(n - 1) + fibonacci(n - 2);

    }


    public static void main(String[] args) {
        {
            System.out.println(String.valueOf("================================================================="));
            System.out.println(String.valueOf("TESTE ABRANGENTE DA LINGUAGEM TARBALO"));
            System.out.println(String.valueOf("================================================================="));
            int a = 10;
            double b = 3.14;
            String c = "Olá mundo";
            char d = 'T';
            boolean f = true;
            int semValor;
            System.out.println(String.valueOf("=== Declarações ==="));
            System.out.println(String.valueOf("a:") + " " + String.valueOf(a) + " " + String.valueOf(" b:") + " " + String.valueOf(b) + " " + String.valueOf(" c:") + " " + String.valueOf(c) + " " + String.valueOf(" d:") + " " + String.valueOf(d) + " " + String.valueOf(" f:") + " " + String.valueOf(f));
            int[] v = {1, 2, 3};
            double[] w = {0.5, 1.5, 2.5};
            String[] x = {"casa", "carro"};
            char[] y = {'a', 'b', 'c'};
            boolean[] z = {true, false};
            System.out.println(String.valueOf("=== Vetores ==="));
            System.out.println(String.valueOf("v[0]:") + " " + String.valueOf(v[0]) + " " + String.valueOf(" v[1]:") + " " + String.valueOf(v[1]) + " " + String.valueOf(" v[2]:") + " " + String.valueOf(v[2]));
            System.out.println(String.valueOf("w[0]:") + " " + String.valueOf(w[0]) + " " + String.valueOf(" x[1]:") + " " + String.valueOf(x[1]) + " " + String.valueOf(" y[2]:") + " " + String.valueOf(y[2]));
            System.out.println(String.valueOf("z[0]:") + " " + String.valueOf(z[0]) + " " + String.valueOf(" z[1]:") + " " + String.valueOf(z[1]));
            int n = 5;
            int[] din = new int[n];
            din[0] = 100;
            din[4] = 999;
            System.out.println(String.valueOf("=== Vetor dinâmico ==="));
            System.out.println(String.valueOf("din[0]:") + " " + String.valueOf(din[0]) + " " + String.valueOf(" din[4]:") + " " + String.valueOf(din[4]));
            int[][] mat = new int[2][3];
            mat[0][0] = 1;
            mat[0][1] = 2;
            mat[0][2] = 3;
            mat[1][0] = 4;
            mat[1][1] = 5;
            mat[1][2] = 6;
            System.out.println(String.valueOf("=== Matriz 2x3 ==="));
            System.out.println(String.valueOf("mat[0][0]:") + " " + String.valueOf(mat[0][0]) + " " + String.valueOf(" mat[1][2]:") + " " + String.valueOf(mat[1][2]));
            int r = 10 + 5 * 2;
            System.out.println(String.valueOf("=== Aritmética ==="));
            System.out.println(String.valueOf("10 + 5 * 2 =") + " " + String.valueOf(r));
            r = (10 + 5) * 2;
            System.out.println(String.valueOf("(10 + 5) * 2 =") + " " + String.valueOf(r));
            r = 17 / 5;
            System.out.println(String.valueOf("17 // 5 =") + " " + String.valueOf(r));
            r = 17 % 5;
            System.out.println(String.valueOf("17 % 5 =") + " " + String.valueOf(r));
            r = 10 - 3 - 2;
            System.out.println(String.valueOf("10 - 3 - 2 =") + " " + String.valueOf(r));
            String s1 = "Hello";
            String s2 = "World";
            String s3 = String.valueOf(s1) + String.valueOf(" ") + String.valueOf(s2);
            System.out.println(String.valueOf("=== Concatenação ==="));
            System.out.println(String.valueOf(s3));
            String s4 = String.valueOf("número: ") + String.valueOf(42);
            System.out.println(String.valueOf(s4));
            System.out.println(String.valueOf("=== Relacionais ==="));
            System.out.println(String.valueOf("5 > 3:") + " " + String.valueOf(5 > 3));
            System.out.println(String.valueOf("5 < 3:") + " " + String.valueOf(5 < 3));
            System.out.println(String.valueOf("5 = 5:") + " " + String.valueOf(5 == 5));
            System.out.println(String.valueOf("5 != 3:") + " " + String.valueOf(5 != 3));
            System.out.println(String.valueOf("5 >= 5:") + " " + String.valueOf(5 >= 5));
            System.out.println(String.valueOf("3 <= 5:") + " " + String.valueOf(3 <= 5));
            System.out.println(String.valueOf("=== Lógicos ==="));
            System.out.println(String.valueOf("VDD e VDD:") + " " + String.valueOf(true && true));
            System.out.println(String.valueOf("VDD ou FAKE:") + " " + String.valueOf(true || false));
            System.out.println(String.valueOf("VDD xor VDD:") + " " + String.valueOf(true ^ true));
            System.out.println(String.valueOf("nao FAKE:") + " " + String.valueOf(!false));
            int t = 100;
            System.out.println(String.valueOf("=== Atribuições compostas ==="));
            t += 5;
            System.out.println(String.valueOf("t += 5 ->") + " " + String.valueOf(t));
            t -= 3;
            System.out.println(String.valueOf("t -= 3 ->") + " " + String.valueOf(t));
            t *= 2;
            System.out.println(String.valueOf("t *= 2 ->") + " " + String.valueOf(t));
            t /= 4;
            System.out.println(String.valueOf("t /= 4 ->") + " " + String.valueOf(t));
            t %= 7;
            System.out.println(String.valueOf("t %= 7 ->") + " " + String.valueOf(t));
            int inc = 0;
            inc++;
            inc++;
            inc++;
            System.out.println(String.valueOf("=== Incremento ==="));
            System.out.println(String.valueOf("inc após 3++:") + " " + String.valueOf(inc));
            inc--;
            System.out.println(String.valueOf("inc após 1--:") + " " + String.valueOf(inc));
            System.out.println(String.valueOf("=== Condicional ==="));
            if (a > 5) {
                System.out.println(String.valueOf("a é maior que 5"));

            } else {
                System.out.println(String.valueOf("a não é maior que 5"));

            }
            if (a == 10) {
                System.out.println(String.valueOf("a é igual a 10"));

            }
            System.out.println(String.valueOf("=== Enquanto ==="));
            int cont = 0;
            while (cont < 3) {
                System.out.println(String.valueOf("cont:") + " " + String.valueOf(cont));
                cont++;

            }
            System.out.println(String.valueOf("=== Faça-enquanto ==="));
            cont = 0;
            do {
                System.out.println(String.valueOf("faca cont:") + " " + String.valueOf(cont));
                cont++;

            } while (cont < 3);
            System.out.println(String.valueOf("=== Para ==="));
            for (int i = 0; i < 5; i++) {
                System.out.println(String.valueOf("i:") + " " + String.valueOf(i));

            }
            int j = 0;
            for (j = 0; j < 3; j++) {
                System.out.println(String.valueOf("j:") + " " + String.valueOf(j));

            }
            System.out.println(String.valueOf("=== Pancada ==="));
            for (int elem : v) {
                System.out.println(String.valueOf("elem:") + " " + String.valueOf(elem));

            }
            System.out.println(String.valueOf("=== Morre ==="));
            int k = 0;
            while (true) {
                k++;
                if (k >= 3) {
                    break;

                }
                System.out.println(String.valueOf("k antes do morre:") + " " + String.valueOf(k));

            }
            System.out.println(String.valueOf("k final:") + " " + String.valueOf(k));
            System.out.println(String.valueOf("=== Dale ==="));
            int m = 0;
            while (m < 5) {
                m++;
                if (m == 3) {
                    continue;

                }
                System.out.println(String.valueOf("m:") + " " + String.valueOf(m));

            }
            System.out.println(String.valueOf("=== Funções ==="));
            a = dobrar(7);
            System.out.println(String.valueOf("dobrar(7):") + " " + String.valueOf(a));
            imprimirSoma(3, 4);
            System.out.println(String.valueOf("fibonacci(6):") + " " + String.valueOf(fibonacci(6)));
            System.out.println(String.valueOf("=== Slice ==="));
            System.out.println(String.valueOf("v[0..1]:") + " " + String.valueOf(slice1d_int(v, 0, (1) + 1)));
            System.out.println(String.valueOf("=== Bondlib ==="));
            int tam = v.length;
            System.out.println(String.valueOf("tamanho(v):") + " " + String.valueOf(tam));
            int[] fatia = slice1d_int(v, 0, 2);
            System.out.println(String.valueOf("slice1d_int(v; 0; 2):") + " " + String.valueOf(fatia[0]) + " " + String.valueOf(fatia[1]));
            v = anexar_int(v, 99);
            System.out.println(String.valueOf("anexar_int(v;99) tamanho:") + " " + String.valueOf(v.length));
            v = inserir_int(v, 1, 50);
            System.out.println(String.valueOf("inserir_int(v;1;50) v[1]:") + " " + String.valueOf(v[1]));
            v = remover_int(v, 0);
            System.out.println(String.valueOf("remover_int(v;0) v[0]:") + " " + String.valueOf(v[0]));
            int[] ordenado = {5, 3, 1, 4, 2};
            ordenar_int(ordenado);
            System.out.println(String.valueOf("ordenar_int [5; 3; 1; 4; 2]:") + " " + String.valueOf(ordenado[0]) + " " + String.valueOf(ordenado[1]) + " " + String.valueOf(ordenado[2]) + " " + String.valueOf(ordenado[3]) + " " + String.valueOf(ordenado[4]));
            int[] inv = {10, 20, 30};
            inverter_int(inv);
            System.out.println(String.valueOf("inverter_int [10; 20; 30]:") + " " + String.valueOf(inv[0]) + " " + String.valueOf(inv[1]) + " " + String.valueOf(inv[2]));
            int[] red = redim_int(v, 3);
            System.out.println(String.valueOf("redim_int(v;3) tamanho:") + " " + String.valueOf(red.length));
            System.out.println(String.valueOf("=== Bondlib outros tipos ==="));
            double[] decArr = {1.1, 2.2, 3.3};
            decArr = anexar_qbd(decArr, 4.4);
            System.out.println(String.valueOf("anexar_qbd tamanho:") + " " + String.valueOf(decArr.length));
            String[] txtArr = {"hello", "world"};
            txtArr = anexar_txt(txtArr, "!");
            System.out.println(String.valueOf("anexar_txt tamanho:") + " " + String.valueOf(txtArr.length) + " " + String.valueOf(" último:") + " " + String.valueOf(txtArr[2]));
            System.out.println(String.valueOf("=== Blocos aninhados ==="));
            {
                int escopo = 42;
                System.out.println(String.valueOf("escopo interno:") + " " + String.valueOf(escopo));
                {
                    int escopo2 = 99;
                    System.out.println(String.valueOf("escopo aninhado:") + " " + String.valueOf(escopo2));

                }

            }
            System.out.println(String.valueOf("=== Laços aninhados ==="));
            for (int ii = 0; ii < 3; ii++) {
                for (int jj = 0; jj < 2; jj++) {
                    System.out.println(String.valueOf("ii:") + " " + String.valueOf(ii) + " " + String.valueOf(" jj:") + " " + String.valueOf(jj));

                }

            }
            System.out.println(String.valueOf("=== FIM DO TESTE ==="));

        }

    }
}
