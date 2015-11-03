package analizador;

public class LocationDeclaration extends AST {
	private String id;
	private Integer literal;

	public LocationDeclaration(String id, Integer length ) {
		this.id = id;
		this.literal = length;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setLiteral(Integer n) {
		this.literal = n;
	}
	
	public Integer getLiteral() {
		return this.literal;
	}

	@Override
	public <T> T accept(ASTVisitor<T> v) {
		return v.visit(this);
	}
}
