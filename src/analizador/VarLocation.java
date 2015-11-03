package analizador;

//import ir.ASTVisitor;

public class VarLocation extends Location {
	private int blockId;
        private Expression expre;
        
        public VarLocation( String id, Expression exp) {
            super(exp);
            this.expre = exp;
            this.id = id;
        }

        public Expression getExpre() {
            return expre;
        }

        public void setExpre(Expression expre) {
            this.expre = expre;
        }
        

	public int getBlockId() {
		return blockId;
	}

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}
	
	@Override
	public String toString() {
		return id;
	}

	@Override
	public <T> T accept(ASTVisitor<T> v) {
		return v.visit(this);
	}
}
