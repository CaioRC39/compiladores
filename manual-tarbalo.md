# Manual da Linguagem Tarbalo

## Visão Geral

Tarbalo é uma linguagem de programação com sintaxe em português brasileiro. Todo comando termina com `.` (ponto). Blocos de código usam `{}`. Um programa é delimitado por `bora` (início) e `flw` (fim).

## Programa Base

```tarbalo
bora
    manda("Olá, mundo!").
flw.
```

## Tipos Primitivos

| Tipo   | Descrição         | Exemplo           |
|--------|-------------------|--------------------|
| `int`  | Inteiro           | `42`               |
| `qbd`  | Decimal           | `3,14`             |
| `txt`  | Texto (string)    | `"Olá"`            |
| `ltr`  | Caractere         | `'A'`              |
| `lgc`  | Booleano          | `VDD`, `FAKE`      |
| `vazio`| Sem retorno (void)| Usado em funções   |

**Nota:** O separador decimal é a **vírgula** (`3,14`), não o ponto.

## Variáveis

Declaração com `bglh`:

```tarbalo
bglh int x : 10.
bglh qbd pi : 3,14.
bglh txt nome : "Maria".
bglh ltr letra : 'A'.
bglh lgc ativo : VDD.
bglh int semValor.
```

Atribuição:

```tarbalo
x : 20.
nome : "João".
```

### Operadores de Atribuição Composta

```tarbalo
x :+ 5.    # x = x + 5
x :- 3.    # x = x - 3
x :* 2.    # x = x * 2
x :/ 4.    # x = x / 4
x :% 3.    # x = x % 3
```

### Incremento e Decremento

```tarbalo
x ++.
x --.
```

## Vetores

Declaração com `bond`:

```tarbalo
# Vetor estático com tamanho fixo
bond int notas[5].

# Vetor com inicialização
bond int valores[3] : [10; 20; 30].

# Vetor dinâmico (tamanho definido por expressão)
bglh int n : 5.
bond int dinamico[n].

# Vetor de texto
bond txt nomes[2] : ["Ana"; "Bob"].
```

### Vetores Multidimensionais

```tarbalo
# Matriz 2D
bond int mat[2][3].

# Com inicialização
bond int mat[2][3] : [[1; 2; 3]; [4; 5; 6]].

# Acesso
bglh int val : mat[0][1].
```

### Acesso e Modificação

```tarbalo
# Leitura
bglh int primeiro : valores[0].

# Escrita
valores[1] : 99.
```

### Fatias (Slices)

```tarbalo
# Extrai sub-vetor de índice 0 até 2 (exclusivo)
bond int sub[] : slice(valores; 0; 2).
```

### Regra de Dimensões Mistas

Vetores multidimensionais devem ser **completamente estáticos** ou **completamente dinâmicos**. Não é permitido misturar:

```tarbalo
bond int a[2][3].     # Válido: ambas estáticas
bond int b[][].        # Válido: ambas dinâmicas
bond int c[][3].       # ERRO: mistura de dimensões
bond int d[3][].       # ERRO: mistura de dimensões
```

## Operadores

### Aritméticos

| Símbolo | Descrição          |
|---------|--------------------|
| `+`     | Soma               |
| `-`     | Subtração (ou unário negativo) |
| `*`     | Multiplicação      |
| `/`     | Divisão            |
| `//`    | Divisão inteira    |
| `%`     | Módulo             |

### Relacionais

| Símbolo | Descrição          |
|---------|--------------------|
| `<`     | Menor que          |
| `>`     | Maior que          |
| `<=`    | Menor ou igual     |
| `>=`    | Maior ou igual     |
| `=`     | Igual a            |
| `!=`    | Diferente de       |

### Lógicos

| Símbolo | Descrição          |
|---------|--------------------|
| `e`     | E lógico (AND)     |
| `ou`    | Ou lógico (OR)     |
| `xor`   | Ou exclusivo (XOR) |
| `nao`   | Negação (NOT)      |

### Concatenação

