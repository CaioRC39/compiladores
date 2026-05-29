# Implementações Futuras

## 1. Classes (`classe`)

### Visão Geral

Implementar suporte a classes na linguagem Tarbalo, permitindo:
- Declaração de campos (atributos)
- Declaração de métodos (funções de instância)
- Construtores
- Instanciação com `new`
- Acesso a campos/métodos via `::`

### Sintaxe Proposta

```tarbalo
classe Pessoa {
    bglh txt nome.
    bglh int idade.

    func Pessoa(txt nome. int idade) {
        this::nome : nome.
        this::idade : idade.
    }.

    func vazio aniversario() {
        this::idade ++.
    }.

    func txt apresentar() {
        receba "Meu nome e " & this::nome & " e tenho " & this::idade & " anos".
    }.
}.
```

### Uso

```tarbalo
bora
    bglh Pessoa p: new Pessoa("Maria"; 25).
    manda(p::nome).
    p::aniversario().
    manda(p::apresentar()).

    # Vetor de objetos
    bond Pessoa turma[3].
    turma[0] : new Pessoa("Ana"; 20).
    turma[1] : new Pessoa("Bob"; 22).
    turma[2] : new Pessoa("Carol"; 21).

    pancada (bglh Pessoa aluno em turma) {
        manda(aluno::apresentar()).
    }.
flw.
```

### Saída Java Esperada

```java
import java.util.Scanner;
import java.util.Locale;

public class ProgramaSaida {
    static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    public static void imprimirPessoa(Pessoa p) {
        System.out.println(p.apresentar());
    }

    public static void main(String[] args) {
        Pessoa p = new Pessoa("Maria", 25);
        System.out.println(p.nome);
        p.aniversario();
        System.out.println(p.apresentar());
        imprimirPessoa(p);

        Pessoa[] turma = new Pessoa[3];
        turma[0] = new Pessoa("Ana", 20);
        turma[1] = new Pessoa("Bob", 22);
        turma[2] = new Pessoa("Carol", 21);

        for (Pessoa aluno : turma) {
            System.out.println(aluno.apresentar());
        }
    }

    public static class Pessoa {
        public String nome;
        public int idade;

        public Pessoa(String nome, int idade) {
            this.nome = nome;
            this.idade = idade;
        }

        public void aniversario() {
            this.idade++;
        }

        public String apresentar() {
            return "Meu nome e " + this.nome + " e tenho " + this.idade + " anos";
        }
    }
}
```

---

### Análise por Componente

#### 1. Gramática (`Tarbalo.g4`)

**O que já existe e pode ser reutilizado:**
- `tipoVariavel : ID` já aceita nomes de classe como tipo (ex: `Pessoa`)
- `acessoElem : ID (dimensao)* DOISPONTOS ID` já funciona para `obj::campo`
- `declaracaoVariavel` e `declaracaoFuncao` podem ser reusados dentro do corpo da classe
- Token `DOISPONTOS : '::'` já existe para acesso a membros

**O que precisa ser adicionado:**
- Token `CLASSE : 'classe'`
- Regra `declaracaoClasse` (corpo com campos + métodos)
- Regra `chamadaMetodo` para `obj::metodo(args)` — a regra `acessoElem` atual não aceita argumentos
- `declaracaoClasse` adicionada em `declaracao`; `chamadaMetodo` adicionada em `operando` e `comando`

**Gramática nova:**
```
declaracaoClasse:
    CLASSE ID ABRECHAVE (membroClasse)* FECHACHAVE PONTO
;

membroClasse:
    declaracaoVariavel
    | declaracaoFuncao
;

chamadaMetodo:
    ID DOISPONTOS ID ABREPARENTE argumentos? FECHAPARENTE
;
```

#### 2. Símbolo (`Simbolo.java`)

**O que já existe e pode ser reutilizar:**
- O padrão de factory methods (`criarCnjt`, `criarCnjtElem`) serve como modelo
- O campo `tipoCnjt` serve como modelo para `nomeClasse`
- Sobrecarga de funções já implementada

**O que precisa ser adicionado:**
- Categorias: `CLASSE`, `METODO`, `CAMPO`, `CONSTRUTOR`
- Campos: `Map<String, Simbolo> campos` e `Map<String, List<Simbolo>> metodos` dentro do Simbolo da classe
- Factories: `criarClasse`, `criarConstrutor`, `criarMetodo`, `criarCampo`

