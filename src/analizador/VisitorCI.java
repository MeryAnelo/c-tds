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
public class VisitorCI implements ASTVisitor<Expression>{

    LinkedList<String> methodsExtern = new LinkedList();
    LinkedList<OperadorCI> li = new LinkedList();
    LinkedList<Pair> pila = new LinkedList();
    int count = 0;
    int Byte = 4;
    int offset = 0;
    
    public LinkedList<OperadorCI> getListaCI(){
        return li;
    }
    
    public void print(){
        for (int i = 0; i < li.size(); i++) {
            System.out.println(li.get(i).toString());
        }
    }
    @Override
    public Expression visit(AssignStmt AssSt) {
        Location loc = AssSt.getId();
        
        AssignOpType op = AssSt.getOperator();
        Expression expr = AssSt.getExpression().accept(this);
        switch (op) {
            case AUTOIN:
                offset -= Byte;
                loc.setOffset(offset);
                if (loc.type==Type.INT) {
                    li.add(new OperadorCI(listaCI.ADDINT, loc, expr, loc));
                }else{
                    li.add(new OperadorCI(listaCI.ADDFLOAT, loc, expr, loc));
                }
                break;
            case AUTODEC:
                offset -= Byte;
                loc.setOffset(offset);
                if (loc.getClass().equals(Integer.class)) {
                    li.add(new OperadorCI(listaCI.MINUSI, loc, expr, loc));
                }else{
                    li.add(new OperadorCI(listaCI.MINUSF, loc, expr, loc));
                }
                break;
            case ASSMNT:
                offset -= Byte;
                loc.setOffset(offset);
                Expression e=expr.accept(this);
                li.add(new OperadorCI(listaCI.ASSMNT, loc, e, null));
                break;
        }
        return null;
    }

    @Override
    public Expression visit(BinOpExpr bOpExpr) {
        BinOpType op = bOpExpr.getOperator();
        Expression left = bOpExpr.getLeftOperand().accept(this); // codigo de primer operando
        Expression right = bOpExpr.getRightOperand().accept(this); // codigo de segundo operando
        int id = count;
        count++;
        VarLocation var = new VarLocation("temp" + id,null);
        offset -= Byte;
        var.setOffset(offset);
        var.setType(left.getType());
        switch (op) {
            case MINUS:
                if (left.getClass().equals(Integer.class)) {
                    li.add(new OperadorCI(listaCI.MINUSI, left, right, var));
                }else{
                    li.add(new OperadorCI(listaCI.MINUSF, left, right, var));
                }
                break;
            case PLUS:
                if (left.getType()==Type.INT) {
                    li.add(new OperadorCI(listaCI.ADDINT, left, right, var));
                }else{
                    li.add(new OperadorCI(listaCI.ADDFLOAT, left, right, var));
                }
                break;
            case MULT:
                if (left.getClass().equals(Integer.class)) {
                    li.add(new OperadorCI(listaCI.MULTI, left, right, var));
                }else{
                    li.add(new OperadorCI(listaCI.MULTF, left, right, var));
                }
                break;
            case DIVIDE:
                if (left.getClass().equals(Integer.class)) {
                    li.add(new OperadorCI(listaCI.DIVIDEI, left, right, var));
                }else{
                    li.add(new OperadorCI(listaCI.DIVIDEF, left, right, var));
                }
                break;
            case DIV:
                if (left.getClass().equals(Integer.class)) {
                    li.add(new OperadorCI(listaCI.MODI, left, right, var));
                }else{
                    li.add(new OperadorCI(listaCI.MODF, left, right, var));
                }
                break;
            case AND:
                li.add(new OperadorCI(listaCI.AND, left, right, var));
                break;
            case OR:
                li.add(new OperadorCI(listaCI.OR, left, right, var));
                break;
            case LESS:
                li.add(new OperadorCI(listaCI.LESS, left, right, var));
                break;
            case LESS_EQ:
                li.add(new OperadorCI(listaCI.LESS_EQ, left, right, var));
                break;
            case GTR:
                li.add(new OperadorCI(listaCI.GTR, left, right, var));
                break;
            case GTR_EQ:
                li.add(new OperadorCI(listaCI.GTR_EQ, left, right, var));
                break;
            case EQ:
                li.add(new OperadorCI(listaCI.EQ, left, right, var));
                break;
            case NOT_EQ:
                li.add(new OperadorCI(listaCI.NOT_EQ, left, right, var));
                break;
        }
        return var;
    }

