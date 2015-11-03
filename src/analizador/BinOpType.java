package analizador;

public enum BinOpType {
	PLUS, // Arithmetic
	MINUS,
	MULT,
	DIVIDE,
	DIV,
	GTR, // Relational
	LESS,
	LESS_EQ,
	GTR_EQ,
	EQ, // Equal
	NOT_EQ, 
	AND, // Conditional
	OR;
	
	@Override
	public String toString() {
		switch(this) {
			case PLUS:
				return "+";
			case MINUS:
				return "-";
			case MULT:
				return "*";
			case DIVIDE:
				return "/";
			case DIV:
				return "%";
			case GTR:
				return ">";
			case LESS:
				return "<";
			case LESS_EQ:
				return "<=";
			case GTR_EQ:
				return ">=";
			case EQ:
				return "==";
			case NOT_EQ:
				return "!=";
			case AND:
				return "&&";
			case OR:
				return "||";
		}
		
		return null;
	}
	
}