#### 3. Tabela de Símbolos (`TabelaSimbolos.java`)

**O que já existe e pode ser reutilizado:**
- `pilhaEscopos` — mesmo mecanismo de escopo
- `funcoes` map — sobrecarga de métodos pode reusar
- `memoria` e `buscarComMemoria` — transpilador já usa para após fechamento de escopos

**O que precisa ser adicionado:**
- `Map<String, Simbolo> classes` — índice de classes declaradas
- `buscarClasse(nome)` e `buscarMembro(nomeClasse, nomeMembro)`

#### 4. Analisador Semântico (`AnalisadorSemantico.java`)

**O que já existe e pode ser reutilizado:**
- `enterDeclaracaoFuncao`/`exitDeclaracaoFuncao` — modelo para gestão de escopo de métodos
- `exitDeclaracaoCnjt` — modelo para validação de membros de classe
- `enterCmdPegue` — modelo para escopo local com variável
- `AvaliadorDeTipos` — inferência de tipos já funciona
- `saoCompativeis` — precisaria extensão para classes

**O que precisa ser adicionado:**
- `Deque<String> pilhaClasses` — para rastrear em qual classe estamos
- `enterDeclaracaoClasse`/`exitDeclaracaoClasse`
- Resolução implícita de `this::campo` quando variável não encontrada localmente
- `enterChamadaMetodo` — validar objeto + método + tipos

**A parte mais complexa:** A resolução implícita de `this`. Quando uma variável não é encontrada no escopo local, o analisador precisa buscar nos campos da classe atual. Isso exige uma pilha de contexto de classe.

#### 5. Transpilador (`TarbaloTranspilador.java`)

**O que já existe e pode ser reutilizado:**
- `visitDeclaracaoFuncao` — modelo para gerar métodos (mas não estáticos)
- `visitDeclaracaoCnjt` — modelo para gerar inner class
- `visitAcessoElem` — já gera `obj.campo` (cnjt `::` vira `.`)
- `visitChamadaFuncao` — modelo para chamada de método
- `mapearTipo` — precisa extensão para tipos de classe

**O que precisa ser adicionado:**
- `visitDeclaracaoClasse` — gera inner class com campos + métodos + construtor
- `visitChamadaMetodo` — gera `obj.metodo(args)`
- `classesGeradas: StringBuilder` — similar a `cnjtsGerados`

#### 6. Main.java

Não precisa de mudanças diretas. Classes são declaradas pelo usuário, não são builtins.

---

### Decisões de Design

| Decisão | Recomendação v1 |
|---------|-----------------|
| Keyword para instanciação | Sem keyword nova — `Pessoa("Ana"; 20)` tratado como chamada de função que vira `new Pessoa(...)` |
| `this` explícito | Implícito apenas — variável não encontrada localmente busca campo da classe |
| Métodos estáticos | v2 — funções globais já cumprem esse papel |
| Classes aninhadas | Não para v1 |
| Herança | v2 (ou nunca) |
| Visibilidade | Tudo public para v1 |
| Bondlib com classes | Sem suporte para v1 |
| Igualdade de objetos | Por referência para v1 |

---

### Plano Incremental (6 fases)

#### Fase 1: Gramática básica + Símbolo
1. Adicionar token `CLASSE : 'classe'`
2. Adicionar regra `declaracaoClasse` (apenas campos, sem métodos)
3. Adicionar `declaracaoClasse` em `declaracao`
4. Adicionar categorias `CLASSE`, `CAMPO` em `Simbolo`
5. Adicionar factories `criarClasse`, `criarCampo`

#### Fase 2: Análise semântica básica
1. `enterDeclaracaoClasse` — registrar classe na tabela
2. `exitDeclaracaoClasse` — validar campos
3. Resolução de `obj::campo` (já funciona com `acessoElem` + validação de tipo classe)
4. Aceitar classe como `tipoVariavel`

#### Fase 3: Transpilação básica
1. `visitDeclaracaoClasse` — gerar inner class com campos
2. `visitAcessoElem` — já gera `obj.campo`, só precisa reconhecer classe
3. Mapear tipo de classe em `mapearTipo`

#### Fase 4: Construtor + Instanciação
1. Adicionar categoria `CONSTRUTOR` em Simbolo
2. Detectar `func NomeClasse(...)` como construtor
3. Gerar construtor na classe Java
4. Tratar chamada `NomeClasse(...)` como `new NomeClasse(...)` no transpilador

