package analizador;

import java.util.LinkedList;

public class FieldDeclaration extends AST {
	private Type tipo;
//	private String id;
        private LinkedList<LocationDeclaration> lid;

    public LinkedList<LocationDeclaration> getLid() {
        return lid;
    }

    public void setLid(LinkedList<LocationDeclaration> lid) {
        this.lid = lid;
    }
	
//	public FieldDeclaration(Type tipo, String id) {
//		this.tipo = tipo;
//		this.id = id;
//	}
        
	public FieldDeclaration(Type tipo,LinkedList<LocationDeclaration> l) {
		this.tipo = tipo;
                this.lid=l;
	}
        
	public void setType(Type tipo) {
		this.tipo = tipo;
	}
	
	public Type getType() {
		return this.tipo;
	}
	
//	public void setString(String id) {
//		this.id = id;
//	}
	
//	public String getString() {
//		return this.id;
//	}
//
//	@Override
//	public String toString() {
//		return tipo + " " + id;
//		
//	}

	@Override
	public <T> T accept(ASTVisitor<T> v) {
		return v.visit(this);
	}
}
