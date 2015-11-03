package analizador;
//import ir.ASTVisitor;

public class WhileStmt extends Statement {
	private Expression condition;
	private Statement s;
	
	public WhileStmt(Expression cond,Statement state) {
		this.condition = cond;
		this.s = state;
	}
	
	public Statement getStatement(){
		return s;
	}

	public void setStatement(Statement state){
		this.s = state;
	}
	
	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

//	@Override
//	public String toString() {
//		String rtn = "while " + condition + '\n' + forBlock.toString();
//		return rtn;
//	}
//
	@Override
	public <T> T accept(ASTVisitor<T> v) {
		return v.visit(this);
	}
        
}