#### Fase 5: Métodos
1. Adicionar categoria `METODO` em Simbolo
2. Regra `declaracaoFuncao` dentro de classe → método
3. Resolver `this` implícito no analisador
4. `visitChamadaMetodo` no transpilador
5. Gerar métodos de instância (não estáticos) na classe Java

#### Fase 6: Edge cases e refinamento
1. Vetores de objetos
2. Objetos como parâmetros/retornos
3. Igualdade de objetos
4. Inicialização/null
5. Testes abrangentes

---

### Riscos e Complicações

1. **Resolução de `this` implícito:** A parte mais complexa. Requer que o analisador tenha uma pilha de contexto de classe e tente resolver variáveis não encontradas como campos da classe.

2. **Diferenciação cnjt vs classe em `acessoElem`:** Ambos usam `::`. O analisador precisa distinguir: lado esquerdo é tipo (cnjt) vs variável (classe).

3. **Construtor como função:** Detectar que `func Pessoa(...)` é um construtor (retorna a própria classe). Precisa comparar nome da função com nome da classe no contexto.

4. **Tabela de símbolos interna:** Cada classe precisa de sua própria tabela de campos/métodos. Isso não existe hoje — toda tabela é global ou por escopo.

5. **ANTLR incremental pitfall:** Depois de alterar a gramática, SEMPRE rodar `mvn clean compile` para regenerar os arquivos do parser.

---

## 2. Escolha (Switch/Case)

### Visão Geral

Implementar comando `escolha` para seleção multi-caso, evitando cadeias longas de `sepa`/`vish`.

### Sintaxe Proposta

```tarbalo
escolha (x) {
    caso (1):
        manda("um").
    caso (2):
        manda("dois").
    padrao:
        manda("outro").
}.
```

### Saída Java Esperada

```java
switch (x) {
    case 1: {
        System.out.println("um");
        break;
    }
    case 2: {
        System.out.println("dois");
        break;
    }
    default: {
        System.out.println("outro");
    }
}
```

### Gramática

```
ESCOLHA : 'escolha';
CASO    : 'caso';
PADRAO  : 'padrao';

cmdEscolha:
    ESCOLHA ABREPARENTE expressao FECHAPARENTE ABRECHAVE
        (blocoCaso)+
        blocoPadrao?
    FECHACHAVE PONTO
;

blocoCaso:
    CASO ABREPARENTE expressao FECHAPARENTE DOISPONTOS bloco
;

blocoPadrao:
    PADRAO DOISPONTOS bloco
;
```

### Análise por Componente

| Componente | Mudança |
|---|---|
| `Tarbalo.g4` | Tokens `ESCOLHA`, `CASO`, `PADRAO`; regras `cmdEscolha`, `blocoCaso`, `blocoPadrao`; adicionar `cmdEscolha` em `comando` |
| `Simbolo.java` | Sem mudanças |
| `TabelaSimbolos.java` | Sem mudanças |
| `AnalisadorSemantico.java` | `exitCmdEscolha` — validar tipo da expressão (int, txt, ltr, cnjt); `exitBlocoCaso` — validar compatibilidade de tipo |
| `TarbaloTranspilador.java` | `visitCmdEscolha` → `switch`; `visitBlocoCaso` → `case ...: { ... break; }`; `visitBlocoPadrao` → `default: { ... }` |
| `Main.java` | Sem mudanças |

---

## 3. Regs (Structs Simples)

### Visão Geral

Implementar `regs` como struct de dados — apenas campos, sem métodos. Construtor implícito gerado automaticamente.

### Sintaxe Proposta

```tarbalo
regs Pessoa {
    bglh txt nome.
    bglh int idade.
}.

bora
    bglh Pessoa p: new Pessoa("Maria"; 25).
    manda(p::nome).
    p::idade : 26.
flw.
```

### Saída Java Esperada

```java
public static class Pessoa {
    public String nome;
    public int idade;
    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
}

// Em main:
Pessoa p = new Pessoa("Maria", 25);
System.out.println(p.nome);
p.idade = 26;
```

### Gramática

```
STRUCT : 'regs';

declaracaoStruct:
    STRUCT ID ABRECHAVE (declaracaoVariavel)+ FECHACHAVE PONTO
;

chamadaConstrutor:
    NEW ID ABREPARENTE argumentos? FECHAPARENTE
;
```

