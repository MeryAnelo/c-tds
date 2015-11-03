package analizador;

public class ForStmt extends Statement {
	private String inicio;
	private Expression condition;
	private Expression forBlock;
	private Statement s;
	
	public ForStmt(String ini, Expression cond, Expression forBlock, Statement state) {
		this.inicio = ini;
		this.forBlock = forBlock;
		this.condition = cond;
		this.s = state;
	}
	
	public Statement getStatement(){
		return s;
	}

	public void setStatement(Statement state){
		this.s = state;
	}
	
	public String getInicio() {
		return inicio;
	}

	public void setInicio(String ini) {
		this.inicio = ini;
	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public Expression getForBlock() {
		return forBlock;
	}

	public void setforBlock(Expression forBlock) {
		this.forBlock = forBlock;
	}
	
	@Override
	public String toString() {
		String rtn = "for " + condition + '\n' + forBlock.toString();
		return rtn;
	}

	@Override
	public <T> T accept(ASTVisitor<T> v) {
		return v.visit(this);
	}
}
