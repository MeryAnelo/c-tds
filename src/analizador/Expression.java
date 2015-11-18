package analizador;

public abstract class Expression extends AST {
	protected Expression expr;
	protected Type type;
	
	public Expression (Expression exp){
		this.expr = exp;
	}
	
	public Expression getExpression() {
		return this.expr;
	}
	
	public void setExpression(Expression e) {
		this.expr = e;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public void setType(Type t) {
		this.type = t;
	}
        
        public void setTypeString(String t){
            if(t.equals("int")){
                this.type=Type.INT;
            }else if(t.equals("float")){
                this.type=Type.FLOAT;
            }else if(t.equals("boolean")){
                this.type=Type.BOOLEAN;
            }else if(t.equals("void")){
                this.type=Type.VOID;
            }else{
                this.type=Type.UNDEFINED;
            }
        }
}