### Análise por Componente

| Componente | Mudança |
|---|---|
| `Tarbalo.g4` | Token `STRUCT`, `NEW`; regras `declaracaoStruct`, `chamadaConstrutor`; adicionar em `declaracao` e `operando` |
| `Simbolo.java` | Categorias `REGS`, `REGS_CAMPO`; campo `nomeRegs`; factories `criarRegs`, `criarCampo` |
| `TabelaSimbolos.java` | Método opcional `buscarCampo(nomeRegs, nomeCampo)` |
| `AnalisadorSemantico.java` | `exitDeclaracaoStruct` — registrar struct + campos; modificações em `visitOperando` para `acessoElem` com REGS; `exitChamadaConstrutor` — validar args |
| `TarbaloTranspilador.java` | `regsGerados`; `visitDeclaracaoStruct` → inner class com construtor; `visitChamadaConstrutor` → `new Nome(args)`; modificações em `mapearTipo` e `visitAcessoElem` |
| `Main.java` | Sem mudanças |

### Decisões de Design

| Decisão | Escolha |
|---|---|
| Keyword | `regs` |
| Métodos | Não (v1) — funções globais cumprem esse papel |
| Construtor | Implícito — gerado automaticamente na ordem dos campos |
| Instanciação | `new Nome(val1; val2)` — argumentos separados por `;` |
| Visibilidade | Tudo `public` (v1) |
| Herança | Não (v1) |
| Acesso | `::` (mesmo operador de cnjt) |

---

## 4. Validação de Dimensões Mistas em Vetores ✅ IMPLEMENTADO (2026-05-27)

### Regra

Vetores multidimensionais devem ser **completamente estáticos** ou **completamente dinâmicos**. Não é permitido misturar dimensões com tamanho e sem tamanho.

| Declaração | Válida? | Motivo |
|---|---|---|
| `bond int mat[2][3].` | Sim | Ambas estáticas |
| `bond int mat[][].` | Sim | Ambas dinâmicas |
| `bond int mat[][3].` | **Não** | Mistura (dinâmica + estática) |
| `bond int mat[3][].` | **Não** | Mistura (estática + dinâmica) |

### Onde implementar

Em `AnalisadorSemantico.java`, no método `exitDeclaracaoVetor` (linha ~108), após o loop que coleta as dimensões (linha ~123-140). Adicionar verificação:

```java
// Verificar mistura de dimensões estáticas e dinâmicas
boolean temEstatica = tamanhos.stream().anyMatch(t -> t != null);
boolean temDinamica = tamanhos.stream().anyMatch(t -> t == null);
if (temEstatica && temDinamica && dim > 1) {
    erros = true;
    System.err.println("Erro Semântico (Linha " + linha + "): Vetor '" + nomeVar
        + "' não pode misturar dimensões estáticas e dinâmicas.");
}
```

Aplicar a mesma validação em `exitParametro` (linha ~601) para parâmetros de função.

---

## 5. Bugs e Edge Cases do ArrayList (2026-05-27)

Encontrados durante a implementação da conversão de vetores para ArrayList.

### Trivial (< 5 min) ✅ CORRIGIDOS

| # | Bug | Arquivo | Correção | Status |
|---|---|---|---|---|
| 1 | Dead cast `(long)` — Tarbalo não tem `long` | TarbaloTranspilador.java:418 | Remover linha | ✅ |
| 2 | `void[]` produz `ArrayList<void>` (inválido) | TarbaloTranspilador.java:92 / boxarTipo | Adicionar guard no analisador ou em boxarTipo | ✅ |
| 3 | `;` sobrando após loops de slice em inc/dec | TarbaloTranspilador.java:1046 | Verificar se termina com `}` antes de append `;` | ✅ |
| 4 | Comentário desatualizado em visitValorAtribuicao | TarbaloTranspilador.java:459 | Atualizar texto para mencionar Arrays.asList | ✅ |
| 5 | `!ctx.dimensao().isEmpty()` sempre true | TarbaloTranspilador.java:1100 | Simplificar condição (redundante) | ✅ |
| 6 | Redundant second `[]` stripping em visitLeitura | TarbaloTranspilador.java:482-488 | Remover segundo if (replace já remove todos) | ✅ |

