# Tarbalo

Tarbalo é uma linguagem de programação com sintaxe em português brasileiro, criada como projeto da disciplina de Compiladores. O compilador funciona como transpilador: lê código-fonte `.tarbalo` e gera código Java equivalente.

## Características da Linguagem

- **Sintaxe em português** — keywords como `bora`, `flw`, `bglh`, `manda`, `sepa`, `enquanto`
- **Terminador de instrução** — todo comando termina com `.` (ponto)
- **5 tipos primitivos** — `int`, `qbd` (decimal), `txt` (texto), `ltr` (caractere), `lgc` (booleano)
- **Vetores e matrizes** — com inicialização estática, fatias e validação de limites
- **Funções** — com sobrecarga e tipos de retorno
- **Controle de fluxo** — `sepa`/`vish` (if/else), `enquanto` (while), `vamo`/`enquanto` (do-while), `para` (for), `cada em` (for-each), `escolha`/`caso` (switch/case)
- **Registros (`regs`)** — structs com campos e construtor implícito
- **Enums (`cnjt`)** — tipos enumerados com acesso via `::`
- **Tratamento de erros** — `sonha`/`deuruim` (try/catch)
- **Biblioteca padrão** — 43 funções utilitárias para manipulação de vetores (slice, ordenar, inverter, etc.)
- **Operadores compostos** — `:+`, `:-`, `:*`, `:/`, `:%`
- **Concatenação** — operador `&` para strings

## O que a linguagem NÃO possui (por enquanto)

- Classes completas com métodos e construtores explícitos
- Herança
- Métodos estáticos
- Igualdade por valor para objetos
- Módulos/pacotes com namespace

## Estrutura do Projeto

```
mini-compilador/
├── src/main/
│   ├── antlr4/com/compilador/tarbalo/
│   │   └── Tarbalo.g4              # Gramática ANTLR4
│   └── java/com/compilador/
│       ├── Main.java                # Ponto de entrada
│       ├── AnalisadorSemantico.java # Análise semântica
│       ├── TabelaSimbolos.java      # Tabela de símbolos
│       ├── Simbolo.java             # Representação de símbolos
│       └── TarbaloTranspilador.java # Transpilador para Java
├── lib/
│   └── bondlib.tarbalo              # Biblioteca padrão
├── linguagem/                       # Programas de exemplo e teste
│   ├── demo.tarbalo
│   └── ...
├── output/                          # Código Java gerado (gitignored)
├── pom.xml                          # Configuração Maven
└── manual-tarbalo.md                # Manual da linguagem
```

## Como Rodar

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+

### 1. Criar um programa Tarbalo

Crie um arquivo `.tarbalo` com seu programa:

```tarbalo
bora
    bglh txt nome : "Mundo".
    manda("Olá, " & nome & "!").
flw.
```

### 2. Transpilar

```bash
# Compilar o projeto
mvn clean compile

# Transpilar o arquivo (gera output/ProgramaSaida.java)
mvn exec:java "-Dexec.mainClass=com.compilador.Main" "-Dexec.args=caminho/para/seu/arquivo.tarbalo"
```

> **Nota PowerShell:** No PowerShell, as aspas devem envolver toda a propriedade `-D`, como no exemplo acima. Não use aspas simples.

Se não passar argumento, o compilador usa `linguagem/demo.tarbalo` como padrão:

```bash
mvn exec:java "-Dexec.mainClass=com.compilador.Main"
```

### 3. Executar o código gerado

```bash
# Compilar e executar o Java gerado
javac output/ProgramaSaida.java
java -cp output ProgramaSaida
```

## Tecnologias

- **Java 17** — linguagem de implementação
- **ANTLR4** — gerador de parser/lexer
- **Maven** — build e gerenciamento de dependências

## Autores

- Caio Rebouças Candolato
-  Gabriel Souza Santos
- Guilherme Miguel Machado