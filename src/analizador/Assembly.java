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
 * @author juancruz
 */
public class Assembly {
    
    private LinkedList<OperadorCI> lista;
    private File f;
    private int pusheado;
    private int temp;
    
    public void generateAss(String rute,String nombre, LinkedList<OperadorCI> l) throws IOException{
        temp=0;
        lista = l;
        f = new File(rute,nombre);
        try{
            FileWriter w = new FileWriter(f);
            w.write(".globl  main\n");
            w.write(".type main, @function\n");
            //PrintWriter wr = new PrintWriter(w);
            //aca el swich
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
                        break;
                        //aca escribir en el archivo
                    case DIVIDEI:
                        w.write(generateDivInt(gen));
                        break;
                    case DIVIDEF:
                        break;
                        //aca escribir en el archivo
                    case MODI:
                        w.write(generateModInt(gen));
                        break;
                    case MODF:
                        break;
                        //aca escribir en el archivo
                    case MINUSI:
                        w.write(generateMinusInt(gen));
                        break;
                    case MINUSF:
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
                        if (!gen.getS().contains("Extern: ") && !gen.getS().contains("End-Method")) {
                            w.write(gen.getS()+"\n"+
                                "pushl %ebp\n"+
                                "movl %esp, %ebp\n"+
                                "subl $16, %esp\n");
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
        String result = "movl " + varOperand(op.getOp()) + ", %eax\n"+
                        "movl " + varOperand(op.getOp1()) + ", %edx\n"+
                        "addl " + "%edx" + ", %eax\n"+
                        "movl %eax, " + varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateAddFloat(OperadorCI op){
        String result = "movl " + varOperand(op.getOp()) + ", %eax\n"+
                        "movl %eax, %xmm0\n"+
                        "movl " + varOperand(op.getOp1()) + ", %eax\n"+
                        "movl %eax, %xmm1\n"+
                        "addss %xmm1, %xmm0\n"+
                        "movl %xmm0, " + varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateMultInt(OperadorCI op){
        String result = "movl " + varOperand(op.getOp()) + ", %eax\n"+
                        "movl " + varOperand(op.getOp1()) + ", %edx\n"+
                        "imull " + "%edx" + ", %eax\n"+
                        "movl  %eax,  " + varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateDivInt(OperadorCI op){
        String result = "movl " + varOperand(op.getOp()) + ", %eax\n"+
                        "movl " + varOperand(op.getOp1()) + ", %ecx\n"+
                        "movl %edx, %r11d\n"+ 
                        "movl $0, %edx\n"+ //inicializar edx en 0
                        "idivl %ecx\n"+
                        "movl %r11d, %edx\n"+ 
                        "movl %eax, " + varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateModInt(OperadorCI op){
        String result = "movl " + varOperand(op.getOp()) + ", %eax\n"+
                        "movl " + varOperand(op.getOp1()) + ", %ecx\n"+
                        "movl %edx, %r11d\n"+
                        "movl $0, %edx\n"+ 
                        "idivl %ecx\n"+//resto en edx, cociente en eax
                        "movl %edx, %ecx\n"+
                        "movl %r11d, %edx\n"+ //restauracion del valor de edx
                        "movl %ecx, " + varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateAssmnt(OperadorCI op){
        
        String result=  "movl "+ varOperand(op.getOp1()) + ", %eax \n"+
                        "movl " + "%eax" +", "+varOperand(op.getOp())+"\n";
        return result;
    }
    
    private String generateMinusInt(OperadorCI op){
        String result = "movl " + varOperand(op.getOp()) + ", %eax\n"+
                        "movl " + varOperand(op.getOp1()) + ", %edx\n"+
                        //"movl " + "%edx" + ", %ecx\n"+
                        "subl " + "%edx" + ", %eax\n"+
                        "movl " + "%ecx" + ", %eax\n"+
                        "movl %eax, " + varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateGtr (OperadorCI op){
        String temporalId = ""+temp;
        temp++;
        String result = ".gtr" + temporalId + ":\n"+
                 "movl " + varOperand(op.getOp()) + ", %eax\n"+
                 "cmpl " + varOperand(op.getOp1()) + ", %eax\n"+
                 "jg .isGtr" + temporalId + "\n"+
                 "movl $0, " + varOperand(op.getOp2()) + "\n"+
                 "jmp .endGtr" + temporalId + "\n"+
                 ".isGtr" + temporalId + ":\n"+
                 "movl $1, " + varOperand(op.getOp2()) + "\n"+
                 ".endGtr" + temporalId + ":\n";
        /*
        **preguntar si esta bien
        */
        
        
        return result;        
    }
    
    private String generateLess(OperadorCI op){
        String temporalId = ""+temp;
        temp++;
        String result = ".less" + temporalId + ":\n"+
                  "movl " + varOperand(op.getOp()) + ", %eax\n";
        if (op.getOp().getType().equals(Type.FLOAT)){
            result += "movl %eax, %xmm0\n"+
                      "movl " + varOperand(op.getOp1()) + ", %eax\n"+
                      "movl %eax, %xmm1\n"+
                      "ucomiss %xmm0, %xmm1\n"+
                      "ja .isLess" + temporalId + "\n";
        }else{
            result += "cmpl " + varOperand(op.getOp1()) + ", %eax\n"+
                      "jl .isLess" + temporalId + "\n";
        }
        result += "movl $0, " + varOperand(op.getOp2()) + "\n"+
                  "jmp .endLess" + temporalId + "\n"+ 
                  ".isLess" + temporalId + ":\n"+
                  "movl $1, " + varOperand(op.getOp2()) + "\n"+
                  ".endLess" + temporalId + ":\n";
        return result;
    }
    
    private String generateLessEq(OperadorCI op){
        String temporalId = ""+temp;
        temp++;
        String result = ".lessEq" + temporalId + ":\n"+
                        "movl " + varOperand(op.getOp()) + ", %eax\n";
        if (op.getOp().getType().equals(Type.FLOAT)){
            result += "movl %eax, %xmm0\n"+
                      "movl " + varOperand(op.getOp1()) + ", %eax\n"+
                      "movl %eax, %xmm1\n"+
                      "ucomiss %xmm0, %xmm1\n"+
                      "jae .isLessEq" + temporalId + "\n"; 
        }else{
            result += "cmpl " + varOperand(op.getOp1()) + ", %eax\n"+
                      "jle .isLessEq" + temporalId + "\n";
        }
        result += "movl $0, " + varOperand(op.getOp2()) + "\n"+
                  "jmp .endLessEq" + temporalId + "\n"+ 
                  ".isLessEq" + temporalId + ":\n"+
                  "movl $1, " + varOperand(op.getOp2()) + "\n"+
                  ".endLessEq" + temporalId + ":\n";
        return result;
    }
    
    private String generateGtrEq(OperadorCI op){
        String temporalId = ""+temp;
        temp++;
        String result = ".gtrEq" + temporalId + ":\n"+
                        "movl " + varOperand(op.getOp()) + ", %eax\n";
        if (op.getOp().getType().equals(Type.FLOAT)){
            result += "movl %eax, %xmm0\n"+
                      "movl " + varOperand(op.getOp1()) + ", %eax\n"+
                      "movl %eax, %xmm1\n"+
                      "ucomiss %xmm0, %xmm1\n"+
                      "jbe .isGtrEq" + temporalId + "\n"; 
        }else{
            result += "cmpl " + varOperand(op.getOp1()) + ", %eax\n"+
                      "jge .isGtrEq" + temporalId + "\n";
        }
        result += "movl $0, " + varOperand(op.getOp2()) + "\n"+
                  "jmp .endGtrEq" + temporalId + "\n"+ 
                  ".isGtrEq" + temporalId + ":\n"+
                  "movl $1, " + varOperand(op.getOp2()) + "\n"+
                  ".endGtrEq" + temporalId + ":";
        return result;
    }
    
    private String generateEq(OperadorCI op){
        String temporalId = ""+temp;
        temp++;
        String result= ".Equal" + temporalId + ":\n"+
                        "movl " + varOperand(op.getOp()) + ", %eax\n"+
                        "cmpl " + varOperand(op.getOp1()) + ", %eax\n"+
                        "je .equals" + temporalId + "\n"+
                        "movl $0, " + varOperand(op.getOp2()) + "\n"+
                        "jmp .endEqual" + temporalId + "\n"+ 
                        ".equals" + temporalId + ":\n"+
                        "movl $1, " + varOperand(op.getOp2()) + "\n"+
                        ".endEqual" + temporalId + ":\n";
        return result;
    }
    
    private String generateNotEqual(OperadorCI op){
        String temporalId = ""+temp;
        temp++;
        String result= ".notEqual" + temporalId + ":\n"+
                        "movl " + varOperand(op.getOp()) + ", %eax\n"+
                        "cmpl " + varOperand(op.getOp1()) + ", %eax\n"+
                        "jne .notEq" + temporalId + "\n"+
                        "movl $0, " + varOperand(op.getOp2()) + "\n"+
                        "jmp .endNotEqual" + temporalId + "\n"+ 
                        ".notEq" + temporalId + ":\n"+
                        "movl $1, " + varOperand(op.getOp2()) + "\n"+
                        ".endNotEqual" + temporalId + ":\n";
        return result;
    }
    
    private String generateAnd(OperadorCI op){
        String result= "movl " + varOperand(op.getOp()) + ", %eax\n"+
                        "andl " + varOperand(op.getOp1()) + ", %eax\n"+
                        "movl %eax, " + varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateOr(OperadorCI op){
        String result= "movl " + varOperand(op.getOp()) + ", %eax\n"+
                        "orl " + varOperand(op.getOp1()) + ", %eax\n"+
                        "movl %eax, " + varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateNot(OperadorCI op){
        String result= "movl " + varOperand(op.getOp()) + ", %eax\n"+
                        "notl %eax\n"+
                        "addl $2, %eax\n"+
                        "movl %eax, " + varOperand(op.getOp1())+"\n";
        return result;
    }
    
    private String generateCall(OperadorCI op){
        String result;
        if (!(op.getOp1().getType().equals(Type.FLOAT))){
                result="movl $0, %eax\n"+
                        "call " + op.getOp() + "\n"+
                        "movl %eax," + varOperand(op.getOp1())+"\n";
        }else{
                result="call " + op.getOp() + "\n"+
                        "movl %xmm0," + varOperand(op.getOp1())+"\n";
        }
        if (pusheado%2 != 0){
            result += "addl $8, %rsp\n";  
        }
        pusheado=0;
        return result;
    }
    
    private String generatePushInt(OperadorCI op){
        System.out.println("---------"+ ((IntLiteral)op.getOp()).getValue());
        int value = ((IntLiteral)op.getOp()).getValue();
        String registro = "";
        String result;
        if (value>0 && value<7){
            switch(value){
                case 1:
                    registro = "%rdi";
                    break;
                case 2:
                    registro = "%rsi";
                    break;
                case 3:
                    registro = "%rdx";
                    break;
                case 4:
                    registro = "%rcx";
                    break;
                case 5:
                    registro = "%r8";
                    break;
                case 6:
                    registro = "%r9";
                    break;    
            }
            result = "movl " + varOperand(op.getOp1()) + ", " + registro+"\n";
        }else{
            result = "push " + varOperand(op.getOp1())+"\n";
            pusheado++;
        }
        return result;
    }
    
    private String generatePushFloat(OperadorCI op){
        int value = ((IntLiteral)op.getOp()).getValue();
        String result;
        if (value>=0 && value<=7){
            result="movl " + varOperand(op.getOp()) + ", %eax\n"+
                   "movl %eax, %xmm"+value+"\n";
        }else{
            result="push "+varOperand(op.getOp())+"\n";
            pusheado++;
        }
        return result;
    }
    
    private String generateJump(OperadorCI op){
                String result= "jmp ." + op.getOp()+"\n";
                return result;
        }
    
    private String generateJumpFalse(OperadorCI op){
            String result="movl " + varOperand(op.getOp()) + ", %eax\n"+
                            "cmpl $0, %eax\n"+
                            "je ." + op.getOp1()+"\n";
            return result;
    }
    
    private String generateMin(OperadorCI op){
        String result="";
        if(op.getOp().getType().toString().equals("int")){
            result="movl "+varOperand(op.getOp())+", %eax\n"+
                    "negl %eax\n"+
                    "movl %eax, "+varOperand(op.getOp1())+"\n";
        }else{//Es un float
            result = "movl " + varOperand(op.getOp()) + ", %eax\n"+
                     "movl $0, %ebx\n"+
                     "movl %ebx, %xmm0\n"+
                     "movl %eax, %xmm1\n"+
                     "subss %xmm1, %xmm0\n"+
                     "movl %xmm0, " + varOperand(op.getOp1())+"\n";
        }
        return result;
    }
        
    private String generateReturn(OperadorCI op){
        String result;
        if (op.getOp()!=null){
            result = "movl " + varOperand(op.getOp()) + ", " + "%eax\n";
            if (op.getOp().getType().toString().equals("float")){
                result += "movl %eax, %xmm0\n";
            }
        }else{
            result = "nop\n"; //no operaciòn, "return;"
        }
        result += "leave\nret\n";
        return result;
    }
    
    
}
