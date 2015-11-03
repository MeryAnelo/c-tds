package analizador;

import java.util.LinkedList;

public class Method extends AST {
	private String id;
	private Type tipo;
	private Block body;
	private LinkedList<Parameter> par;

	public Method(Type t, String e,Block body) {
		this.id = e;
		this.tipo = t;
		this.body = body;
		this.par = null;
	}
	
	public Method(Type t, String e, LinkedList<Parameter> parametros, Block body) {
		this.id = e;
		this.tipo = t;
		this.body = body;
		this.par = parametros;
	}
	
	public void setId(String e) {
		this.id = e;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setType(Type t) {
		this.tipo = t;
	}
	
	public Type getType() {
		return this.tipo;
	}
	
	public void setBlock(Block b) {
		this.body = b;
	}
	
	public Block getBlock() {
		return this.body;
	}
	
	public void setParameter(LinkedList<Parameter> par) {
		this.par = par;
	}
	
	public LinkedList<Parameter> getParameter() {
		return this.par;
	}

        
	@Override
	public <T> T accept(ASTVisitor<T> v) {
		return v.visit(this);
	}
}
