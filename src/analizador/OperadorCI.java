/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

/**
 *
 * @author juancruz
 */
public class OperadorCI {
    private listaCI nom;
    private Expression op;
    private Expression op1;
    private Expression op2;
    private String s;

    public OperadorCI(listaCI nom, Expression op, Expression op1, Expression op2) {
        this.nom = nom;
        this.op = op;
        this.op1 = op1;
        this.op2 = op2;
    }
    
    public OperadorCI(listaCI nom, String s) {
        this.nom = nom;
        this.s = s;
    }

    public OperadorCI(listaCI nom, String s,Expression op) {
        this.nom = nom;
        this.s = s;
        this.op = op;
    }
    
    public listaCI getNom() {
        return nom;
    }

    public void setNom(listaCI nom) {
        this.nom = nom;
    }

    public Expression getOp() {
        return op;
    }

    public void setOp(Expression op) {
        this.op = op;
    }

    public Expression getOp1() {
        return op1;
    }

    public void setOp1(Expression op1) {
        this.op1 = op1;
    }

    public Expression getOp2() {
        return op2;
    }

    public void setOp2(Expression op2) {
        this.op2 = op2;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
    
    @Override
    public String toString() {
        String res = nom.toString();
        if (nom==listaCI.LABEL || nom==listaCI.JUMP_FALSE || nom==listaCI.JUMP || nom==listaCI.CALL || nom==listaCI.CALL_EXTERN) {
            res = nom.toString()+" "+s;
        }
        if (op!=null){
            res = res.concat(" "+op.toString());
            if (op1!=null){
                res = res.concat(" "+op1.toString());
                if (op2!=null){
                    res = res.concat(" "+op2.toString());
                }
            }
        }
        return res;
    }
    
}
