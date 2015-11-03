/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

/**
 *
 * @author juan
 */
public class ClassDec extends AST{
    private String id;
    private Declaration body;

    public ClassDec(String id, Declaration body) {
        this.id = id;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Declaration getBody() {
        return body;
    }

    public void setBody(Declaration body) {
        this.body = body;
    }
    
    @Override
    public <T> T accept(ASTVisitor<T> v) {
            return v.visit(this);
    }
}
