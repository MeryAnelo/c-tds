package analizador;

public abstract class Literal extends Expression {

    public Literal(Expression exp) {
        super(exp);
    }
	/*
	 * @return: returns Type of Literal instance
	 */
	public abstract Type getType();
}
