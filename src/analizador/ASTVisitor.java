/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

/**
 *
 * @author alumno
 */
public interface ASTVisitor <T>{
    public abstract T visit(AssignStmt AssSt);
    public abstract T visit(BinOpExpr bOpExpr);
    public abstract T visit(Block bl);
    public abstract T visit(BoolLiteral bLit);
    public abstract T visit(BreakStmt breakSt);
    public abstract T visit(ClassDec clDec);
    public abstract T visit(ContinueStmt ConSt);
    public abstract T visit(Declaration dec);
    public abstract T visit(Expression expr);
    public abstract T visit(ExternStmt extSt);
    public abstract T visit(FieldDeclaration flDec);
    public abstract T visit(FloatLiteral fLit);
    public abstract T visit(ForStmt forSt);
    public abstract T visit(IfStmt ifSt);
    public abstract T visit(IntLiteral iLit);
    public abstract T visit(Literal lit);
    public abstract T visit(Location loc);
    public abstract T visit(LocationDeclaration locDec);
    public abstract T visit(Method meth);
    public abstract T visit(MethodCall mc);
    public abstract T visit(MethodCallExpr mcExpr);
    public abstract T visit(Parameter param);
    public abstract T visit(ReturnStmt ret);
    public abstract T visit(Statement stmt);
    public abstract T visit(UnaryOpExpr unOpExpr);
    public abstract T visit(VarLocation varLoc);
    public abstract T visit(WhileStmt whileSt);
    public abstract T visit(Program prog);
    
    
//    public abstract boolean endVisit(AssignOpType endVisit);
//    public abstract boolean endVisit(AssignStmt AssSt);
//    public abstract boolean endVisit(BinOpExpr bOpExpr);   
//    public abstract boolean endVisit(BinOpType bOpType);
//    public abstract boolean endVisit(Block bl);
//    public abstract boolean endVisit(BoolLiteral bLit);
//    public abstract boolean endVisit(BreakStmt breakSt);
//    public abstract boolean endVisit(ClassDec clDec);
//    public abstract boolean endVisit(ContinueStmt ConSt);
//    public abstract boolean endVisit(Declaration dec);
//    public abstract boolean endVisit(Expression expr);
//    public abstract boolean endVisit(ExternStmt extSt);
//    public abstract boolean endVisit(FieldDeclaration flDec);
//    public abstract boolean endVisit(FloatLiteral fLit);
//    public abstract boolean endVisit(ForStmt forSt);
//    public abstract boolean endVisit(IfStmt ifSt);
//    public abstract boolean endVisit(IntLiteral iLit);
//    public abstract boolean endVisit(Literal lit);
//    public abstract boolean endVisit(Location loc);
//    public abstract boolean endVisit(LocationDeclaration locDec);
//    public abstract boolean endVisit(Method meth);
//    public abstract boolean endVisit(MethodCall mc);
//    public abstract boolean endVisit(MethodCallExpr mcExpr);
//    public abstract boolean endVisit(Parameter param);
//    public abstract boolean endVisit(ReturnStmt ret);
//    public abstract boolean endVisit(Statement stmt);
//    public abstract boolean endVisit(Type type);
//    public abstract boolean endVisit(UnaryOpExpr unOpExpr);
//    public abstract boolean endVisit(UnaryOpType unOpType);
//    public abstract boolean endVisit(VarLocation varLoc);
//    public abstract boolean endVisit(WhileStmt whileSt);
}
