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
public class MethodCall extends Statement{
    
    private String id;
//    private Parameter param;
    private LinkedList<Expression> lParam;
    
    
     public MethodCall(String id, LinkedList<Expression> param) {
        //super(null);
        this.id = id;
        this.lParam=param;
        
    }

    public LinkedList<Expression> getlParam() {
        return lParam;
    }

    public void setlParam(LinkedList<Expression> lParam) {
        this.lParam = lParam;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @Override
    public <T> T accept(ASTVisitor<T> v) {
            return v.visit(this);
    }
}
