package analizador;

public class BoolLiteral extends Literal {
	private Boolean value;

        public BoolLiteral(Boolean value) {
            super(null);
            this.value = value;
        }

	@Override
	public Type getType() {
		return Type.BOOLEAN;
	}

	public Boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
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
