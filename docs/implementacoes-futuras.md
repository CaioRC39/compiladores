# Implementações Futuras

## 1. Sobrecarga de funções em `bondlib`

Verificar a viabilidade de implementar sobrecarga automática das funções presentes em `bondlib`, permitindo que o código escrito em `tarbalo` não precise especificar o tipo no nome da função.

### Exemplo atual

```tarbalo
ordenar_int(v).
```

### Objetivo desejado

```tarbalo
ordenar(v).
```

Nesse caso, o analisador semântico deveria:

* identificar automaticamente o tipo de `v`;
* verificar se `v` é compatível com alguma assinatura existente;
* validar restrições adicionais (ex.: vetor possuir ao menos dois elementos);
* resolver automaticamente a chamada correta (`ordenar_int`, `ordenar_real`, etc.).

Caso seja viável, implementar um mecanismo de resolução de sobrecarga baseado em tipos e assinaturas.

---

## 2. Separação correta de responsabilidades entre Transpilador e Analisador Semântico

O Transpilador ainda executa validações que deveriam ser responsabilidade exclusiva do AnalisadorSemântico. Refatorar os seguintes pontos:

### 2.1 Verificação de argumentos em `visitChamadaFuncao`

Atualmente, o Transpilador verifica se `tamanho` recebeu argumentos:

```java
if (ctx.argumentos() == null || ctx.argumentos().expressao().isEmpty()) {
    erro(ctx.start.getLine(), "tamanho requer um argumento");
    return "";
}
```

Essa validação deve ser movida para o AnalisadorSemântico.

---

### 2.2 Fallback silencioso para funções não resolvidas

Trecho atual:

```java
if (fun != null) {
    return fun.getNome() + "(" + argumentos + ")";
}

// Fallback (não deve ocorrer)
return nomeFunc + "(" + argumentos + ")";
```

Se `analisador.obterResolucao(ctx)` retornar `null`, isso deve gerar erro semântico de função não declarada. O Transpilador não deve gerar código em situações inválidas.

---

### 2.3 Remover sistema duplicado de erros no Transpilador

O Transpilador mantém:

* variável `erros`;
* método `erro()`;
* emissão própria de mensagens.

Idealmente:

* apenas o AnalisadorSemântico deve validar programas;
* o Transpilador deve assumir que a árvore recebida já está semanticamente correta;
* o Transpilador deve apenas gerar código.

---

## 3. Alteração da função `manda`

Modificar a função de escrita `manda` para aceitar concatenação usando `&` ao invés de separadores com `;`.

### Exemplo desejado

```tarbalo
manda("Valor: " & x & "\n").
```

---

## 4. Melhorias futuras na linguagem

### 4.1 Tipos compostos (registros/structs)

Adicionar suporte a tipos compostos:

```tarbalo
tipo Pessoa {
    texto nome;
    int idade;
}
```

Objetivo:

* agrupar dados relacionados;
* facilitar modelagem de entidades;
* permitir maior organização semântica.

---

### 4.2 Enums

Adicionar suporte a enums:

```tarbalo
enum Dias { SEG, TER, QUA, QUI, SEX }
```

Objetivo:

* representar conjuntos discretos de valores;
* melhorar legibilidade e segurança semântica.

---

### 4.3 Tratamento de exceções

Adicionar mecanismo de tratamento de exceções:

```tarbalo
tente {
    ...
} senao {
    ...
}
```

Objetivo:

* capturar falhas em tempo de execução;
* permitir recuperação controlada de erros.

---

## 5. Validação de limites (edge cases)

Adicionar validações rigorosas nas funções utilitárias de arrays:

### `inserir_*`

Lançar `ArrayIndexOutOfBoundsException` quando:

* `pos < 0`
* `pos > tamanho(arr)`

---

### `remover_*`

Lançar `ArrayIndexOutOfBoundsException` quando:

* `pos < 0`
* `pos >= tamanho(arr)`

---

### `slice1d_*`

Lançar `ArrayIndexOutOfBoundsException` quando:

* `ini < 0`
* `fimExcl > tamanho(arr)`

---

### `redim_*`

Lançar `NegativeArraySizeException` quando:

* `novoTam < 0`
