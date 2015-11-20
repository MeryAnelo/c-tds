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
    static LinkedList<Location> locations = new LinkedList();
    LinkedList<OperadorCI> li = new LinkedList();
    LinkedList<Pair> pila = new LinkedList();
    int count = 0;
    int Byte = 4;
    int offset = 0;
    
    public LinkedList<OperadorCI> getListaCI(){
        return li;
    }
    
    public static Location buscarLoc(String s){
        Location l=null;
        boolean encontre = false;
        for (int i = 0; i < locations.size() & !encontre; i++) {
            if (locations.get(i).getId().equals(s)) {
                encontre = true;
                l = locations.get(i);
            }
        }
        return l;
    }
    public void print(){
        for (int i = 0; i < li.size(); i++) {
            System.out.println(li.get(i).toString());
        }
//        for (int i = 0; i < locations.size(); i++) {
//            System.out.println(locations.get(i));
//        }
    }
    @Override
    public Expression visit(AssignStmt AssSt) {
//        int indice=-1;
        Location loc= AssSt.getId();;
//        if (locations.contains(AssSt.getId().getId())) {
//            indice = locations.indexOf(AssSt.getId());
//        }else{
//            loc = AssSt.getId();
//        }
//        
        AssignOpType op = AssSt.getOperator();
        Expression expr = AssSt.getExpression().accept(this);
        switch (op) {
            case AUTOIN:
                offset -= Byte;
                AssSt.getId().setOffset(offset);
                loc.setOffset(offset);
                loc=AssSt.getId();
                if (AssSt.getId().getType()==Type.INT) {
                    li.add(new OperadorCI(listaCI.ADDINT, AssSt.getId(), expr,loc));
                }else{
                    li.add(new OperadorCI(listaCI.ADDFLOAT, AssSt.getId(), expr, loc));
                }
                break;
            case AUTODEC:
                offset -= Byte;
                AssSt.getId().setOffset(offset);
                loc.setOffset(offset);
                loc=AssSt.getId();
                if (AssSt.getId().getType()==Type.INT) {
                    li.add(new OperadorCI(listaCI.MINUSI, AssSt.getId(), expr, loc));
                }else{
                    li.add(new OperadorCI(listaCI.MINUSF, AssSt.getId(), expr, loc));
                }
                break;
            case ASSMNT:
                offset -= Byte;
                AssSt.getId().setOffset(offset);
                loc.setOffset(offset);
                Expression e=expr.accept(this);
                li.add(new OperadorCI(listaCI.ASSMNT, AssSt.getId(), e, null));
                break;
        }
        locations.add(loc);
//        System.out.println("1 Var: "+AssSt.getId().getId()+", OffSet: "+AssSt.getId().getOffset());
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
                if (left.getType()==Type.INT) {
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
                if (left.getType()==Type.INT) {
                    li.add(new OperadorCI(listaCI.MULTI, left, right, var));
                }else{
                    li.add(new OperadorCI(listaCI.MULTF, left, right, var));
                }
                break;
            case DIVIDE:
                if (left.getType()==Type.INT) {
                    li.add(new OperadorCI(listaCI.DIVIDEI, left, right, var));
                }else{
                    li.add(new OperadorCI(listaCI.DIVIDEF, left, right, var));
                }
                break;
            case DIV:
                if (left.getType()==Type.INT) {
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
        locations.add(var);
//        System.out.println("2 Var: "+var.getId()+", OffSet: "+var.getOffset());
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
                li.add(new OperadorCI(listaCI.LABEL,"Extern: "+dec.getMethod().get(i).getId()+":"));
                dec.getMethod().get(i).setExtern(true);
                methodsExtern.add(dec.getMethod().get(i).getId());
            }else{
                li.add(new OperadorCI(listaCI.LABEL,dec.getMethod().get(i).getId()+":"));
                dec.getMethod().get(i).accept(this);
                li.add(new OperadorCI(listaCI.LABEL,"End_Method: "+dec.getMethod().get(i).getId()));
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
        li.add(new OperadorCI(listaCI.JUMP_FALSE, "end_for: "+auxE,e));
        count++;
        
        pila.addFirst(new Pair("for"+auxW,"end_for: "+auxE));
        if(forSt.getStatement()!=null){
            forSt.getStatement().accept(this);
        }
        li.add(new OperadorCI(listaCI.JUMP, "for"+auxW));
        li.add(new OperadorCI(listaCI.LABEL,"end_for"+auxE+":"));
        pila.removeFirst();
        return null;
    }

    @Override
    public Expression visit(IfStmt ifSt) {
        VarLocation v = (VarLocation)ifSt.getCondition().accept(this);
        li.add(new OperadorCI(listaCI.JUMP_FALSE, "else_if"+count,v));
        Integer aux = count;
        count++;
        ifSt.getIfBlock().accept(this);
        li.add(new OperadorCI(listaCI.JUMP, "end_if"+count));
        Integer aux2 = count;
        count++;
        li.add(new OperadorCI(listaCI.LABEL, "else_if"+aux.toString()+":"));
        if (ifSt.getElseBlock()!= null) {
            ifSt.getElseBlock().accept(this);
        }
        
        li.add(new OperadorCI(listaCI.LABEL, "end_if"+aux2+":"));
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
        locations.add(loc);
//        System.out.println("3 Var: "+loc.getId()+", OffSet: "+loc.getOffset());
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
            //System.out.println("METODO: "+mc.getId()+" Parametro Nª: "+expr.toString()+" Tipo: "+expr.getType());
            Location location = buscarLoc(expr.toString());
            //System.out.println(location.toString()+" OffSet:"+location.getOffset());
            if (expr.getType()==Type.INT || expr.getType()==Type.BOOLEAN) {
                expr.setType(Type.INT);
                System.out.println("Metodo: "+mc.getId()+" Push Nª: "+expr.toString());
                Location loc = locations.get(0);
                li.add(new OperadorCI(listaCI.PUSHI,expr,location,null));
            }else if(expr.getType()==Type.FLOAT){
                li.add(new OperadorCI(listaCI.PUSHF,expr,location,null));
            }
        }
        VarLocation res = new VarLocation("temp"+count,null);
        count++;
        offset -= Byte;
        res.setOffset(offset);
        li.add(new OperadorCI(listaCI.CALL, mc.getId(),res));
        locations.add(res);
//        System.out.println("4 Var: "+res.getId()+", OffSet: "+res.getOffset());
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
        locations.add(var);
        //System.out.println("5 Var: "+var.getId()+", OffSet: "+var.getOffset());
        return var;
    }

    @Override
    public Expression visit(VarLocation varLoc) {
        int i =0;
        for (int j = 0; j < locations.size(); j++) {
            if (locations.get(j).getId().equals(varLoc.getId())) {
                i = j;
            }
        }
        varLoc.setOffset(locations.get(i).getOffset());
        return varLoc;
    }

    @Override
    public Expression visit(WhileStmt whileSt) {
        int auxW = count;
        li.add(new OperadorCI(listaCI.LABEL,"while"+auxW));
        count++;
        VarLocation v = (VarLocation)whileSt.getCondition().accept(this);
        int auxE = count;
        li.add(new OperadorCI(listaCI.JUMP_FALSE, "end_while"+auxE,v));
        count++;
        pila.addFirst(new Pair("while"+auxW,"end_while"+auxE+":"));
        whileSt.getStatement().accept(this);
        li.add(new OperadorCI(listaCI.JUMP, "while"+auxW));
        li.add(new OperadorCI(listaCI.LABEL,"end_while"+auxE+":"));
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
