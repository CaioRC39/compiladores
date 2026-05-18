grammar Tarbalo;

/*
|--------------------------------------------------------------------------
| PARSER
|--------------------------------------------------------------------------
*/

programa:
    INICIO
        declaracoes
        blocoPrincipal
    FIM
    PONTO
    EOF
;

/* --------------------------------------------------------------------- */
/* DECLARAÇÕES */
/* --------------------------------------------------------------------- */

declaracoes:
    declaracao*
;

declaracao:
    declaracaoVariavel
    | declaracaoFuncao
;

declaracaoVariavel:
    tipoVariavel variavel (VIRGULA variavel)*
    PONTO
;

variavel:
    ID dimensaoDeclaracao*
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

dimensaoDeclaracao:
    ABRECOLCHETE NUM FECHACOLCHETE
;

acessoVetor:
    ID acessoDimensao+
;

acessoDimensao:
    ABRECOLCHETE
        expressao
    FECHACOLCHETE
;

/* --------------------------------------------------------------------- */
/* FUNÇÕES */
/* --------------------------------------------------------------------- */

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

/* --------------------------------------------------------------------- */
/* BLOCO */
/* --------------------------------------------------------------------- */

blocoPrincipal:
    bloco
;

bloco:
    comando*
;

comando:
    declaracaoVariavel
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
    | incremento
    | decremento
    | chamadaFuncao PONTO
;

/* --------------------------------------------------------------------- */
/* ENTRADA E SAÍDA */
/* --------------------------------------------------------------------- */

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
        (
            expressao (PONTIVIRGULA expressao)*
        )?
    FECHAPARENTE
    PONTO
;

/* --------------------------------------------------------------------- */
/* ATRIBUIÇÕES */
/* --------------------------------------------------------------------- */

destinoAtribuicao:
    ID
    | acessoVetor
;

atribuicao:
    destinoAtribuicao
    (
        ATRIBUICAO
        | operadorAtribuicaoComposta 
    )
    expressao
    PONTO
;

incremento:
    destinoAtribuicao INCREMENTO
;

decremento:
    destinoAtribuicao DECREMENTO
;

/* --------------------------------------------------------------------- */
/* CONDICIONAIS */
/* --------------------------------------------------------------------- */

cmdSe:
    SE
    ABREPARENTE
        expressao
    FECHAPARENTE
    ABRECHAVE
        bloco
    FECHACHAVE
    (
        SENAO
        ABRECHAVE
            bloco
        FECHACHAVE
    )?
    PONTO
;

/* --------------------------------------------------------------------- */
/* REPETIÇÕES */
/* --------------------------------------------------------------------- */

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
        atribuicaoPara PONTO
        expressao PONTO
        atualizacaoPara
    FECHAPARENTE
    ABRECHAVE
        bloco
    FECHACHAVE
    PONTO
;

atribuicaoPara:
    destinoAtribuicao
    (
        ATRIBUICAO
        | operadorAtribuicaoComposta 
    )
    expressao
;

atualizacaoPara:
    incrementoPara
    | decrementoPara
    | atribuicaoPara
    | atribuicaoCompostaPara
;

incrementoPara:
    destinoAtribuicao INCREMENTO
;

decrementoPara:
    destinoAtribuicao DECREMENTO
;

atribuicaoCompostaPara:
    destinoAtribuicao operadorAtribuicaoComposta (variavel | NUM)
;

/* --------------------------------------------------------------------- */
/* CONTROLE */
/* --------------------------------------------------------------------- */

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

/* --------------------------------------------------------------------- */
/* EXPRESSÕES */
/* --------------------------------------------------------------------- */

expressao:
    expressaoOu
;

/* --------------------------------------------------------------------- */
/* OPERADOR OU */
/* --------------------------------------------------------------------- */

expressaoOu:
    expressaoXor
    (
        OU expressaoXor
    )*
;

/* --------------------------------------------------------------------- */
/* OPERADOR XOR */
/* --------------------------------------------------------------------- */

expressaoXor:
    expressaoE
    (
        XOR expressaoE
    )*
;

/* --------------------------------------------------------------------- */
/* OPERADOR E */
/* --------------------------------------------------------------------- */

expressaoE:
    expressaoNegacao
    (
        E expressaoNegacao
    )*
;

/* --------------------------------------------------------------------- */
/* OPERADOR NAO */
/* --------------------------------------------------------------------- */

expressaoNegacao:
    NAO expressaoNegacao
    | expressaoRelacional
;

/* --------------------------------------------------------------------- */
/* EXPRESSÕES RELACIONAIS */
/* --------------------------------------------------------------------- */

expressaoRelacional:
    expressaoAditiva
    (
        operadorRelacional
        expressaoAditiva
    )?
;

/* --------------------------------------------------------------------- */
/* EXPRESSÕES ADITIVAS */
/* --------------------------------------------------------------------- */

expressaoAditiva:
    expressaoMultiplicativa
    (
        (
            MAIS
            | MENOS
            | CONCAT
        )
        expressaoMultiplicativa
    )*
;

/* --------------------------------------------------------------------- */
/* EXPRESSÕES MULTIPLICATIVAS */
/* --------------------------------------------------------------------- */

