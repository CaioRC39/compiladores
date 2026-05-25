/* ======================================================================
   Gramática Tarbalo
   Linguagem imperativa com palavras‑chave em português.
   ====================================================================== */


grammar Tarbalo;

/* ======================================================================
   I. ESTRUTURA DO PROGRAMA
   ====================================================================== */

programa:
    INICIO
        (diretiva)*
        (bloco)+
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
    ID dimensao*               // ex.: a, vet[10], mat[2][3]
;

tipoVariavel:
    INTEIRO
    | DECIMAL
    | TEXTO_TIPO
    | CARACTERE
    | BOOLEANO_TIPO
;

tipoComposto:
    tipoVariavel dimensao*
;

tipoRetorno:
    tipoComposto
    | VAZIO
;

// Regra unificada para dimensões de vetores/arrays
dimensao:
    ABRECOLCHETE
        (expressao (PONTOPONTO expressao)?)?
    FECHACOLCHETE
;

declaracaoVetor:
    VETOR tipoVariavel ID dimensao+ (ATRIBUICAO valorAtribuicao)?
    PONTO
;

inicializacaoVetor:
    ABRECOLCHETE
        (
            expressao (PONTIVIRGULA expressao)*
        )?
    FECHACOLCHETE
;

valorAtribuicao:
    expressao
    | inicializacaoVetor
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
    parametro (PONTO parametro)*
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
    | cmdParaCada
    | cmdBloco PONTO
    | retorno
    | pare
    | continuar
    | incrementoPonto
    | decrementoPonto
    | chamadaFuncao PONTO
;

cmdBloco:
    ABRECHAVE bloco FECHACHAVE
;

diretiva:
    PACOTE STRING
    PONTO
;

// -----------------------------------------------------------------------
// 1. Entrada e saída
// -----------------------------------------------------------------------

leitura:
    LEIA
    ABREPARENTE
        variavel
    FECHAPARENTE
    PONTO
;

escrita:
    ESCREVA
    ABREPARENTE
        expressao?
    FECHAPARENTE
    PONTO
;

// -----------------------------------------------------------------------
// 2. Atribuição e incrementos
// -----------------------------------------------------------------------

atribuicao:
    variavel (ATRIBUICAO | operadorAtribuicaoComposta) valorAtribuicao
    PONTO
;

incremento:
    variavel INCREMENTO
;

decremento:
    variavel DECREMENTO
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
      inicializacaoPara PONTO
      expressao PONTO
      atualizacaoPara
    FECHAPARENTE
    ABRECHAVE
        bloco
    FECHACHAVE
    PONTO
;

inicializacaoPara:
    atribuicaoPara
    | VARIAVEL tipoVariavel ID ATRIBUICAO expressao
;

atribuicaoPara:
    variavel (ATRIBUICAO | operadorAtribuicaoComposta) valorAtribuicao
;

atualizacaoPara:
    incremento
    | decremento
    | atribuicaoPara
;

cmdParaCada:
    PARACADA
    ABREPARENTE
        VARIAVEL tipoVariavel ID EM expressao
    FECHAPARENTE
    ABRECHAVE
        bloco
    FECHACHAVE
    PONTO
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

// ---------- 6. Adição / subtração ----------
expressaoAditiva:
    expressaoConcatenacao ((MAIS | MENOS) expressaoConcatenacao)*
;

// ---------- 7. Concatenação ----------
expressaoConcatenacao:
    expressaoMultiplicativa (CONCAT expressaoMultiplicativa)*
;

// ---------- 8. Multiplicação / divisão ----------
expressaoMultiplicativa:
    operando ((MULT | DIV | DIVINT | MOD) operando)*
;

// ---------- 9. Operandos ----------
operando:
    NUM
    | NUMDEC
    | STRING
    | CHAR
    | VERDADEIRO
    | FALSO
    | variavel
    | chamadaFuncao
    | ABREPARENTE expressao FECHAPARENTE
    | MENOS operando  // unário negativo
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
;

/* ======================================================================
   VI. LEXER
   ====================================================================== */

// -----------------------------------------------------------------------
// A. Palavras reservadas
// -----------------------------------------------------------------------

INICIO          : 'bora';
FIM             : 'flw';
VARIAVEL        : 'bglh';
VETOR           : 'bond';
INTEIRO         : 'int';
DECIMAL         : 'qbd';
CARACTERE       : 'ltr';
TEXTO_TIPO      : 'txt';
BOOLEANO_TIPO   : 'lgc';
VAZIO           : 'vazio';
FUNCAO          : 'func';
RETORNE         : 'receba';
LEIA            : 'espia';
ESCREVA         : 'manda';
SE              : 'sepa';
SENAO           : 'vish';
ENQUANTO        : 'enquanto';
FACA            : 'faca';
PARA            : 'para';
PARACADA        : 'pancada';
EM              : 'em';
PARE            : 'morre';
CONTINUAR       : 'dale';
VERDADEIRO      : 'VDD';
FALSO           : 'FAKE';
PACOTE          : 'usar';

// -----------------------------------------------------------------------
// B. Operadores
// -----------------------------------------------------------------------

SOMA_ATRIBUICAO      : '+:';
SUBTRACAO_ATRIBUICAO : '-:';
MULT_ATRIBUICAO      : '*:';
DIV_ATRIBUICAO       : '/:';
MOD_ATRIBUICAO       : '%:';
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
MENORIGUAL           : '<=';
MAIORIGUAL           : '>=';
DIFERENTE            : '!=';
MENOR                : '<';
MAIOR                : '>';
IGUAL                : '=';
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