    @Override
    public Expression visit(Block bl) {
        /*para extern*/
        if (bl.getField()==null && bl.getStatements()==null) {
            ExternStmt ext=new ExternStmt();
            ext.accept(this);
        }
        int i =0;
        while(i < bl.getStatements().size()){
            if(bl.getStatements().get(i)!=null){
                bl.getStatements().get(i).accept(this);
            }
            i++;
        }
        return null;
    }

    @Override
    public Expression visit(BoolLiteral bLit) {
        return bLit;
    }

    @Override
    public Expression visit(BreakStmt breakSt) {
        li.add(new OperadorCI(listaCI.BREAK,null));
        li.add(new OperadorCI(listaCI.JUMP, pila.getFirst().getSnd()));
        return breakSt.getExpression();
    }

    @Override
    public Expression visit(ClassDec clDec) {
        clDec.getBody().accept(this);
        return null;
    }

    @Override
    public Expression visit(ContinueStmt ConSt) {
        li.add(new OperadorCI(listaCI.CONTINUE, null));
        li.add(new OperadorCI(listaCI.JUMP, pila.getFirst().getFst()));
        return ConSt.getExpression();
    }

    @Override
    public Expression visit(Declaration dec) {
        int i=0;
        while (i < dec.getMethod().size()){
            if (dec.getMethod().get(i).getBlock().getField()==null && dec.getMethod().get(i).getBlock().getStatements()==null){
                li.add(new OperadorCI(listaCI.LABEL,"Method Extern: "+dec.getMethod().get(i).getId()));
                dec.getMethod().get(i).setExtern(true);
                methodsExtern.add(dec.getMethod().get(i).getId());
            }else{
                li.add(new OperadorCI(listaCI.LABEL,"Method: "+dec.getMethod().get(i).getId()));
                dec.getMethod().get(i).accept(this);
                li.add(new OperadorCI(listaCI.LABEL,"End-Method: "+dec.getMethod().get(i).getId()));
            }
            i++;
        }
        return null;
    }

    @Override
    public Expression visit(Expression expr) {
        return null;
    }

    @Override
    public Expression visit(ExternStmt extSt) {
        return null;
    }

    @Override
    public Expression visit(FieldDeclaration flDec) {
        return null;
    }

    @Override
    public Expression visit(FloatLiteral fLit) {
        return fLit;
    }

    @Override
    public Expression visit(ForStmt forSt) {
        int auxW = count;
        li.add(new OperadorCI(listaCI.LABEL,"for"+auxW));
        count++;
        int auxE = count;
        Expression e;
        if(!(forSt.getForBlock().getClass().toString().contains("Expression"))){
            e=(Expression)new BinOpExpr(forSt.getCondition().accept(this),BinOpType.LESS,forSt.getForBlock());
        }else{
            e=forSt.getForBlock().accept(this);
        }
        li.add(new OperadorCI(listaCI.JUMP_FALSE, "end-for"+auxE,e));
        count++;
        
        pila.addFirst(new Pair("for"+auxW,"end-for"+auxE));
        if(forSt.getStatement()!=null){
            forSt.getStatement().accept(this);
        }
        li.add(new OperadorCI(listaCI.JUMP, "for"+auxW));
        li.add(new OperadorCI(listaCI.LABEL,"end-for"+auxE));
        pila.removeFirst();
        return null;
    }

    @Override
    public Expression visit(IfStmt ifSt) {
        VarLocation v = (VarLocation)ifSt.getCondition().accept(this);
        li.add(new OperadorCI(listaCI.JUMP_FALSE, "else-if"+count,v));
        Integer aux = count;
        count++;
        ifSt.getIfBlock().accept(this);
        li.add(new OperadorCI(listaCI.JUMP, "end-if"+count));
        Integer aux2 = count;
        count++;
        li.add(new OperadorCI(listaCI.LABEL, "else-if"+aux.toString()));
        if (ifSt.getElseBlock()!= null) {
            ifSt.getElseBlock().accept(this);
        }
        
        li.add(new OperadorCI(listaCI.LABEL, "end-if"+aux2));
        return null;
    }

