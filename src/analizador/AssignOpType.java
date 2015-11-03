package analizador;

public enum AssignOpType {
	AUTOIN,
	AUTODEC,
	ASSMNT;
	
	@Override
	public String toString() {
		switch(this) {
			case AUTOIN:
				return "+=";
			case AUTODEC:
				return "-=";
			case ASSMNT:
				return "=";
		}
		
		return null;		
	}
	
//	@Override
//	public <T> T accept(ASTVisitor<T> v) {
//		return v.visit(this);
//	}
}
