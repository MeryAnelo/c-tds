/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

import java.util.LinkedList;

/**
 *
 * @author mery
 */
public class VisitorType implements ASTVisitor <String>{
    LinkedList<LinkedList<MethodAndDeclList>> fieldlist;
    LinkedList<MethodAndDeclList> methlist;
    MethodAndDeclList thismethod;

    public String visit(AssignStmt AssSt) {
        String analyze=AssSt.getExpression().accept(this);
        
        if(AssSt.getExpression().getClass().toString().contains("BinOpExpr")){
            return analyze;
        }else{
            for(int i=0;i<fieldlist.size();i++){
                for(int j=0;j<fieldlist.get(i).size();j++){
                    if(fieldlist.get(i).get(j).getName().equals(AssSt.getId().toString())){
                        if(!(fieldlist.get(i).get(j).getType().equals(analyze))){
                            return "type";
                        }
                    }
                }
            }
            analyze=null;
        }
        return analyze;
    }

    public String visit(BinOpExpr bOpExpr) {
        String typeL=bOpExpr.getLeftOperand().accept(this);   
        String typeR=bOpExpr.getRightOperand().accept(this);
        if(typeR.equals(typeL)){
            bOpExpr.setTypeString(typeR);
            return null;
        }else{
            return "type";
        }
    }

    public String visit(Block bl) {
        String acEnc=null;
        int i=0;
        while(i<bl.getStatements().size() && acEnc==null){
            if(bl.getStatements().get(i)!=null){
                acEnc=bl.getStatements().get(i).accept(this);
            }
            i++;
        }
        return acEnc;
    }

    public String visit(BoolLiteral bLit) {
        return "boolean";
    }

    public String visit(BreakStmt breakSt) {
        return null;
    }

    public String visit(ClassDec clDec) {
        return clDec.getBody().accept(this);
    }

    public String visit(ContinueStmt ConSt) {
        return null;
    }

    public String visit(Declaration dec) {
        String encontro=null;        
        fieldlist=new LinkedList();
        LinkedList<MethodAndDeclList> listMeth= new LinkedList();
        if(dec.getField()!=null){
            int i = 0;
            while(i < dec.getField().size()){
                for(int j=0;j<dec.getField().get(i).getLid().size();j++){
                    MethodAndDeclList list = new MethodAndDeclList(dec.getField().get(i).getLid().get(j).getId(),dec.getField().get(i).getType().toString());
                    listMeth.add(list);
                }
                i++;
            }
        }       
        fieldlist.push(listMeth);
        methlist=new LinkedList<MethodAndDeclList>();
        for(int j=0;j<dec.getMethod().size() && encontro==null;j++){
            thismethod = new MethodAndDeclList(dec.getMethod().get(j).getId(),dec.getMethod().get(j).getType().toString());
            methlist.add(thismethod);
            encontro=dec.getMethod().get(j).accept(this);
        }
        fieldlist.pop();
        return encontro;
    }

    public String visit(Expression expr) {;
        return null;
    }

    public String visit(ExternStmt extSt) {
        return null;
    }

    public String visit(FieldDeclaration flDec) {
        throw new UnsupportedOperationException("FieldDeclaration"); //To change body of generated methods, choose Tools | Templates.
    }

    public String visit(FloatLiteral fLit) {
        return "float";
    }

    public String visit(ForStmt forSt) {
        String visit=forSt.getForBlock().accept(this);       
        if(visit==null){
            visit=forSt.getStatement().accept(this);
        }
        if(visit==null){
            if(forSt.getCondition().getClass().toString().contains("MethodCallExpr")){
                String search=forSt.getCondition().accept(this);
                if(!(search.equals("type"))){
                    for(int i=0;i<methlist.size();i++){
                        if(methlist.get(i).equals(search)){
                            if(!(methlist.get(i).equals("int"))){
                                visit="type";
                            }
                        }
                    }
                }else{
                    visit="type";
                } 
            }else{
                visit=forSt.getCondition().accept(this);
                if(visit==null){
                    for(int i=0;i<fieldlist.size() && visit==null;i++){
                        for(int j=0;j<fieldlist.get(i).size() && visit==null;j++){
                            if(fieldlist.get(i).get(j).getName().equals(forSt.getInicio())){
                                if(!(fieldlist.get(i).get(j).getType().equals("int"))){
                                    visit="type";
                                }
                            }
                        }  
                    }  
                }
                
            } 
        }
        return visit;
    }

    public String visit(IfStmt ifSt) {
        String value=ifSt.getCondition().accept(this);
        if(value!=null){
            return value;
        }
        value=ifSt.getIfBlock().accept(this);
        if(value!=null){
            return value;
        }
        if(ifSt.getElseBlock()!=null){
            value=ifSt.getElseBlock().accept(this);
            if(value!=null){
                return value;
            }
        }
        return null;
    }

    public String visit(IntLiteral iLit) {
        return "int";
    }

    public String visit(Literal lit) {
        return lit.accept(this);
    }

    public String visit(Location loc) {
        throw new UnsupportedOperationException("Location"); //To change body of generated methods, choose Tools | Templates.
    }

    public String visit(LocationDeclaration locDec) {
        throw new UnsupportedOperationException("LocationDeclaration"); //To change body of generated methods, choose Tools | Templates.
    }

