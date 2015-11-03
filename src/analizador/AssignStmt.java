
package analizador;

public class AssignStmt extends Statement {
	private Location id;
	private Expression expr;
	private AssignOpType operator;

	public AssignStmt(Location id, AssignOpType op, Expression e) {
		this.id = id;
		this.expr = e;
		this.operator = op;
	}
	
	public void setId(Location id) {
		this.id = id;
	}
	
	public Location getId() {
		return this.id;
	}
	
	public void setExpression(Expression e) {
		this.expr = e;
	}
	
	public Expression getExpression() {
		return this.expr;
	}
	
	public AssignOpType getOperator() {
		return operator;
	}

	public void setOperator(AssignOpType operator) {
		this.operator = operator;
	}
	
	@Override
	public String toString() {
		return id + " " + operator + " " + expr;
		
	}

	@Override
	public <T> T accept(ASTVisitor<T> v) {
		return v.visit(this);
	}
}
