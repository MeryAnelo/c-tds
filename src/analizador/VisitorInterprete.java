///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package analizador;
//
//import java.util.LinkedList;
//
///**
// *
// * @author juancruz
// */
//public class VisitorInterprete implements ASTVisitor <Object>{
//
//    @Override
//    public Object visit(AssignOpType type) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Object visit(AssignStmt AssSt) {
//        AssignOpType op = AssSt.getOperator();
//        switch (op) {
//            case AUTOIN:
//                if (AssSt.getExpression().accept(this).getClass().equals(Integer.class)) {
//                    
//                } else {
//                    
//                }
//                break;
//            case AUTODEC:
//                if (AssSt.getExpression().accept(this).getClass().equals(Integer.class)) {
//                    
//                } else {
//                    
//                }
//                break;
//            case ASSMNT:
//                
//                break;
//        }
//        return null;
//    }
//
//    @Override
//    public Object visit(BinOpExpr bOpExpr) {
//        BinOpType op = bOpExpr.getOperator();
//        switch (op) {
//            case PLUS:
//                if (bOpExpr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
//                    return (int) bOpExpr.getLeftOperand().accept(this) + (int) bOpExpr.getRightOperand().accept(this);
//                } else {
//                    return (float) bOpExpr.getLeftOperand().accept(this) + (float) bOpExpr.getRightOperand().accept(this);
//                }
//            case MINUS:
//                if (bOpExpr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
//                    return (int) bOpExpr.getLeftOperand().accept(this) - (int) bOpExpr.getRightOperand().accept(this);
//                } else {
//                    return (float) bOpExpr.getLeftOperand().accept(this) - (float) bOpExpr.getRightOperand().accept(this);
//                }
//            case MULT:
//                if (bOpExpr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
//                    return (int) bOpExpr.getLeftOperand().accept(this) * (int) bOpExpr.getRightOperand().accept(this);
//                } else {
//                    return (float) bOpExpr.getLeftOperand().accept(this) * (float) bOpExpr.getRightOperand().accept(this);
//                }
//            case DIVIDE:
//                if (bOpExpr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
//                    return (int) bOpExpr.getLeftOperand().accept(this) / (int) bOpExpr.getRightOperand().accept(this);
//                } else {
//                    return (float) bOpExpr.getLeftOperand().accept(this) / (float) bOpExpr.getRightOperand().accept(this);
//                }
//            case DIV:
//                return (int) bOpExpr.getLeftOperand().accept(this) % (int) bOpExpr.getRightOperand().accept(this);
//            case LESS:
//                if (bOpExpr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
//                    return (int) bOpExpr.getLeftOperand().accept(this) < (int) bOpExpr.getRightOperand().accept(this);
//                } else {
//                    return (float) bOpExpr.getLeftOperand().accept(this) < (float) bOpExpr.getRightOperand().accept(this);
//                }
//            case LESS_EQ:
//                if (bOpExpr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
//                    return (int) bOpExpr.getLeftOperand().accept(this) <= (int) bOpExpr.getRightOperand().accept(this);
//                } else {
//                    return (float) bOpExpr.getLeftOperand().accept(this) <= (float) bOpExpr.getRightOperand().accept(this);
//                }
//            case GTR:
//                if (bOpExpr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
//                    return (int) bOpExpr.getLeftOperand().accept(this) > (int) bOpExpr.getRightOperand().accept(this);
//                } else {
//                    return (float) bOpExpr.getLeftOperand().accept(this) > (float) bOpExpr.getRightOperand().accept(this);
//                }
//            case GTR_EQ:
//                if (bOpExpr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
//                    return (int) bOpExpr.getLeftOperand().accept(this) >= (int) bOpExpr.getRightOperand().accept(this);
//                } else {
//                    return (float) bOpExpr.getLeftOperand().accept(this) >= (float) bOpExpr.getRightOperand().accept(this);
//                }
//            case EQ:
//                if (bOpExpr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
//                    return (int) bOpExpr.getLeftOperand().accept(this) == (int) bOpExpr.getRightOperand().accept(this);
//                } else {
//                    if (bOpExpr.getLeftOperand().accept(this).getClass().equals(Boolean.class)) {
//                        return (boolean) bOpExpr.getLeftOperand().accept(this) == (boolean) bOpExpr.getRightOperand().accept(this);
//                    } else {
//                        return (float) bOpExpr.getLeftOperand().accept(this) == (float) bOpExpr.getRightOperand().accept(this);
//                    }
//                }
//            case NOT_EQ:
//                if (bOpExpr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
//                    return (int) bOpExpr.getLeftOperand().accept(this) != (int) bOpExpr.getRightOperand().accept(this);
//                } else {
//                    if (bOpExpr.getLeftOperand().accept(this).getClass().equals(Boolean.class)) {
//                        return (boolean) bOpExpr.getLeftOperand().accept(this) != (boolean) bOpExpr.getRightOperand().accept(this);
//                    } else {
//                        return (float) bOpExpr.getLeftOperand().accept(this) != (float) bOpExpr.getRightOperand().accept(this);
//                    }
//                }
//            case AND:
//                return (boolean) bOpExpr.getLeftOperand().accept(this) && (boolean) bOpExpr.getRightOperand().accept(this);
//            case OR:
//                return (boolean) bOpExpr.getLeftOperand().accept(this) || (boolean) bOpExpr.getRightOperand().accept(this);
//        }
//        return null;
//    }
//
//    @Override
//    public Object visit(Block bl) {
//        Object objeto = null;
//        for (Statement s : bl.getStatements()) {
//            Object aux = s.accept(this);
//            if (aux != null) {
//                objeto = aux;
//            }
//        }
//        return objeto;
//    }
//
//    @Override
//    public Object visit(BoolLiteral bLit) {
//        return bLit.getValue();
//    }
//
//    @Override
//    public Object visit(BreakStmt breakSt) {
//        return "break";
//    }
//
//    @Override
//    public Object visit(ClassDec clDec) {
//        if (clDec.getBody()!= null) {
//            clDec.getBody().accept(this);
//        }
//        return null;
//    }
//
//    @Override
//    public Object visit(ContinueStmt ConSt) {
//        return null;
//    }
//
//    @Override
//    public Object visit(Declaration dec) {
//        int i =0;
//        while (i<dec.getMethod().size()) {
//            dec.getMethod().get(i).accept(this);
//            i++;
//        }
//        return null;
//    }
//
//    @Override
//    public Object visit(Expression expr) {
//        expr.accept(this);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Object visit(ExternStmt extSt) {
//        return null;
//    }
//
//    @Override
//    public Object visit(FieldDeclaration flDec) {
//        return null;
//    }
//
//    @Override
//    public Object visit(FloatLiteral fLit) {
//        return fLit.getValue();
//    }
//
//    @Override
//    public Object visit(ForStmt forSt) {
//        //CONSULTAR
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Object visit(IfStmt ifSt) {
//        if ((boolean) ifSt.getCondition().accept(this)) {
//            if (ifSt.getIfBlock() != null) {
//                return ifSt.getIfBlock().accept(this);
//            }
//        } else {
//            if (ifSt.getElseBlock() != null) {
//                return ifSt.getElseBlock().accept(this);
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Object visit(IntLiteral iLit) {
//        return iLit.getValue();
//    }
//
//    @Override
//    public Object visit(Literal lit) {
//        lit.accept(this);
//        return null;
//    }
//
//    @Override
//    public Object visit(Location loc) {
//        //CONSULTAR
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Object visit(LocationDeclaration locDec) {
//        return null;
//    }
//
//    @Override
//    public Object visit(Method meth) {
//        Object o = null;
//        if (meth.getBlock()!= null) {
//            o = meth.getBlock().accept(this);
//        }
//        
//        if (!meth.getType().isVoid()) {
//            return o;
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public Object visit(MethodCall mc) {
//        //consultar
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Object visit(MethodCallExpr mcExpr) {
//        return mcExpr.getMethodCallExpr().accept(this);
//    }
//
//    @Override
//    public Object visit(Parameter param) {
//        return null;
//    }
//
//    @Override
//    public Object visit(ReturnStmt ret) {
//        if (ret.getExpression() != null) {
//            return ret.getExpression().accept(this);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public Object visit(Statement stmt) {
//        stmt.accept(this);
//        return null;
//    }
//
//    @Override
//    public Object visit(UnaryOpExpr unOpExpr) {
//        UnaryOpType op = unOpExpr.getOperator();
//        switch (op) {
//            case NOT:
//                return !((boolean) unOpExpr.getLeftOperand().accept(this));
//            case MINUS:
//                if (unOpExpr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
//                    return (-(int) unOpExpr.getLeftOperand().accept(this));
//                } else {
//                    return (-(float) unOpExpr.getLeftOperand().accept(this));
//                }
//        }
//        return null;
//    }
//
//    @Override
//    public Object visit(VarLocation varLoc) {
//        //consultar
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Object visit(WhileStmt whileSt) {
//        LinkedList<Object> lista = new LinkedList<Object>();
//        while ((boolean) whileSt.getCondition().accept(this)) {
//            if (whileSt.getStatement()!= null) {
//                Object object = whileSt.getStatement().accept(this);
//                lista.add(object);
//                if (object != null && object.equals("break")) {
//                    for (Object obAux : lista) {
//                        if (obAux != null) {
//                            return obAux;
//                        }
//                    }
//                    return null;
//                }
//            }
//        }
//        for (Object ob : lista) {
//            if (ob != null) {
//                return ob;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Object visit(Program prog) {
//        int i =0;
//        while (i<prog.getL().size()){
//            prog.getL().get(i).accept(this);
//            i++;
//        }
//        return null;
//    }
//    
//}
