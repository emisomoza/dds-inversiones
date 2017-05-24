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
expression
    : LPAR expression RPAR #between_parenthesis
    | signed_primary ADDITIVE_OP expression #additive_operation
    | ADDITIVE_OP expression RAISE_OP expression #signed_raise_operation
    | signed_expression #signed_exp
    | expression RAISE_OP expression #raise_operation
    | expression MULTIPLICATIVE_OP expression #multiplicative_operation
    | expression ADDITIVE_OP expression #additive_operation
    | function #func
    | signed_primary #signed_prim
    | primary #prim
    ;

signed_primary: ADDITIVE_OP primary;
//signed_raise_operation: ADDITIVE_OP expression RAISE_OP expression;
signed_expression: ADDITIVE_OP expression;

function
    : function_static
    | function_unary
    | function_binary
    ;
function_static: FUNC_STATIC LPAR RPAR;
function_unary: FUNC_UNARY LPAR expression RPAR;
function_binary: FUNC_BINARY LPAR expression COMMA expression RPAR;

primary
    : VAR
    | DOUBLE
    ;

//Tokens (lexer)
FUNC_STATIC: PI_FUNC;
PI_FUNC: 'pi';

FUNC_UNARY: SQRT_FUNC;
SQRT_FUNC: 'sqrt';

FUNC_BINARY: ROOT_FUNC;
ROOT_FUNC: 'root';

VAR: WORD ('_' WORD)? NUMBER?;
DOUBLE: NUMBER ('.' NUMBER)?;

NUMBER: DIGIT+;
DIGIT: ('0'..'9');
WORD: LETTER+;
LETTER: ('a'..'z' | 'A'..'Z');

ASSIGN: '=';
LPAR: '(';
RPAR: ')';
COMMA: ',';

RAISE_OP: '^';
MULTIPLICATIVE_OP: '*' | '/';
ADDITIVE_OP: '+' | '-';

WS: (' ' | '\n' | '\t' | '\r')+ -> skip;