    @Override
    public Expression visit(IntLiteral iLit) {
        return iLit;
    }

    @Override
    public Expression visit(Literal lit) {
        return lit;
    }

    @Override
    public Expression visit(Location loc) {
        
        return loc;
    }

    @Override
    public Expression visit(LocationDeclaration locDec) {
        return null;
    }

    @Override
    public Expression visit(Method meth) {
        meth.getBlock().accept(this);
        return null;
    }

    @Override
    public Expression visit(MethodCall mc) {
        LinkedList<Expression> l = mc.getlParam();
        Expression expr;
        for (int i = 0; i < l.size(); i++) {
            //System.out.println("Metodo: "+mc.getId()+" Parametro Nª: "+i+" Tamaño Parametro: "+l.size());
            expr = l.get(i);
            System.out.println("METODO: "+mc.getId()+" Parametro Nª: "+expr.toString()+" Tipo: "+expr.getType());
            if (expr.getType()==Type.INT || expr.getType()==Type.BOOLEAN) {
                System.out.println("Metodo: "+mc.getId()+" Push Nª: "+expr.toString());
                li.add(new OperadorCI(listaCI.PUSHI,expr,null,null));
            }else if(expr.getType()==Type.FLOAT){
                li.add(new OperadorCI(listaCI.PUSHF,expr,null,null));
            }
        }
        VarLocation res = new VarLocation("temp"+count,null);
        count++;
        offset -= Byte;
        res.setOffset(offset);
        if (methodsExtern.contains(mc.getId())) {
            li.add(new OperadorCI(listaCI.CALL_EXTERN, mc.getId(),res));
        }else{
            li.add(new OperadorCI(listaCI.CALL, mc.getId(),res));
        }
        return res;
    }

    @Override
    public Expression visit(MethodCallExpr mcExpr) {
        return mcExpr.getMethodCallExpr().accept(this);
    }

    @Override
    public Expression visit(Parameter param) {
        offset += Byte;
        param.getVar().setOffset(offset);
        return null;
    }

    @Override
    public Expression visit(ReturnStmt ret) {
        if (ret.getExpression() != null) {
            li.add(new OperadorCI(listaCI.RET, ret.getExpression().accept(this), null, null));
        } else {
            li.add(new OperadorCI(listaCI.RET, null, null, null));
        }
        return null;
    }

    @Override
    public Expression visit(Statement stmt) {
        stmt.accept(this);
        return null;
    }

    @Override
    public Expression visit(UnaryOpExpr unOpExpr) {
        int id = count;
        count++;
        UnaryOpType op = unOpExpr.getOperator();
        Expression e = unOpExpr.getLeftOperand().accept(this);
        VarLocation var = new VarLocation("t" + id,null);
        offset -= Byte;
        var.setOffset(offset);
        switch (op) {
            case NOT:
                li.add(new OperadorCI(listaCI.NOT, e, var, null));
            case MINUS:
                li.add(new OperadorCI(listaCI.MIN, e, var, null));
                break;
        }
        return var;
    }

    @Override
    public Expression visit(VarLocation varLoc) {
        offset -= Byte;
        varLoc.setOffset(offset);
        return varLoc;
    }

    @Override
    public Expression visit(WhileStmt whileSt) {
        int auxW = count;
        li.add(new OperadorCI(listaCI.LABEL,"while"+auxW));
        count++;
        VarLocation v = (VarLocation)whileSt.getCondition().accept(this);
        int auxE = count;
        li.add(new OperadorCI(listaCI.JUMP_FALSE, "end-while"+auxE,v));
        count++;
        pila.addFirst(new Pair("while"+auxW,"end-while"+auxE));
        whileSt.getStatement().accept(this);
        li.add(new OperadorCI(listaCI.JUMP, "while"+auxW));
        li.add(new OperadorCI(listaCI.LABEL,"end-while"+auxE));
        pila.removeFirst();
        return null;
    }

    @Override
    public Expression visit(Program prog) {
        int i =0;
        while (i < prog.getL().size()){
            prog.getL().get(i).accept(this);
            i++;
        }
        return null;
    }

}
