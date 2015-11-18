/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

/**
 *
 * @author juancruz
 */
public enum listaCI {
    ADDINT,  //Arithmetic
    ADDFLOAT,
    MULTI,
    MULTF,
    DIVIDEI,
    DIVIDEF,
    MODI,
    MODF,
    MINUSI,
    MINUSF,
    GTR, // Relational
    LESS,
    LESS_EQ,
    GTR_EQ,
    EQ, // Equal
    NOT_EQ, 
    AND, // Conditional
    OR,
    ASSMNT, //Assigna
    NOT,
    MIN,
    RET,
    CALL,
    CALL_EXTERN,
    JUMP,
    JUMP_FALSE,
    BREAK,
    CONTINUE,
    PUSHI,
    PUSHF,
    LABEL;
    
}