    public String visit(Method meth) {
        LinkedList<MethodAndDeclList> listFieldMeth= new LinkedList<MethodAndDeclList>();
        if(meth.getParameter()!=null){
            for(int i=0;i<meth.getParameter().size();i++){
                MethodAndDeclList list = new MethodAndDeclList(meth.getParameter().get(i).getId(),meth.getParameter().get(i).getType().toString());
                listFieldMeth.add(list);
            }
//            for(int j=0;j<meth.getParameter().size();j++){
//                System.out.println("Tipo: "+ meth.getParameter().get(j).getType()+" | Parametro: "+meth.getParameter().get(j).getId());
//            }
        }
        if(meth.getBlock().getField()!=null){
            for(int i=0; i<meth.getBlock().getField().size();i++){
                for(int j=0; j<meth.getBlock().getField().get(i).getLid().size();j++){
                    MethodAndDeclList list = new MethodAndDeclList(meth.getBlock().getField().get(i).getLid().get(j).getId(),meth.getBlock().getField().get(i).getType().toString());
                    listFieldMeth.push(list);
                }
               
            } 
        }
        fieldlist.push(listFieldMeth);
        String encontro=meth.getBlock().accept(this);
        fieldlist.pop();
        return encontro;
    }

    public String visit(MethodCall mc) {
            String t1=null;
            String t2=null;
            for(int i=0;i<mc.getlParam().size() && t1==null;i++){
                t1=mc.getlParam().get(i).accept(this);
            }
            for(int i=0;i<methlist.size();i++){
                if(mc.getId().equals(methlist.get(i).getName())){
                    t2=methlist.get(i).getType();
                }
            }
            if(t1!=null){
                return t1;
            }
            if(t2!=null){
                return mc.getId();
            }else{
                return "type";
            }
    }

    public String visit(MethodCallExpr mcExpr) {
        return mcExpr.getMethodCallExpr().accept(this);
    }

    public String visit(Parameter param) {
        boolean encontro=false;
        for(int i=0;i<fieldlist.size()&& !encontro;i++){
            for(int j=0;i<fieldlist.get(i).size() && !encontro;j++){
                if(param.getId().equals(fieldlist.get(i).get(j).getName())){
                    param.setTypeString(fieldlist.get(i).get(j).getType());
                    encontro=true;
                }  
            }
        }
        if (encontro){
            return null;
        }else{
            return "type";
        }
    }

    public String visit(ReturnStmt ret) {
        String analyze;
        if(ret.getExpression()==null){
            if(thismethod.getType().equals("void")){
                return null;
            }else{
                return "ret";
            }
        }else
            analyze=ret.getExpression().accept(this);
            if(!(ret.getExpression().getType().toString().equals(thismethod.getType()))){
                return "ret";
            }
        return analyze;
    }

    public String visit(Statement stmt) {
        throw new UnsupportedOperationException("Statement"); //To change body of generated methods, choose Tools | Templates.
    }

    public String visit(UnaryOpExpr unOpExpr) {
        throw new UnsupportedOperationException("UnaryOpExpr"); //To change body of generated methods, choose Tools | Templates.
    }

    public String visit(VarLocation varLoc) {
        String type=null;
        for(int i=0;i<fieldlist.size()&& type==null;i++){
                for(int j=0;j<fieldlist.get(i).size() && type==null;j++){
                    if(fieldlist.get(i).get(j).getName().equals(varLoc.toString())){
                        if(fieldlist.get(i).get(j).getType().equals("int")){
                            type="int";
                            varLoc.setType(Type.INT);
                        } else if(fieldlist.get(i).get(j).getType().equals("float")){
                            type="float";
                            varLoc.setType(Type.FLOAT);
                        }else if(fieldlist.get(i).get(j).getType().equals("boolean")){
                            type="boolean";
                            varLoc.setType(Type.BOOLEAN);
                        }else if (fieldlist.get(i).get(j).getType().equals("void")){
                            type="viod";
                            varLoc.setType(Type.VOID);
                        }else if (fieldlist.get(i).get(j).getType().equals("int[]")){
                            type="int[]";
                            varLoc.setType(Type.INTARRAY);
                        }else{
                            type="undefined";
                            varLoc.setType(Type.UNDEFINED);
                        }
                    }
                }
            }
        return type;
    }

    public String visit(WhileStmt whileSt) {
        String search=null;
        search=whileSt.getCondition().accept(this);
        if(search == null){
            search=whileSt.getStatement().accept(this);
        }
        return search;
    }

    public String visit(Program prog) {
        int i = 0;
        String termino=null;
        while(termino==null && i<prog.getL().size()){
            termino = prog.getL().get(i).accept(this);
            i++;           
        }
        if(termino!=null){
            if (termino.equals("type")) {
                System.out.println(new IllegalArgumentException("Incompatible data types in assing. Line: "+prog.getLineNumber()+" Column: "+prog.getColumnNumber()));
            }
            if (termino.equals("ret")) {
                System.out.println(new IllegalArgumentException("Incompatible data types in return. Line: "+prog.getLineNumber()+" Column: "+prog.getColumnNumber()));
            }
        }
        return null;
    }
    
}