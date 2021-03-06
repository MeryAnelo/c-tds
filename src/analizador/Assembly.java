/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * 
 *
 * @author juancruz
 */
public class Assembly {
    
    private LinkedList<OperadorCI> lista;
    private File f;
    private int pusheado=0;
    private int temp;
    
    public void generateAss(String nombre, LinkedList<OperadorCI> l) throws IOException{
        temp=0;
        lista = l;
        f = new File("../"+nombre);
        try{
            FileWriter w = new FileWriter(f);
            listaCI op;
            for (int i = 0; i < lista.size(); i++) {
                op = lista.get(i).getNom();
                OperadorCI gen=lista.get(i);
                System.out.println("--"+lista.get(i).getNom()+"="+op);
                switch (op){
                    case ADDINT:
                       w.write(generateAddInt(gen));
                       break;
                    case ADDFLOAT:
                        w.write(generateAddFloat(gen));
                        break;
                    case MULTI:
                        w.write(generateMultInt(gen));
                        break;
                    case MULTF:
                        w.write(generateMultFloat(gen));
                        break;
                    case DIVIDEI:
                        w.write(generateDivInt(gen));
                        break;
                    case DIVIDEF:
                        w.write(generateDivFloat(gen));
                        break;
                    case MODI:
                        w.write("\n\n"+generateModInt(gen)+"\n\n");
                        break;
                    case MODF:
                        break;
                        //aca escribir en el archivo
                    case MINUSI:
                        w.write(generateMinusInt(gen));
                        break;
                    case MINUSF:
                        w.write(generateMinusFloat(gen));
                        break;
                        //aca escribir en el archivo
                    case GTR:
                        w.write(generateGtr(gen));
                        break;
                    case LESS:
                        w.write(generateLess(gen));
                        break;
                    case LESS_EQ:
                        w.write(generateLessEq(gen));
                        break;
                    case GTR_EQ:
                        w.write(generateGtrEq(gen));
                        break;
                    case EQ:
                        w.write(generateEq(gen));
                        break;
                    case NOT_EQ:
                        w.write(generateNotEqual(gen));
                        break;
                    case AND:
                        w.write(generateAnd(gen));
                        break;
                    case OR:
                        w.write(generateOr(gen));
                        break;
                    case ASSMNT:
                        w.write(generateAssmnt(gen));
                        break;
                    case NOT:
                        w.write(generateNot(gen));
                        break;
                    case MIN:
                        w.write(generateMin(gen));
                        break;
                    case RET:
                        w.write(generateReturn(gen));
                        break;
                    case CALL:
                        w.write(generateCall(gen));
                        break;
                    case CALL_EXTERN:
                        //aca escribir en el archivo
                        break;
                    case JUMP:
                        w.write(generateJump(gen));
                        break;
                    case JUMP_FALSE:
                        w.write(generateJumpFalse(gen));
                    case BREAK:
                        break;
                        //aca no hacer nada
                    case CONTINUE:
                        break;
                        //aca no hacer nada
                    case PUSHI:
                        w.write(generatePushInt(gen));
                        break;
                    case PUSHF:
                        w.write(generatePushFloat(gen));
                        break;
                    case LABEL:
//                        if (gen.getS().contains("Main:") || gen.getS().contains("main:") || gen.getS().contains("MAIN:")) {
//                            w.write(gen.getS()+"\n"+
//                                "\tpushl %ebp\n"+
//                                "\tmovl %esp, %ebp\n"+
//                                "\tsubl $16, %esp\n");
//                        }
                        if(gen.isMeth()){
                            w.write(gen.getS());
                        }
                        if (!gen.isMeth() && (!gen.getS().contains("Main:") && !gen.getS().contains("main:") && !gen.getS().contains("MAIN:"))) {
                            w.write(gen.getS()+":"+"\n");
                        }
                        
                        break;
                }
                System.out.println(i+" < "+lista.size());
            }
            w.close();
            
        }catch (IOException e){System.out.println(e.getMessage());}
    }
    
    
    private String varOperand(Expression e){
        String type = e.getClass().getSimpleName();
        if (type.equals("VarLocation")){ 
           Location expLoc=(Location) e;
           int offset= ((Location)e).getOffset();
           if (offset >= 1 && offset <= 7 ){
               if(expLoc.getType().toString().equals("float")){
                    return "%xmm"+ offset;
               }else{
                 switch(offset){
                 case 1:
                     return "%edi";
                  case 2:
                     return "%esi";
                  case 3:
                     return "%edx";
                  case 4:
                     return "%ecx";
                  case 5:
                     return "%r8d";
                  case 6:
                     return "%r9d";  
                  }
                }
           }else{
                return offset +"(%ebp)"; //MEMORIA
           }
        }else{ 
            /*
            **ES UN LITERAL
            */
            System.out.println("clase: "+e.getClass().toString());
            String valor=valor=e.toString();
            if (e.getType().toString().equals("float")){
                Float valorF = Float.valueOf(valor);
                valor = "" + Float.floatToIntBits(valorF);
            }
            if (valor.equals("true")){
                valor = "1";
            }
            if (valor.equals("false")){
                valor = "0";
            } 
            return "$" + valor;
        }
        return "";
    }
    
