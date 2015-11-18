/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

import java_cup.runtime.*;



public class VisitorPrint implements ASTVisitor <String>{


    @Override
    public String visit(AssignStmt AssSt) {
        return null;
    }

    @Override
    public String visit(BinOpExpr bOpExpr) {
        throw new UnsupportedOperationException("Not supported yet.2"); //To change body of generated methods, choose Tools | Templates.
    }

    public String visit(BinOpType bOpType) {
        throw new UnsupportedOperationException("Not supported yet.3"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(Block bl) {
        for (int i = 0; i < bl.getStatements().size(); i++) {
            bl.getStatements().get(i).accept(this);
        }
        return null;
    }

    @Override
    public String visit(BoolLiteral bLit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(BreakStmt breakSt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(ClassDec clDec) {
        clDec.getBody().getMethod().getFirst().accept(this);
        return null;
    }

    @Override
    public String visit(ContinueStmt ConSt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(Declaration dec) {
        dec.accept(this);
        return null;
    }

    @Override
    public String visit(Expression expr) {
        throw new UnsupportedOperationException("Not supported yet.4"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(ExternStmt extSt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(FieldDeclaration flDec) {
        throw new UnsupportedOperationException("Not supported yet.5"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(FloatLiteral fLit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(ForStmt forSt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(IfStmt ifSt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(IntLiteral iLit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(Literal lit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(Location loc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(LocationDeclaration locDec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(Method meth) {
        meth.getBlock().accept(this);
        return null;
    }

    @Override
    public String visit(MethodCall mc) {
        for (Expression e : mc.getlParam()) {
            System.out.println("Parametros: ");
            System.out.println("Nombre: "+e.toString()+"Tipo: "+e.getType());
        }
        return null;
    }

    @Override
    public String visit(MethodCallExpr mcExpr) {
        mcExpr.accept(this);
        return null;
    }

    @Override
    public String visit(Parameter param) {
        throw new UnsupportedOperationException("Not supported yet.6"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(ReturnStmt ret) {
        return null;
    }

    @Override
    public String visit(Statement stmt) {
        stmt.accept(this);
        return null;
    }

    public String visit(Type type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(UnaryOpExpr unOpExpr) {
        throw new UnsupportedOperationException("Not supported yet.8"); //To change body of generated methods, choose Tools | Templates.
    }

    public String visit(UnaryOpType unOpType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(VarLocation varLoc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(WhileStmt whileSt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String visit(Program prog) {
        //System.out.println(prog.getL().get(0).getId());
        prog.getL().getFirst().accept(this);
        return null;
    }
}
