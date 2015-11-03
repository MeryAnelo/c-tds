package analizador;
import java_cup.runtime.*;

%%
%class Lexer
%state COMENTARIO
%line
%column
%cup


/*-*
 * PATTERN DEFINITIONS:
 */
letter          = [A-Za-z]
digit           = [0-9]
alphanumeric    = {letter}|{digit}
other_id_char   = [_]
integer         = -?{digit}{digit}*
real            = {integer}\.{digit}+
identifier      = {letter}({alphanumeric}|{other_id_char})*


LineTerminator = \r|\n|\r\n
whitespace     = {LineTerminator}|[ \t\f]

/* comments */
comment = {TraditionalComment}|{EndOfLineComment}|{DocumentationComment}

TraditionalComment   = "/*"[^*]~"*/"
EndOfLineComment     = "//".*
DocumentationComment = "/**"{CommentContent}"*"+ "/"
CommentContent       = ([^*]|\*+[^/*])*


%%

/**
 * LEXICAL RULES:
 */

"&&"            { return new Symbol(sym.AND,yyline+1,yycolumn+1,yytext()); }
while		{ return new Symbol(sym.WHILE,yyline+1,yycolumn+1,yytext()); }
"{"             { return new Symbol(sym.BEGIN,yyline+1,yycolumn+1,yytext()); }
else            { return new Symbol(sym.ELSE,yyline+1,yycolumn+1,yytext()); }
"}"             { return new Symbol(sym.END,yyline+1,yycolumn+1,yytext()); }
if              { return new Symbol(sym.IF,yyline+1,yycolumn+1,yytext()); }
for             { return new Symbol(sym.FOR,yyline+1,yycolumn+1,yytext()); }
"||"            { return new Symbol(sym.OR,yyline+1,yycolumn+1,yytext()); }
class           { return new Symbol(sym.CLASS,yyline+1,yycolumn+1,yytext()); }
void		{ return new Symbol(sym.VOID,yyline+1,yycolumn+1,yytext()); }
break           { return new Symbol(sym.BREAK,yyline+1,yycolumn+1,yytext()); }
true		{ return new Symbol(sym.boolLiteral,yyline+1,yycolumn+1,new Boolean(yytext())); }
false		{ return new Symbol(sym.boolLiteral,yyline+1,yycolumn+1,new Boolean(yytext())); }
boolean       	{ return new Symbol(sym.BOOLEAN,yyline+1,yycolumn+1); }
int		{ return new Symbol(sym.INT,yyline+1,yycolumn+1); }
float	        { return new Symbol(sym.FLOAT,yyline+1,yycolumn+1); }


continue	{ return new Symbol(sym.CONTINUE,yyline+1,yycolumn+1,yytext()); }
return          { return new Symbol(sym.RET,yyline+1,yycolumn+1,yytext()); }
extern		{ return new Symbol(sym.EXTERN,yyline+1,yycolumn+1,yytext()); }


"*"             { return new Symbol(sym.MULT,yyline+1,yycolumn+1,yytext()); }
"+"             { return new Symbol(sym.PLUS,yyline+1,yycolumn+1,yytext()); }
"-"             { return new Symbol(sym.MINUS,yyline+1,yycolumn+1,yytext()); }
"/"             { return new Symbol(sym.DIVIDE,yyline+1,yycolumn+1,yytext()); }
"%"		{ return new Symbol(sym.DIV,yyline+1,yycolumn+1,yytext()); }
";"             { return new Symbol(sym.SEMI,yyline+1,yycolumn+1,yytext()); }
","             { return new Symbol(sym.COMMA,yyline+1,yycolumn+1,yytext()); }
"("             { return new Symbol(sym.LEFT_P,yyline+1,yycolumn+1,yytext()); }
")"             { return new Symbol(sym.RT_P,yyline+1,yycolumn+1,yytext()); }
"["             { return new Symbol(sym.LEFT_B,yyline+1,yycolumn+1,yytext()); }
"]"             { return new Symbol(sym.RT_B,yyline+1,yycolumn+1,yytext()); }
"="		{ return new Symbol(sym.ASSMNT,yyline+1,yycolumn+1,yytext()); }
"=="            { return new Symbol(sym.EQ,yyline+1,yycolumn+1,yytext()); }
">"             { return new Symbol(sym.GTR,yyline+1,yycolumn+1,yytext()); }
"<"             { return new Symbol(sym.LESS,yyline+1,yycolumn+1,yytext()); }
"<="            { return new Symbol(sym.LESS_EQ,yyline+1,yycolumn+1,yytext()); }
">="            { return new Symbol(sym.GTR_EQ,yyline+1,yycolumn+1,yytext()); }
"!"             { return new Symbol(sym.NOT,yyline+1,yycolumn+1,yytext()); }
"!="            { return new Symbol(sym.NOT_EQ,yyline+1,yycolumn+1,yytext()); }
"+="		{ return new Symbol(sym.AUTOIN,yyline+1,yycolumn+1,yytext()); }
"-="            { return new Symbol(sym.AUTODEC,yyline+1,yycolumn+1,yytext()); }
"."             { return new Symbol(sym.DOT,yyline+1,yycolumn+1,yytext()); }
{integer}      	{return new Symbol(sym.intLiteral,yyline+1,yycolumn+1,new Integer(yytext())); }
{real}         	{return new Symbol(sym.floatLiteral,yyline+1,yycolumn+1,new Float(yytext())); }
{identifier}    {return new Symbol(sym.IDENT,yyline+1,yycolumn+1,yytext()); }
"/*"            {yybegin(COMENTARIO);}
<COMENTARIO>{
        /*este token hace que se regrese al YYINITIAL*/
        [^*]*   {System.out.println("¡Error! Unclosed comment");}
        "*/"    { yybegin(YYINITIAL);}
}
{comment}       { /* For this stand-alone lexer, print out comments. */ }
{whitespace}    { /* Ignore whitespace. */ }
.               { System.out.println("Illegal char, '" + yytext() +
                    "' line: " + (yyline+1) + ", column: " + (yycolumn+1)); }


