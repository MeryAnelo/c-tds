package analizador;
//import ir.ASTVisitor;

public class UnaryOpExpr extends Expression {
	private UnaryOpType operator; //operator in the expr = expr operator expr
	private Expression lOperand; //left expression

        public UnaryOpExpr(UnaryOpType operator, Expression lOperand) {
            super(lOperand);
            this.operator = operator;
            this.lOperand = lOperand;
        }
       
	public UnaryOpType getOperator() {
		return operator;
	}

	public void setOperator(UnaryOpType operator) {
		this.operator = operator;
	}

	public Expression getLeftOperand() {
		return lOperand;
	}

	public void setLeftOperand(Expression lOperand) {
		this.lOperand = lOperand;
	}

	@Override
	public String toString() {
		return operator + " " + lOperand;
	}

	@Override
	public <T> T accept(ASTVisitor<T> v) {
		return v.visit(this);
	}
}
