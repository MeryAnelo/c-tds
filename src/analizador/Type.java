package analizador;


public enum Type {
	INT,
	FLOAT,
	BOOLEAN,
	INTARRAY,
	VOID,
	UNDEFINED;
        
        
	
	@Override
	public String toString() {
		switch(this) {
			case INT:
				return "int";
			case VOID:
				return "void";
			case UNDEFINED:
				return "undefined";
			case INTARRAY:
				return "int[]";
			case FLOAT:
				return "float";
			case BOOLEAN:
				return "boolean";
		}
		
		return null;
	}
	
	public boolean isArray() {
		if (this == Type.INTARRAY) {
			return true;
		}
		
		return false;
	}
        
        public boolean isVoid(){
            if (this == Type.VOID) {
                return true;
            }else{
                return false;
            }
        }
	
}
