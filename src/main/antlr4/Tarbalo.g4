/* ======================================================================
   Gramática Tarbalo
   Linguagem imperativa com palavras‑chave em português.
   Bloco delimitado por prog ... fimprog.
   ====================================================================== */

grammar Tarbalo;

/* ======================================================================
   I. ESTRUTURA DO PROGRAMA
   ====================================================================== */

programa:
    INICIO
      bloco+
    FIM
    PONTO
    EOF
;

/* ======================================================================
   II. DECLARAÇÕES
   ====================================================================== */

declaracao:
    declaracaoVariavel
    | declaracaoVetor
    | declaracaoFuncao
;

// -----------------------------------------------------------------------
// Variáveis e vetores
// -----------------------------------------------------------------------

declaracaoVariavel:
    VARIAVEL tipoVariavel ID (ATRIBUICAO expressao)?
    PONTO
;

variavel:
    ID dimensaoVetor*          // ex.: a, vet[10], mat[2][3]
;

tipoVariavel:
    INTEIRO
    | DECIMAL
    | TEXTO_TIPO
    | CARACTERE
    | BOOLEANO_TIPO
;

tipoRetorno:
    tipoVariavel
    | VAZIO
;

dimensaoVetor:
    ABRECOLCHETE
        NUM?
    FECHACOLCHETE
;

declaracaoVetor:
    VETOR tipoVariavel ID dimensaoVetor+ (ATRIBUICAO inicializacaoVetor)?
    PONTO
;

inicializacaoVetor:
    ABRECOLCHETE
        (
            expressao (PONTIVIRGULA expressao)*
        )?
    FECHACOLCHETE
;

// -----------------------------------------------------------------------
// Funções
// -----------------------------------------------------------------------

declaracaoFuncao:
    FUNCAO tipoRetorno ID
    ABREPARENTE
        parametros?
    FECHAPARENTE
    ABRECHAVE
        bloco
    FECHACHAVE
    PONTO
;

parametros:
    parametro (PONTIVIRGULA parametro)*
;

parametro:
    tipoVariavel variavel
;

/* ======================================================================
   III. BLOCOS E COMANDOS
   ====================================================================== */

bloco:
    comando+
;

comando:
    declaracao
    | atribuicao
    | leitura
    | escrita
    | cmdSe
    | cmdEnquanto
    | cmdFacaEnquanto
    | cmdPara
    | retorno
    | pare
    | continuar
    | incrementoPonto
    | decrementoPonto
    | chamadaFuncao PONTO
;

// -----------------------------------------------------------------------
// 1. Entrada e saída
// -----------------------------------------------------------------------

leitura:
    LEIA
    ABREPARENTE
        selecaoVariavel
    FECHAPARENTE
    PONTO
;

escrita:
    ESCREVA
    ABREPARENTE
        (expressao (PONTIVIRGULA expressao)*)?
    FECHAPARENTE
    PONTO
;

// -----------------------------------------------------------------------
// 2. Atribuição e incrementos
// -----------------------------------------------------------------------

selecaoVariavel:
    ID
    | acessoVetor
;

atribuicao:
    selecaoVariavel (ATRIBUICAO | operadorAtribuicaoComposta) expressao
    PONTO
;

incremento:
    selecaoVariavel INCREMENTO
;

decremento:
    selecaoVariavel DECREMENTO
;

incrementoPonto:
    incremento
    PONTO
;
decrementoPonto:
    decremento
    PONTO
;

// -----------------------------------------------------------------------
// 3. Estruturas de controle
// -----------------------------------------------------------------------

cmdSe:
    SE
    ABREPARENTE
        expressao
    FECHAPARENTE
    ABRECHAVE
        bloco
    FECHACHAVE
    (SENAO
        ABRECHAVE
            bloco
        FECHACHAVE
    )?
    PONTO
;

cmdEnquanto:
    ENQUANTO
    ABREPARENTE
        expressao
    FECHAPARENTE
    ABRECHAVE
        bloco
    FECHACHAVE
    PONTO
;

cmdFacaEnquanto:
    FACA
    ABRECHAVE
        bloco
    FECHACHAVE
    ENQUANTO
    ABREPARENTE
        expressao
    FECHAPARENTE
    PONTO
;

cmdPara:
    PARA
    ABREPARENTE
      atribuicaoPara PONTIVIRGULA
      expressao PONTIVIRGULA
      atualizacaoPara
    FECHAPARENTE
    ABRECHAVE
        bloco
    FECHACHAVE
    PONTO
;

atribuicaoPara:
    selecaoVariavel (ATRIBUICAO | operadorAtribuicaoComposta) expressao
;

atualizacaoPara:
    incremento
    | decremento
    | atribuicaoPara
;

// -----------------------------------------------------------------------
// 4. Comandos de fluxo
// -----------------------------------------------------------------------

retorno:
    RETORNE expressao?
    PONTO
;

pare:
    PARE
    PONTO
;

continuar:
    CONTINUAR
    PONTO
;

/* ======================================================================
   IV. EXPRESSÕES
   ====================================================================== */

// ---------- 1. Operador OU ----------
expressao:
    expressaoXor (OU expressaoXor)*
;

// ---------- 2. Operador XOR ----------
expressaoXor:
    expressaoE (XOR expressaoE)*
;

// ---------- 3. Operador E ----------
expressaoE:
    expressaoNegacao (E expressaoNegacao)*
;

// ---------- 4. Negação lógica ----------
expressaoNegacao:
    NAO expressaoNegacao
    | expressaoRelacional
;

// ---------- 5. Operadores relacionais ----------
expressaoRelacional:
    expressaoAditiva (operadorRelacional expressaoAditiva)?
