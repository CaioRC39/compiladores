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
    | declaracaoEnum
    | declaracaoStruct
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
    | ID    // permite nomes de cnjt como tipo (ex: Cor, Dia)
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
            elemVetor (PONTIVIRGULA elemVetor)*
        )?
    FECHACOLCHETE
;

elemVetor:
    inicializacaoVetor
    | expressao
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

// -----------------------------------------------------------------------
// Conjuntos (enums)
// -----------------------------------------------------------------------

declaracaoEnum:
    ENUM tipoVariavel ID ABRECHAVE (cnjtElem PONTO)+ FECHACHAVE PONTO
;

cnjtElem:
    ID (ATRIBUICAO expressao)?
;

declaracaoStruct:
    STRUCT ID ABRECHAVE (declaracaoVariavel)+ FECHACHAVE PONTO
;

chamadaConstrutor:
    NEW ID ABREPARENTE argumentos? FECHAPARENTE
;

acessoElem:
    ID (dimensao)* (DOISPONTOS ID)+
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
    | cmdTente
    | cmdEscolha
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
        (acessoElem | variavel)
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
    (acessoElem | variavel) (operadorAtribuicaoComposta | ATRIBUICAO) valorAtribuicao
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
    variavel (operadorAtribuicaoComposta | ATRIBUICAO) valorAtribuicao
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

cmdTente:
    TENTE ABRECHAVE bloco FECHACHAVE
    cmdPegue?
    PONTO
;

cmdPegue:
    PEGUE ABREPARENTE tipoVariavel ID FECHAPARENTE ABRECHAVE bloco FECHACHAVE
;

// -----------------------------------------------------------------------
// Escolha (switch/case)
// -----------------------------------------------------------------------

cmdEscolha:
    ESCOLHA ABREPARENTE expressao FECHAPARENTE ABRECHAVE
        (blocoCaso)+
        blocoPadrao?
    FECHACHAVE PONTO
;

blocoCaso:
    CASO ABREPARENTE expressao FECHAPARENTE ATRIBUICAO bloco
;

blocoPadrao:
    PADRAO ATRIBUICAO bloco
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
    expressaoConcatenacao (OU expressaoConcatenacao)*
;

// ---------- 2. Concatenação (abaixo de OU) ----------
expressaoConcatenacao:
    expressaoXor (CONCAT expressaoXor)*
;

// ---------- 3. Operador XOR ----------
expressaoXor:
    expressaoE (XOR expressaoE)*
;

// ---------- 4. Operador E ----------
expressaoE:
    expressaoNegacao (E expressaoNegacao)*
;

// ---------- 5. Negação lógica ----------
expressaoNegacao:
    NAO expressaoNegacao
    | expressaoRelacional
;

// ---------- 6. Operadores relacionais ----------
expressaoRelacional:
    expressaoAditiva (operadorRelacional expressaoAditiva)?
;

// ---------- 7. Adição / subtração ----------
expressaoAditiva:
    expressaoMultiplicativa ((MAIS | MENOS) expressaoMultiplicativa)*
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
    | acessoElem
    | variavel
    | chamadaFuncao
    | chamadaConstrutor
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
FACA            : 'vamo';
PARA            : 'para';
PARACADA        : 'cada';
EM              : 'em';
PARE            : 'morreu';
CONTINUAR       : 'dale';
VERDADEIRO      : 'VDD';
FALSO           : 'FAKE';
PACOTE          : 'usar';
ENUM            : 'cnjt';
TENTE           : 'sonha';
PEGUE           : 'deuruim';
ESCOLHA         : 'escolha';
CASO            : 'caso';
PADRAO          : 'padrao';
STRUCT          : 'regs';
NEW             : 'new';

// -----------------------------------------------------------------------
// B. Operadores
// -----------------------------------------------------------------------

SOMA_ATRIBUICAO      : ':+';
SUBTRACAO_ATRIBUICAO : ':-';
MULT_ATRIBUICAO      : ':*';
DIV_ATRIBUICAO       : ':/';
MOD_ATRIBUICAO       : ':%';
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
DOISPONTOS           : '::';
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