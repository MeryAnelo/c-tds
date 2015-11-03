package analizador;

public class FloatLiteral extends Literal {
	private Float value;

        public FloatLiteral(Float value) {
            super(null);
            this.value = value;
        }
	
	@Override
	public Type getType() {
		return Type.FLOAT;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(float value) {
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