;

// ---------- 6. Adição / subtração / concatenação ----------
expressaoAditiva:
    expressaoMultiplicativa ((MAIS | MENOS | CONCAT) expressaoMultiplicativa)*
;

// ---------- 7. Multiplicação / divisão ----------
expressaoMultiplicativa:
    expressaoUnaria ((MULT | DIV | DIVINT | MOD) expressaoUnaria)*
;

// ---------- 8. Expressões unárias ----------
expressaoUnaria:
    MENOS expressaoUnaria
    | MAIS expressaoUnaria
    | operando
;

// ---------- 9. Operandos ----------
operando:
    NUM
    | NUMDEC
    | STRING
    | CHAR
    | VERDADEIRO
    | FALSO
    | ID
    | acessoVetor
    | chamadaFuncao
    | ABREPARENTE expressao FECHAPARENTE
;

// ---------- 10. Acesso a vetor ----------
acessoVetor:
    ID acessoDimensao+
;

acessoDimensao:
    ABRECOLCHETE
        expressao (PONTOPONTO expressao)?
    FECHACOLCHETE
;

// ---------- 11. Chamada de função ----------
chamadaFuncao:
    ID
    ABREPARENTE
        argumentos?
    FECHAPARENTE
;

argumentos:
    expressao (PONTIVIRGULA expressao)*
;

/* ======================================================================
   V. OPERADORES (símbolos compostos)
   ====================================================================== */

operadorRelacional:
    MENOR
    | MAIOR
    | MENORIGUAL
    | MAIORIGUAL
    | IGUAL
    | DIFERENTE
;

operadorAtribuicaoComposta:
    SOMA_ATRIBUICAO
    | SUBTRACAO_ATRIBUICAO
    | MULT_ATRIBUICAO
    | DIV_ATRIBUICAO
    | MOD_ATRIBUICAO
    | CONCAT_ATRIBUICAO
;

/* ======================================================================
   VI. LEXER
   ====================================================================== */

// -----------------------------------------------------------------------
// A. Palavras reservadas
// -----------------------------------------------------------------------

INICIO          : 'prog';
FIM             : 'fimprog';
VARIAVEL        : 'var';
VETOR           : 'vtr';
INTEIRO         : 'int';
DECIMAL         : 'dec';
CARACTERE       : 'car';
TEXTO_TIPO      : 'texto';
BOOLEANO_TIPO   : 'logico';
VAZIO           : 'vazio';
FUNCAO          : 'func';
RETORNE         : 'retorne';
LEIA            : 'leia';
ESCREVA         : 'escreva';
SE              : 'se';
SENAO           : 'senao';
ENQUANTO        : 'enquanto';
FACA            : 'faca';
PARA            : 'para';
PARE            : 'pare';
CONTINUAR       : 'continuar';
VERDADEIRO      : 'VDD';
FALSO           : 'FAKE';

// -----------------------------------------------------------------------
// B. Operadores
// -----------------------------------------------------------------------

SOMA_ATRIBUICAO      : '+:';
SUBTRACAO_ATRIBUICAO : '-:';
MULT_ATRIBUICAO      : '*:';
DIV_ATRIBUICAO       : '/:';
MOD_ATRIBUICAO       : '%:';
CONCAT_ATRIBUICAO    : '&:';
INCREMENTO           : '++';
DECREMENTO           : '--';
PONTOPONTO           : '..';
MAIS                 : '+';
MENOS                : '-';
MULT                 : '*';
DIVINT               : '//';
DIV                  : '/';
MOD                  : '%';
CONCAT               : '&';
ATRIBUICAO           : ':';
MENOR                : '<';
MAIOR                : '>';
MENORIGUAL           : '<=';
MAIORIGUAL           : '>=';
IGUAL                : '=';
DIFERENTE            : '!=';
NAO                  : 'nao';
E                    : 'e';
OU                   : 'ou';
XOR                  : 'xor';

// -----------------------------------------------------------------------
// C. Pontuação
// -----------------------------------------------------------------------

ABREPARENTE   : '(';
FECHAPARENTE  : ')';
ABRECHAVE     : '{';
FECHACHAVE    : '}';
ABRECOLCHETE  : '[';
FECHACOLCHETE : ']';
VIRGULA       : ',';
PONTIVIRGULA  : ';';
PONTO         : '.';

// -----------------------------------------------------------------------
// D. Literais
// -----------------------------------------------------------------------

NUMDEC:
    '0' ',' [0-9]+
    | [1-9][0-9]* ',' [0-9]+
;

NUM:
    '0'
    | [1-9][0-9]*
;

CHAR:
    '\'' ( ESCAPE_CHAR | ~['\\\r\n] ) '\''
;

fragment ESCAPE_CHAR:
    '\\' ( '\'' | '\\' | 'n' | 't' | 'r' )
;

STRING:
    '"' ( ESCAPE_STRING | ~["\\\r\n] )* '"'
;

fragment ESCAPE_STRING:
    '\\' ( '"' | '\\' | 'n' | 't' | 'r' )
;

// -----------------------------------------------------------------------
// E. Identificadores
// -----------------------------------------------------------------------

ID:
    [a-zA-Z_áàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ]
    [a-zA-Z_0-9áàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ]*
;

// -----------------------------------------------------------------------
// F. Comentários e espaços (ignorados)
// -----------------------------------------------------------------------

COMENTARIO_LINHA : '#' ~[\r\n]* -> skip;
COMENTARIO_BLOCO : '/*' .*? '*/' -> skip;
WS               : [ \t\r\n]+ -> skip;