| Símbolo | Descrição                    |
|---------|------------------------------|
| `&`     | Concatenação de strings      |

```tarbalo
bglh txt saudacao : "Olá" & ", " & "mundo".
```

### Precedência (do menor para o maior)

1. `ou`
2. `&`
3. `xor`
4. `e`
5. `nao`
6. Relacionais (`<`, `>`, `<=`, `>=`, `=`, `!=`)
7. `+`, `-`
8. `*`, `/`, `//`, `%`
9. Unário `-`, literais, parênteses

## Controle de Fluxo

### Condicional: `sepa` / `vish` (if / else)

```tarbalo
bglh int x : 10.

sepa (x > 5) {
    manda("Maior que 5").
} vish {
    manda("Menor ou igual a 5").
}.
```

O `vish` é opcional:

```tarbalo
sepa (x > 0) {
    manda("Positivo").
}.
```

### Enquanto (while)

```tarbalo
bglh int i : 0.

enquanto (i < 5) {
    manda(i).
    i ++.
}.
```

### Faça-Enquanto (do-while)

```tarbalo
bglh int i : 0.

vamo {
    manda(i).
    i ++.
} enquanto (i < 5).
```

### Para (for)

```tarbalo
para (bglh int i : 0. i < 10. i ++) {
    manda(i).
}.
```

Com variável já declarada:

```tarbalo
bglh int i : 0.

para (i : 0. i < 10. i ++) {
    manda(i).
}.
```

### Para Cada (for-each)

```tarbalo
bond int nums[3] : [1; 2; 3].

cada (bglh int n em nums) {
    manda(n).
}.
```

### Escolha (switch/case)

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

- `caso (valor):` — cada caso com parênteses e dois-pontos
- `padrao:` — caso padrão (equivalente a `default`)
- `break` é automático (não há fall-through)

### `morreu` (break) e `dale` (continue)

```tarbalo
enquanto (VDD) {
    sepa (x = 5) {
        morreu.
    }.
    sepa (x = 3) {
        dale.
    }.
    manda(x).
}.
```

## Funções

### Declaração

```tarbalo
# Função com retorno
func int dobro(int n) {
    receba n * 2.
}.

# Função sem retorno (void)
func vazio mostrar(txt msg) {
    manda(msg).
}.

# Função com múltiplos parâmetros (separados por ponto)
func txt formatar(txt nome. int idade) {
    receba nome & " tem " & idade & " anos".
}.
```

### Chamada

```tarbalo
bglh int resultado : dobro(5).
mostrar("Olá").
bglh txt info : formatar("Maria"; 25).
```

### Sobrecarga

Funções podem ser sobrecarregadas (mesmo nome, tipos de parâmetros diferentes):

```tarbalo
func int dobrar(int x) {
    receba x * 2.
}.

func qbd dobrar(qbd x) {
    receba x * 2,0.
}.
```

## Registros (`regs`)

### Declaração

```tarbalo
regs Pessoa {
    bglh txt nome.
    bglh int idade.
}.
```

### Instanciação

```tarbalo
bglh Pessoa p : new Pessoa("Maria"; 25).
```

### Acesso a Campos

```tarbalo
manda(p::nome).
p::idade : 26.
```

### Registros Aninhados

```tarbalo
regs Endereco {
    bglh txt rua.
    bglh int numero.
}.

regs Pessoa {
    bglh txt nome.
    bglh Endereco endereco.
}.

bglh Pessoa p : new Pessoa("Maria"; new Endereco("Rua A"; 123)).
manda(p::endereco::rua).
```

## Enums (`cnjt`)

### Declaração

```tarbalo
cnjt Cor {
    Vermelho.
    Verde.
    Azul.
}.
```

### Com Valores Explícitos

```tarbalo
cnjt Status {
    Ativo : 1.
    Inativo : 0.
}.
```

### Uso

```tarbalo
bglh Cor favorita : Cor::Vermelho.

escolha (favorita) {
    caso (Cor::Vermelho):
        manda("Vermelho").
    caso (Cor::Verde):
        manda("Verde").
    padrao:
        manda("Outra cor").
}.
```

