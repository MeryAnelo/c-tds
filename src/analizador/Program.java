/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

import java.util.LinkedList;

/**
 *
 * @author juancruz
 */
public class Program extends AST{
    private LinkedList<ClassDec> l;

    public Program(LinkedList<ClassDec> l) {
        this.l = l;
    }

    public LinkedList<ClassDec> getL() {
        return l;
    }

    public void setL(LinkedList<ClassDec> l) {
        this.l = l;
    }
    
    @Override
    public <T> T accept(ASTVisitor<T> v) {
            return v.visit(this);
    }
}