    private String generateAddInt(OperadorCI op){
        String result = "\tmovl " + varOperand(op.getOp()) + ", %eax\n"+
                        "\tmovl " + varOperand(op.getOp1()) + ", %edx\n"+
                        "\taddl %edx, %eax\n"+
                        "\tmovl %eax, " + varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateAddFloat(OperadorCI op){
        String result = "\tflds "+varOperand(op.getOp())+"\n"+
                        "\tfadds "+varOperand(op.getOp1())+"\n"+
                        "\tfstps "+varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateMultInt(OperadorCI op){
        String result = "\tmovl " + varOperand(op.getOp()) + ", %eax\n"+
                        "\tmovl " + varOperand(op.getOp1()) + ", %edx\n"+
                        "\timull " + "%edx" + ", %eax\n"+
                        "\tmovl  %eax,  " + varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateDivInt(OperadorCI op){ 
        String result="\tmovl $0, %edx\n"+
                   "\tmovl " + varOperand(op.getOp()) + ", %eax\n"+ 
                   "\tmovl " + varOperand(op.getOp1()) + ", %ecx\n"+
                   "\tcltd\n"+
                   "\tidivl %ecx\n"+
                   "\tmovl " + " %eax, " + varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateModInt(OperadorCI op){
        String result = "\tmovl $0, %edx\n"+
                        "\tmovl " + varOperand(op.getOp()) + ", %eax\n"+
                        "\tmovl " + varOperand(op.getOp1()) + ", %ecx\n"+
                        "\tcltd\n"+
                        "\tidivl %ecx\n"+
                        "\tmovl " + " %edx, " + varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateAssmnt(OperadorCI op){
        String result=  "\tmovl "+ varOperand(op.getOp1()) + ", %eax \n"+
                        "\tmovl " + "%eax" +", "+varOperand(op.getOp())+"\n";
        return result;
    }
    
    private String generateMinusInt(OperadorCI op){   
        String result = "\tmovl " + varOperand(op.getOp()) + ", %eax\n"+
                    "\tmovl " + varOperand(op.getOp1()) + ", %edx\n"+
                    "\tsubl %edx, %eax\n"+
                    "\tmovl %eax, " + varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateGtr (OperadorCI op){
        String temporalId = ""+temp;
        temp++;
        String result ="\tmovl " + varOperand(op.getOp()) + ", %eax\n"+
                 "\tcmpl " + varOperand(op.getOp1()) + ", %eax\n"+
                 "jg isGtr" + temporalId + "\n"+
                 "\tmovl $0, " + varOperand(op.getOp2()) + "\n"+
                 "jmp endGtr" + temporalId + "\n"+
                 "isGtr" + temporalId + ":\n"+
                 "\tmovl $1, " + varOperand(op.getOp2()) + "\n"+
                 "endGtr" + temporalId + ":\n";
        return result;        
    }
    
    private String generateLess(OperadorCI op){
        String temporalId = ""+temp;
        temp++;
        
        String result= "\tmovl " + varOperand(op.getOp()) + ", %eax\n"+
                    "\tcmpl " + varOperand(op.getOp1())+ ", %eax\n"+
                    "\tjl  trueLess" + temporalId+"\n"+
                    "\tmovl $0, " + varOperand(op.getOp2()) + "\n"+
                    "\tjmp  endtrueLess" + temporalId+"\n"+
                    "trueLess" + temporalId + ":\n"+
                    "\tmovl $1, " + varOperand(op.getOp2()) + "\n"+
                    "endtrueLess" + temporalId + ":\n";
        return result;
    }
    
    private String generateLessEq(OperadorCI op){
        String temporalId = ""+temp;
        temp++;
        String result= "\tmovl " + varOperand(op.getOp()) + ", %eax\n"+
                    "\tcmpl " + varOperand(op.getOp1())+ ", %eax\n"+
                    "\tjle  trueLessEq" + temporalId+"\n"+
                    "\tmovl $0, " + varOperand(op.getOp2()) + "\n"+
                    "\tjmp  endtrueLessEq" + temporalId+"\n"+
                    "trueLessEq" + temporalId + ":\n"+
                    "\tmovl $1, " + varOperand(op.getOp2()) + "\n"+
                    "endtrueLessEq" + temporalId + ":\n";
        return result;
    }
    
    private String generateGtrEq(OperadorCI op){
        String temporalId = ""+temp;
        temp++;
        String result= "\tmovl " + varOperand(op.getOp()) + ", %eax\n"+
                    "\tcmpl " + varOperand(op.getOp1())+ ", %eax\n"+
                    "\tjge  trueGtrEq" + temporalId+"\n"+
                    "\tmovl $0, " + varOperand(op.getOp2()) + "\n"+
                    "\tjmp  endtrueGtrEq" + temporalId+"\n"+
                    "trueGtrEq" + temporalId + ":\n"+
                    "\tmovl $1, " + varOperand(op.getOp2()) + "\n"+
                    "endtrueGtrEq" + temporalId + ":\n";
        return result;
    }
    
    private String generateEq(OperadorCI op){
        String temporalId = ""+temp;
        temp++;
        String result= //"Equal" + temporalId + ":\n"+
                        "\tmovl " + varOperand(op.getOp()) + ", %eax\n"+
                        "\tcmpl " + varOperand(op.getOp1()) + ", %eax\n"+
                        "je equals" + temporalId + "\n"+
                        "\tmovl $0, " + varOperand(op.getOp2()) + "\n"+
                        "jmp endEqual" + temporalId + "\n"+ 
                        "equals" + temporalId + ":\n"+
                        "\tmovl $1, " + varOperand(op.getOp2()) + "\n"+
                        "endEqual" + temporalId + ":\n";
        return result;
    }
    
    private String generateNotEqual(OperadorCI op){
        String temporalId = ""+temp;
        temp++;
        String result= "\tmovl " + varOperand(op.getOp()) + ", %eax\n"+
                    "\tcmpl " + varOperand(op.getOp1())+ ", %eax\n"+
                    "\tjne  notEq" + temporalId+"\n"+
                    "\tmovl $0, " + varOperand(op.getOp2()) + "\n"+
                    "\tjmp  endnotEq" + temporalId+"\n"+
                    "notEq" + temporalId + ":\n"+
                    "\tmovl $1, " + varOperand(op.getOp2()) + "\n"+
                    "endnotEq" + temporalId + ":\n";
        return result;
    }
    
    private String generateAnd(OperadorCI op){
        String result= "\tmovl " + varOperand(op.getOp()) + ", %eax\n"+
                        "\tandl " + varOperand(op.getOp1()) + ", %eax\n"+
                        "\tmovl %eax, " + varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateOr(OperadorCI op){
        String result= "\tmovl " + varOperand(op.getOp()) + ", %eax\n"+
                        "\torl " + varOperand(op.getOp1()) + ", %eax\n"+
                        "\tmovl %eax, " + varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateNot(OperadorCI op){
        String result= "\tmovl " + varOperand(op.getOp()) + ", %eax\n"+
                        "\tnotl %eax\n"+
                        "\taddl $2, %eax\n"+
                        "\tmovl %eax, " + varOperand(op.getOp1())+"\n";
        return result;
    }
    
    private String generateCall(OperadorCI op){
        String result="\tcall "+op.getS()+"\n"+
                      "\tmovl %eax,"+varOperand(op.getOp())+"\n"+
                      "\taddl $"+pusheado*4+", %esp\n";  
        pusheado=0;
        return result;
    }
    
    private String generatePushInt(OperadorCI op){
        String result;
//        if (op.getOp().getType()==Type.INT && op.getOp1()==null) {
//            //System.out.println("LITERALLLLLLLLLLLLLL");
//            result="\tmovl "+varOperand(op.getOp())+", %eax\n"+
//            "\tpushl %eax\n";
//        }else{
//            result="\tmovl "+varOperand(op.getOp1())+", %eax\n"+
//            "\tpushl %eax\n";
//        }
//        if(varOperand(op.getOp()).equals("")){
//            return "";
//        }
        result="\tpushl "+varOperand(op.getOp())+"\n";
        pusheado++;
        return result;
    }
    
    private String generatePushFloat(OperadorCI op){
        int value = ((IntLiteral)op.getOp()).getValue();
        String result;
        if (value>=0 && value<=7){
            result="\tmovl " + varOperand(op.getOp()) + ", %eax\n"+
                   "\tmovl %eax, %xmm"+value+"\n";
        }else{
            result="\tpush "+varOperand(op.getOp())+"\n";
            pusheado++;
        }
        return result;
    }
    
    private String generateJump(OperadorCI op){
                String result= "\tjmp " + op.getS()+"\n";
                return result;
        }
    
    private String generateJumpFalse(OperadorCI op){
            String result=  "\tmovl " + varOperand(op.getOp()) + ", %eax\n"+
                            "\tcmpl $0, %eax\n"+
                            "\tje " + op.getS()+"\n";
            return result;
    }
    
    private String generateMin(OperadorCI op){
        String result="";
        if(op.getOp().getType().toString().equals("int")){
            result="\tmovl "+varOperand(op.getOp())+", %eax\n"+
                    "\tnegl %eax\n"+
                    "\tmovl %eax, "+varOperand(op.getOp1())+"\n";
        }else{//Es un float
            result = "\tmovl " + varOperand(op.getOp()) + ", %eax\n"+
                     "\tmovl $0, %ebx\n"+
                     "\tmovl %ebx, %xmm0\n"+
                     "\tmovl %eax, %xmm1\n"+
                     "\tsubss %xmm1, %xmm0\n"+
                     "\tmovl %xmm0, " + varOperand(op.getOp1())+"\n";
        }
        return result;
    }
        
    
    private String generateReturn(OperadorCI op){
        String result;
        if (op.getOp()!=null){
            result = "\tmovl " + varOperand(op.getOp()) + ", " + "%eax\n";
//            if (op.getOp().getType().toString().equals("float")){
//                result += "\tmovl %eax, %xmm0\n";
//            }
        }else{
            result = "\tnop\n"; //no operaciòn, "return;"
        }
        result += "\tleave\n\tret\n";
        return result;
    }
    
    private String generateMinusFloat(OperadorCI op){
        String result = "\tflds "+varOperand(op.getOp())+"\n"+
                        "\tfsubs "+varOperand(op.getOp1())+"\n"+
                        "\tfstps "+varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateMultFloat(OperadorCI op){
        String result = "\tflds "+varOperand(op.getOp())+"\n"+
                        "\tfmuls "+varOperand(op.getOp1())+"\n"+
                        "\tfstps "+varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateDivFloat(OperadorCI op){
        String result = "\tflds "+varOperand(op.getOp())+"\n"+
                        "\tfdivs "+varOperand(op.getOp1())+"\n"+
                        "\tfstps "+varOperand(op.getOp2())+"\n";       
        return result;
    }
    
}