expressaoMultiplicativa:
    expressaoUnaria
    (
        (
            MULT
            | DIV
            | DIVINT
            | MOD
        )
        expressaoUnaria
    )*
;

/* --------------------------------------------------------------------- */
/* EXPRESSÕES UNÁRIAS */
/* --------------------------------------------------------------------- */

expressaoUnaria:
    MENOS expressaoUnaria
    | MAIS expressaoUnaria
    | operando
;

/* --------------------------------------------------------------------- */
/* OPERANDOS */
/* --------------------------------------------------------------------- */

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

/* --------------------------------------------------------------------- */
/* FUNÇÕES */
/* --------------------------------------------------------------------- */

chamadaFuncao:
    ID
    ABREPARENTE
        argumentos?
    FECHAPARENTE
;

argumentos:
    expressao (PONTIVIRGULA expressao)*
;

/* --------------------------------------------------------------------- */
/* OPERADORES */
/* --------------------------------------------------------------------- */

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

/*
|--------------------------------------------------------------------------
| LEXER
|--------------------------------------------------------------------------
*/

/* --------------------------------------------------------------------- */
/* PALAVRAS RESERVADAS */
/* --------------------------------------------------------------------- */

INICIO:
    'prog'
;

FIM:
    'fimprog'
;

INTEIRO:
    'int'
;

DECIMAL:
    'dec'
;

CARACTERE:
    'car'
;

TEXTO_TIPO:
    'texto'
;

BOOLEANO_TIPO:
    'booleano'
;

VAZIO:
    'vazio'
;

FUNCAO:
    'func'
;

RETORNE:
    'retorne'
;

LEIA:
    'leia'
;

ESCREVA:
    'escreva'
;

SE:
    'se'
;

SENAO:
    'senao'
;

ENQUANTO:
    'enquanto'
;

FACA:
    'faca'
;

PARA:
    'para'
;

PARE:
    'pare'
;

CONTINUAR:
    'continue'
;

VERDADEIRO:
    'verdadeiro'
;

FALSO:
    'falso'
;

/* --------------------------------------------------------------------- */
/* OPERADORES */
/* --------------------------------------------------------------------- */

SOMA_ATRIBUICAO:
    '+:'
;

SUBTRACAO_ATRIBUICAO:
    '-:'
;

MULT_ATRIBUICAO:
    '*:'
;

DIV_ATRIBUICAO:
    '/:'
;

MOD_ATRIBUICAO:
    '%:'
;

INCREMENTO:
    '++'
;

DECREMENTO:
    '--'
;

MAIS:
    '+'
;

MENOS:
    '-'
;

MULT:
    '*'
;

DIVINT:
    '//'
;

DIV:
    '/'
;

MOD:
    '%'
;

CONCAT:
    '&'
;

ATRIBUICAO:
    ':'
;

MENOR:
    '<'
;

MAIOR:
    '>'
;

MENORIGUAL:
    '<='
;

MAIORIGUAL:
    '>='
;

IGUAL:
    '='
;

DIFERENTE:
    '!='
;

NAO:
    'nao'
;

E:
    'e'
;

OU:
    'ou'
;

XOR:
    'xor'
;

/* --------------------------------------------------------------------- */
/* PONTUAÇÃO */
/* --------------------------------------------------------------------- */

ABREPARENTE:
    '('
;

FECHAPARENTE:
    ')'
;

ABRECHAVE:
    '{'
;

FECHACHAVE:
    '}'
;

ABRECOLCHETE:
    '['
;

FECHACOLCHETE:
    ']'
;

VIRGULA:
    ','
;

PONTIVIRGULA:
    ';'
;

PONTO:
    '.'
;

/* --------------------------------------------------------------------- */
/* LITERAIS */
/* --------------------------------------------------------------------- */

NUMDEC:
    '0' ',' [0-9]+
    | [1-9][0-9]* ',' [0-9]+
;

NUM:
    '0'
    | [1-9][0-9]*
;

CHAR:
    '\'' (
        ESCAPE_CHAR
        | ~['\\\r\n]
    )
    '\''
;

fragment ESCAPE_CHAR:
    '\\'
    (
        '\''
        | '\\'
        | 'n'
        | 't'
        | 'r'
    )
;

STRING:
    '"'
    (
        ESCAPE_STRING
        | ~["\\\r\n]
    )*
    '"'
;

fragment ESCAPE_STRING:
    '\\'
    (
        '"'
        | '\\'
        | 'n'
        | 't'
        | 'r'
    )
;

/* --------------------------------------------------------------------- */
/* IDENTIFICADORES */
/* --------------------------------------------------------------------- */

ID:
    [a-zA-Z_áàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ]
    [a-zA-Z_0-9áàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ]*
;

/* --------------------------------------------------------------------- */
/* COMENTÁRIOS */
/* --------------------------------------------------------------------- */

COMENTARIO_LINHA:
    '#' ~[\r\n]* -> skip
;

COMENTARIO_BLOCO:
    '/*' .*? '*/' -> skip
;

/* --------------------------------------------------------------------- */
/* ESPAÇOS */
/* --------------------------------------------------------------------- */

WS:
    [ \t\r\n]+ -> skip
;