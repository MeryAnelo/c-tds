package analizador;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//import ir.ASTVisitor;

public class Block extends Statement {
	private LinkedList<FieldDeclaration> dec;
	private LinkedList<Statement> statements;
	
	
	public Block(LinkedList<FieldDeclaration> dec,LinkedList<Statement> stmt) {
		this.statements = stmt;
		this.dec = dec;
	}
	
	public void setField(LinkedList<FieldDeclaration> d){
		this.dec = d;
	}
	
	public LinkedList<FieldDeclaration> getField(){
		return this.dec;
	}
	
	public void addStatement(Statement s) {
		this.statements.add(s);
	}
		
	public LinkedList<Statement> getStatements() {
		return this.statements;
	} 
	
	@Override
	public String toString() {
		String rtn = "";
		
	    for (Statement s: statements) {
			rtn += s.toString() + '\n';
		}
		
		if (rtn.length() > 0) return rtn.substring(0, rtn.length() - 1); // remove last new line char
		
		return rtn; 
	}

	@Override
	public <T> T accept(ASTVisitor<T> v) {
		return v.visit(this);
	}
	
}
