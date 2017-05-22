/*
 * Grammar for natural languaje for Indicators expressions
 * To compile this you should have the antlr plugin for your IDE installed
 * or use the command line.
 * http://www.antlr.org/tools.html
 */

grammar Indicador;

@header {package ar.utn.edu.dds.antlr;}

//Rules
termAdd
    : termMult OP_ADD termAdd
    | termMult
    ;

termMult
    : termPow OP_MULT termAdd
    | termPow
    ;

termPow
    : primary OP_POW termAdd
    | SQRT LPAR termAdd RPAR
    | RT LPAR termAdd COMMA termAdd RPAR
    | primary
    ;

primary
    : LPAR termAdd RPAR
    | ID
    | NUMBER
    ;

//Tokes
OP_ADD
    : '+'
    | '-'
    ;

OP_MULT
    : '*'
    | '/'
    ;

OP_POW: '^';
SQRT: 'sqrt';
RT: 'rt';
COMMA: ',';
LPAR: '(';
RPAR: ')';
ID: [a-zA-Z]+('_'[a-zA-Z]+)*;
NUMBER: [0-9]+('.'[0-9]+)?;
WS: (' ' | '\n' | '\t' | '\r')+ -> skip;