### Fácil (5-20 min) ✅ CORRIGIDOS

| # | Bug | Arquivo | Correção | Status |
|---|---|---|---|---|
| 7 | Scanner errado para elementos 2D+ em espia | TarbaloTranspilador.java:549 | Usar `simbolo.getTipoBase()` em vez de `replace("[]","")` | ✅ |
| 8 | `tamanho()` em array 2D vazio lança IndexOutOfBoundsException | TarbaloTranspilador.java:1227 | Checar `size() > 0` antes de `.get(0)` | ✅ |
| 9 | Índices negativos escapam da validação de limites | AnalisadorSemantico.java:990 | Tratar unário negativo em extrairNumero | ✅ |
| 10 | `contains("[]")` frágil no enterCmdParaCada | AnalisadorSemantico.java:797 | Usar `endsWith("[]")` ou checar dimensoes > 0 | ✅ |

### Moderado (20-60 min) ✅ CORRIGIDOS

| # | Bug | Arquivo | Correção | Status |
|---|---|---|---|---|
| 11 | Hash collision em nomes de variáveis de loop 2D | TarbaloTranspilador.java:152 | Usar contador global em vez de hashCode | ✅ |
| 12 | Indentação errada para alocação 2D local | TarbaloTranspilador.java:1104 | Passar indent correto baseado no escopo | ✅ |
| 13 | Slice 2D+ com literal usa índice errado | TarbaloTranspilador.java:388 | Calcular índice linear `i * cols + j` | ✅ |
| 14 | Temp ArrayList recriada a cada iteração de slice | TarbaloTranspilador.java:387 | Mover declaração para antes do loop | ✅ |

### Difícil (> 60 min)

| # | Bug | Arquivo | Correção | Status |
|---|---|---|---|---|
| 15 | Alocação 2D+ local desconectada da declaração | TarbaloTranspilador.java:1115 | Refatorar alocacaoArrayList para retornar bloco completo ou usar helper method | ⚠️ By design |

### Notas

- **Collections.nCopies** retorna lista de tamanho fixo (não suporta `.add()`/`.remove()`). Bondlib funciona porque cria novos arrays. Limitação conhecida, não é bug.
- Bug #7 (scanner 2D) e #8 (tamanho vazio) são os mais impactantes — podem causar erros em runtime para usuários.
- Bug #9 (índices negativos) faz a validação de limites perder casos — `v[-1]` não é detectado.

---

## 4. Switch/Case (`escolha`)

### Visão Geral

Implementar comando de seleção múltipla na linguagem Tarbalo, equivalente ao `switch/case` de outras linguagens.

### Sintaxe Proposta

```tarbalo
bglh int dia : 3.
escolha (dia) {
    caso (1):
        manda("Segunda").
    caso (2):
        manda("Terça").
    caso (3):
        manda("Quarta").
    padrao:
        manda("Outro dia").
}.
```

### Saída Java Esperada

```java
int dia = 3;
switch (dia) {
    case 1:
        System.out.println("Segunda");
        break;
    case 2:
        System.out.println("Terça");
        break;
    case 3:
        System.out.println("Quarta");
        break;
    default:
        System.out.println("Outro dia");
        break;
}
```

### Decisões de Design

| Decisão | Valor |
|---------|-------|
| Fall-through | **Não** — `break` automático após cada caso |
| Tipos válidos | `int`, `txt`, `ltr`, cnjt |
| Valores dos casos | Constantes (literais ou cnjt elem) |
| `padrao` | Opcional (equivalente a `default`) |
| Parênteses no `caso` | Sim — `caso (valor):` |

### Onde implementar

- **Gramática:** Tokens `ESCOLHA`, `CASO`, `PADRAO`; regras `cmdEscolha`, `blocoCaso`, `blocoPadrao`; adicionar em `comando`
- **Analisador Semântico:** `exitCmdEscolha` (validar tipo da expressão), `exitBlocoCaso` (validar compatibilidade de tipos)
- **Transpilador:** `visitCmdEscolha`, `visitBlocoCaso`, `visitBlocoPadrao`

---

## 5. Structs Simples (`reg`)

### Visão Geral

Implementar structs (registros) simples na linguagem Tarbalo — apenas campos de dados, sem métodos, sem construtor, sem `this`. Acesso via `::` como cnjt.

### Sintaxe Proposta