## Tratamento de Erros

```tarbalo
sonha {
    bglh int resultado : 10 / 0.
} deuruim (Erro err) {
    manda("Erro: " & err::mensagem).
}.
```

## Diretivas

Para incluir outros arquivos `.tarbalo`:

```tarbalo
bora
    usar "lib/bondlib.tarbalo".

    # seu código aqui
flw.
```

- As diretivas devem aparecer no início do programa, antes de qualquer comando
- O caminho é relativo ao diretório de execução
- Arquivos incluídos não podem ter suas próprias diretivas `usar`

## Comandos de E/S

### Saída: `manda`

```tarbalo
manda("Olá, mundo!").
manda(42).
bglh int x : 10.
manda("Valor: " & x).
```

### Entrada: `espia`

```tarbalo
bglh txt nome.
espia(nome).
manda("Olá, " & nome).

bglh int idade.
espia(idade).
```

## Comentários

```tarbalo
# Comentário de linha

/* Comentário
   de
   múltiplas linhas */
```

## Biblioteca Padrão (bondlib)

A biblioteca `bondlib.tarbalo` fornece funções utilitárias para manipulação de vetores. Pode ser incluída com `usar "lib/bondlib.tarbalo"`.

Todas as funções aceitam chamada genérica (ex: `ordenar(v)`) graças à sobrecarga automática.

### `tamanho(vetor)` — Tamanho do vetor

Retorna o número de elementos. Disponível nativamente para qualquer vetor.

### `slice(vetor; ini; fim)` — Extrair sub-vetor

Retorna um novo vetor com elementos do índice `ini` até `fim` (exclusivo).

```tarbalo
bond int v[5] : [10; 20; 30; 40; 50].
bond int sub[] : slice(v; 1; 3).    # [20; 30]
```

### `anexar(vetor; elem)` — Adicionar ao final

Retorna um novo vetor com o elemento adicionado ao final.

```tarbalo
bond int v[] : [1; 2; 3].
bond int v2[] : anexar(v; 4).       # [1; 2; 3; 4]
```

### `inserir(vetor; pos; elem)` — Inserir em posição

Retorna um novo vetor com o elemento inserido na posição indicada.

```tarbalo
bond int v[] : [1; 2; 3].
bond int v2[] : inserir(v; 1; 99).  # [1; 99; 2; 3]
```

### `remover(vetor; pos)` — Remover de posição

Retorna um novo vetor sem o elemento da posição indicada.

```tarbalo
bond int v[] : [1; 2; 3].
bond int v2[] : remover(v; 1).      # [1; 3]
```

### `ordenar(vetor)` — Ordenar in-place

Ordena o vetor original em ordem crescente (bubble sort). Não retorna novo vetor.

```tarbalo
bond int v[3] : [3; 1; 2].
ordenar(v).                         # v agora é [1; 2; 3]
```

### `inverter(vetor)` — Inverter in-place

Inverte a ordem dos elementos no vetor original.

```tarbalo
bond int v[3] : [1; 2; 3].
inverter(v).                        # v agora é [3; 2; 1]
```

### `redim(vetor; novoTamanho)` — Redimensionar

Retorna um vetor com o novo tamanho. Se menor, trunca; se maior, preenche com valores padrão.

```tarbalo
bond int v[3] : [1; 2; 3].
bond int v2[] : redim(v; 5).        # [1; 2; 3; 0; 0]
```

### Tipos Suportados

Cada função (exceto `tamanho`) está disponível para os 5 tipos: `int`, `qbd`, `txt`, `ltr`, `lgc`.

Exceção: `ordenar` não está disponível para `lgc` (não faz sentido ordenar booleanos).

### Slices 2D

As funções `slice2d` permitem extrair sub-matrizes com 4 parâmetros de intervalo:

```tarbalo
bond int mat[3][3] : [[1; 2; 3]; [4; 5; 6]; [7; 8; 9]].
bond int sub[][] : slice(mat; 0; 2; 0; 2).    # [[1; 2]; [4; 5]]
```
