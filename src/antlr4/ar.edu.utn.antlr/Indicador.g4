/*
 * Grammar for natural languaje for Indicators expressions
 * To compile this you should have the antlr plugin for your IDE installed
 * or use the command line.
 * http://www.antlr.org/tools.html
 *
 * Configuration:
 * path: path/to/project/dds-inversiones/src/java
 * package: ar.utn.edu.dds.antlr
 * languaje: Java
 * visitors: check
 * listeners: uncheck
 */

//Grammar for index creation
grammar Indicador;

//Rules (parser)
indicador: expression EOF;

expression
    : termino #term
    | expression MULTIPLICATIVE_OP  expression #multiplicative_operation
    | expression ADDITIVE_OP expression #additive_operation
    ;

termino
    : terminal
    | raise_operation
    | signed_termino
    ;

terminal
    : between_parenthesis
    | function
    | primary
    ;

raise_operation
    : terminal RAISE_OP terminal
    | terminal RAISE_OP raise_operation
    ;

signed_termino
    : ADDITIVE_OP terminal
    | ADDITIVE_OP raise_operation
    ;

between_parenthesis: LPAR expression RPAR;

function
    : function_static
    | function_unary
    | function_binary
    ;

function_static: FUNC_STATIC LPAR RPAR;
function_unary: FUNC_UNARY LPAR expression RPAR;
function_binary: FUNC_BINARY LPAR expression COMMA expression RPAR;

primary
    : IND LPAR VAR RPAR
    | CUE LPAR VAR RPAR
    | DOUBLE
    ;

//Tokens (lexer)
FUNC_STATIC: PI_FUNC;
PI_FUNC: 'pi';

FUNC_UNARY: SQRT_FUNC;
SQRT_FUNC: 'sqrt';

FUNC_BINARY: ROOT_FUNC;
ROOT_FUNC: 'root';

IND: 'ind';
CUE: 'cue';

ASSIGN: '=';
LPAR: '(';
RPAR: ')';
COMMA: ',';

RAISE_OP: '^';
MULTIPLICATIVE_OP: '*' | '/';
ADDITIVE_OP: '+' | '-';

VAR: WORD ('_' WORD)? NUMBER?;
DOUBLE: NUMBER ('.' NUMBER)?;

NUMBER: DIGIT+;
DIGIT: ('0'..'9');
WORD: LETTER+;
LETTER: ('a'..'z' | 'A'..'Z');

WS: (' ' | '\n' | '\t' | '\r')+ -> skip;