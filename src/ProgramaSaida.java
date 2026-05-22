import java.util.Scanner;

import java.util.Locale;

public class ProgramaSaida {
    static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String[] args) {
        String nome;
        int idade;
        double altura;
        boolean gostaDeProgramar;
        char inicial;
        System.out.println("=== TESTE COMPLETO DO SCANNER ===");
        System.out.println("Digite o seu primeiro nome: ");
        nome = scanner.next();
        System.out.println("Digite a sua idade (inteiro): ");
        idade = scanner.nextInt();
        System.out.println("Digite a sua altura (com ponto): ");
        altura = scanner.nextDouble();
        System.out.println("Gosta de programar? (escreva true ou false): ");
        gostaDeProgramar = scanner.nextBoolean();
        System.out.println("Digite a inicial do seu apelido (1 caractere): ");
        inicial = scanner.next().charAt(0);
        System.out.println("");
        System.out.println("=== DADOS LIDOS COM SUCESSO ===");
        System.out.println("Nome lido: ");
        System.out.println(nome);
        System.out.println("Idade lida: ");
        System.out.println(idade);
        System.out.println("Altura lida: ");
        System.out.println(altura);
        System.out.println("Gosta de programar: ");
        System.out.println(gostaDeProgramar);
        System.out.println("Inicial lida: ");
        System.out.println(inicial);
    }
}
