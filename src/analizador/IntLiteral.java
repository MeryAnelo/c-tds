package analizador;

//import ir.ASTVisitor;

public class IntLiteral extends Literal {
	private Integer value;

        public IntLiteral(Integer value) {
            super(null);
            this.value = value;
        }
	
	@Override
	public Type getType() {
		return Type.INT;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
        
        @Override
        public String toString(){
            return this.value.toString();
        }
        
	@Override
	public <T> T accept(ASTVisitor<T> v) {
		return v.visit(this);
	}
}
