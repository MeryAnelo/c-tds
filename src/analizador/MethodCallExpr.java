/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

/**
 *
 * @author alumno
 */
public class MethodCallExpr extends Expression {
    private MethodCall mc;
        
        public MethodCallExpr(MethodCall exp) {
            super(null);
            this.mc=exp;        
        }
                
                
	public MethodCall getMethodCallExpr() {
		return this.mc;
	}
        
        @Override
        public String toString(){
            return mc.toString();
        }
	
	public void setMethodCallExpr(MethodCall e) {
		this.mc = e;
	}
        
        @Override
	public <T> T accept(ASTVisitor<T> v) {
		return v.visit(this);
	}
}
