package analizador;

public class ExternStmt extends Statement {
	//private Expression expression; // the extern expression
	private MethodCall mc;

        public ExternStmt() {
            this.mc = null;
        }

        public MethodCall getMc() {
            return mc;
        }

        public void setMc(MethodCall mc) {
            this.mc = mc;
        }
        
        
//	public ExternStmt() {
//		this.expression = null;
//	}
//
//	public Expression getExpression() {
//		return expression;
//	}
//
//	public void setExpression(Expression expression) {
//		this.expression = expression;
//	}
//	
//	@Override
//	public String toString() {
//		if (expression == null) {
//			return "Extern";
//		}
//                return "";
//	}

	@Override
	public <T> T accept(ASTVisitor<T> v) {
		return v.visit(this);
	}
}
