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
    private int temp;
    
    public void generateAss(String rute,String nombre, LinkedList<OperadorCI> l) throws IOException{
        temp=0;
        lista = l;
        f = new File(rute,nombre);
        try{
            FileWriter w = new FileWriter(f);
            //PrintWriter wr = new PrintWriter(w);
            //aca el swich
            listaCI op;
            for (int i = 0; i < lista.size(); i++) {
                op = lista.get(i).getNom();
                OperadorCI gen=lista.get(i);
                switch (op){
                    case ADDINT:
                       w.write(generateAddInt(gen));
                       break;
                        //aca escribir en el archivo
                    case ADDFLOAT:
                        break;
                        //aca escribir en el archivo
                    case MULTI:
                        w.write(generateMultInt(gen));
                        break;
                    case MULTF:
                        break;
                        //aca escribir en el archivo
                    case DIVIDEI:
                        w.write(generateDivInt(gen));
                        break;
                        //aca escribir en el archivo
                    case DIVIDEF:
                        break;
                        //aca escribir en el archivo
                    case MODI:
                        w.write(generateModInt(gen));
                        break;
                        //aca escribir en el archivo
                    case MODF:
                        break;
                        //aca escribir en el archivo
                    case MINUSI:
                        w.write(generateMinusInt(gen));
                        break;
                        //aca escribir en el archivo
                    case MINUSF:
                        break;
                        //aca escribir en el archivo
                    case GTR:
                        w.write(generateGtr(gen));
                        break;
                        //aca escribir en el archivo
                    case LESS:
                        break;
                        //aca escribir en el archivo
                    case LESS_EQ:
                        break;
                        //aca escribir en el archivo
                    case GTR_EQ:
                        break;
                        //aca escribir en el archivo
                    case EQ:
                        break;
                        //aca escribir en el archivo
                    case NOT_EQ:
                        break;
                        //aca escribir en el archivo
                    case AND:
                        break;
                        //aca escribir en el archivo
                    case OR:
                        break;
                        //aca escribir en el archivo
                    case ASSMNT:
                        w.write(generateAssmnt(gen));
                        break;
                        //aca escribir en el archivo
                    case NOT:
                        break;
                        //aca escribir en el archivo
                    case MIN:
                        break;
                        //aca escribir en el archivo
                    case RET:
                        break;
                        //aca escribir en el archivo
                    case CALL:
                        break;
                        //aca escribir en el archivo
                    case JUMP:
                        break;
                        //aca escribir en el archivo
                    case JUMP_FALSE:
                        break;
                        //aca escribir en el archivo
                    case BREAK:
                        break;
                        //aca no hacer nada
                    case CONTINUE:
                        break;
                        //aca no hacer nada
                    case PUSHI:
                        break;
                        //aca escribir en el archivo
                    case PUSHF:
                        break;
                        //aca escribir en el archivo
                    case LABEL:
                        w.write("." + gen.getS()+"\n");
                        break;
                        //aca escribir en el archivo
                }
            }
            w.close();
            
        }catch (IOException e){System.out.println(e.getMessage());}
    }
    
    private String varOperand(Expression e){
        String type = e.getClass().getSimpleName();
        if (type.equals("VarLocation")){ 
           int offsetOp = ((Location)e).getOffset();
           if (offsetOp >= 1 && offsetOp <= 7 ){ // Is register!
               switch(offsetOp){
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
           else{ //Is memory
                return offsetOp + "(%rbp)";
           }
        }
        else{ //Is literal
            String value = e.toString();
            if (value.equals("true")) value = "1";
            if (value.equals("false")) value = "0";    
            return "$" + value;
        }
        return "";
    }
    
    private String generateAddInt(OperadorCI op){
        String result = "movl " + varOperand(op.getOp()) + ", %eax\n"+
                        "addl " + varOperand(op.getOp1()) + ", %eax\n"+
                        "movl %eax, " + varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateMultInt(OperadorCI op){
        String result = "movl " + varOperand(op.getOp()) + ", %eax\n"+
                        "imull " + varOperand(op.getOp1()) + ", %eax\n"+
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
        String result= "movl " + varOperand(op.getOp()) + ", %eax\n"+ 
                        "movl %eax, " + varOperand(op.getOp1())+"\n"; 
        return result;
    }
    
    private String generateMinusInt(OperadorCI op){
        String result = "movl " + varOperand(op.getOp()) + ", %eax\n"+
                        "subl " + varOperand(op.getOp1()) + ", %eax\n"+
                        "movl %eax, " + varOperand(op.getOp2())+"\n";
        return result;
    }
    
    private String generateGtr (OperadorCI op){
        String result;
        String temporalId = ""+temp;
        temp++;
        result = ".gtr" + temporalId + ":\n"+
                 "movl " + varOperand(op.getOp()) + ", %eax\n"+
                 "cmpl " + varOperand(op.getOp1()) + ", %eax\n"+
                 "jg .isGtr" + temporalId + "\n"+
                 "movl $0, " + varOperand(op.getOp2()) + "\n"+
                 "jmp .endGtr" + temporalId + "\n"+
                 ".isGtr" + temporalId + ":\n"+
                 "movl $1, " + varOperand(op.getOp2()) + "\n"+
                 ".endGtr" + temporalId + ":";
        return result;
    }
    
}