```tarbalo
reg Pessoa {
    bglh txt nome.
    bglh int idade.
}.

# Uso
bglh Pessoa p : Pessoa().
p::nome : "Maria".
p::idade : 25.
manda(p::nome).

# Vetor de structs
bond Pessoa turma[3].
turma[0] : Pessoa().
```

### Saída Java Esperada

```java
public static class Pessoa {
    public String nome;
    public int idade;
}

// Em main:
Pessoa p = new Pessoa();
p.nome = "Maria";
p.idade = 25;
System.out.println(p.nome);
```

### Decisões de Design

| Decisão | Valor |
|---------|-------|
| Keyword | `reg` (token `STRUCT`) |
| Métodos | Não — structs são só dados |
| Construtor | Não — campos atribuídos via `obj::campo : valor` |
| Instanciação | `NomeStruct()` — sem `new`, transpilador detecta e gera `new Nome()` |
| Acesso | `::` (reutiliza `acessoElem` existente) |
| Herança | Não |
| Visibilidade | Tudo `public` |
| Vetores de structs | Sim — `bond Pessoa turma[3]` |

### Onde implementar

- **Gramática:** Token `STRUCT : 'reg'`; regra `declaracaoStruct`; adicionar em `declaracao`
- **Símbolo:** Categorias `REGS`, `REGS_CAMPO`; factories `criarRegs`, `criarCampo`
- **Analisador Semântico:** `exitDeclaracaoStruct`; modificar `visitOperando` para suportar acesso a campos de regs
- **Transpilador:** `regsGerados`; `visitDeclaracaoStruct`; modificar `visitChamadaFuncao` para detectar REGS; modificar `mapearTipo`
- **Tabela de Símbolos:** Método opcional `buscarCampo(nomeRegs, nomeCampo)`

---

## 6. Organização de Arquivos do Projeto

### Situação Atual

Todos os arquivos `.tarbalo` e o `ProgramaSaida.java` ficam na raiz do projeto. Isso dificulta a organização conforme o projeto cresce.

### Estrutura Proposta

```
mini-compilador/
├── src/                          # Código-fonte do compilador
│   └── main/
│       ├── antlr4/               # Gramática ANTLR
│       └── java/                 # Java do compilador
├── linguagem/                    # Arquivos `.tarbalo`
│   ├── demo.tarbalo              # Demonstração completa
│   ├── erros_demo.tarbalo        # Testes de erros semânticos
│   ├── escolha_test.tarbalo      # Testes de escolha/switch
│   ├── regs_test.tarbalo         # Testes de structs
│   └── bondlib/                  # Testes da biblioteca padrão
│       ├── GerenciadorNotas.tarbalo
│       ├── JogoDaVelha.tarbalo
│       └── SistemaBancario.tarbalo
├── lib/                          # Biblioteca padrão
│   └── bondlib.tarbalo
└── output/                       # Código Java gerado
    └── ProgramaSaida.java
```

### Mudanças Necessárias

| Componente | Mudança |
|---|---|
| `Main.java` | Atualizar caminhos de leitura/escrita para usar pastas `linguagem/`, `lib/`, `output/` |
| `Main.java` | Adicionar argumento de linha de comando para selecionar arquivo de teste |
| `.gitignore` | Adicionar `output/` (código gerado não deve ser versionado) |
| `pom.xml` | Sem mudanças necessárias |

### Detalhes da Implementação

1. **Pasta `linguagem/`:** Mover todos os `.tarbalo` para cá. O `demo.tarbalo` fica como arquivo principal de demonstração.

2. **Pasta `lib/`:** Mover `bondlib.tarbalo` para cá. O `Main.java` deve carregar automaticamente o `bondlib.tarbalo` da pasta `lib/` antes de processar o arquivo de entrada.

3. **Pasta `output/`:** O `ProgramaSaida.java` gerado vai para cá. O `Main.java` deve compilar e executar de `output/ProgramaSaida.java`.

4. **Argumento de linha de comando:** Permitir `java com.compilador.Main linguagem/demo.tarbalo` em vez de sempre usar `demo.tarbalo` fixo.

5. **Auto-carregamento do bondlib:** Se o usuário não usar `usar "bondlib.tarbalo"`, o compilador não deve carregar automaticamente o bondlib da pasta `lib/`.

### Prioridade

**Média** — Não afeta funcionalidade, mas melhora organização e manutenibilidade do projeto.
