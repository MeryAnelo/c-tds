package analizador;

public abstract class Location extends Expression {
    protected String id;
    protected int offset;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
        
    public Location(Expression exp) {
        super(exp);
    }
	
    public void setId(String id) {
            this.id = id;
    }

    public String getId() {
            return id;
    }

    public Expression getExpr() {
        return expr;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }
        
}
