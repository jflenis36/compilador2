grammar RemiLang;

programa     : sentencia* EOF ;
sentencia    : imprimir ;

imprimir     : IMPRIMIR CADENA ;

IMPRIMIR     : 'IMPRIMIR' ;
CADENA       : '"' .*? '"' ;
WS           : [ \t\r\n]+ -> skip ;
