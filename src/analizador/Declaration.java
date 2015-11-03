package analizador;

import java.util.LinkedList;

public class Declaration extends AST {
	private LinkedList<FieldDeclaration> dec;
	private LinkedList<Method> met;

	public Declaration(LinkedList<FieldDeclaration> field, LinkedList<Method> metodo) {
		this.dec = field;
		this.met = metodo;
	}

	
	public void setField(FieldDeclaration field) {
		this.dec.add(field);
	}
	
	public LinkedList<FieldDeclaration> getField() {
		return this.dec;
	}
	
	public void setMethod(Method metodo) {
		this.met.add(metodo);
	}
	
	public LinkedList<Method> getMethod() {
		return this.met;
	}

	@Override
	public <T> T accept(ASTVisitor<T> v) {
		return v.visit(this);
	}
}
