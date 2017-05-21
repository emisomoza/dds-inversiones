/*
 * Grammar for natural languaje for Indicators expressions
 * To compile this you should have the antlr plugin for your IDE installed
 * or use the command line.
 * http://www.antlr.org/tools.html
 */

grammar Indicador;

@header {package ar.utn.edu.dds.antlr;}

//Rules
expression
    : term OP_ADITIVO expression
    | term
    ;

term
    : primary OP_MULTIPLICATIVO expression
    | primary
    ;

primary
    : LPAR expression RPAR
    | ID
    | NUMBER
    ;

//Tokes
OP_ADITIVO
    : '+'
    | '-'
    ;

OP_MULTIPLICATIVO
    : '*'
    | '/'
    ;

LPAR: '(';
RPAR: ')';
ID: [a-zA-Z]+('_'[a-zA-Z]+)*;
NUMBER: [0-9]+(.[0-9]+)?;
WS: (' ' | '\n' | '\t' | '\r')+ -> skip;