package analizador;

public class Parameter extends AST {
	private String id;
	private Type tipo;
        private VarLocation var;
	public Parameter(Type t, String e) {
            this.id = e;
            this.tipo = t;
            var = new VarLocation(id,null);
            var.setType(t);
	}

        public VarLocation getVar() {
            return var;
        }

        public void setVar(VarLocation var) {
            this.var = var;
        }
	
	public void setId(String e) {
		this.id = e;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setType(Type t) {
		this.tipo = t;
	}
	
	public Type getType() {
		return this.tipo;
	}
        
        public void setTypeString(String t){
            if(t.equals("int")){
                this.tipo=Type.INT;
            }else if(t.equals("float")){
                this.tipo=Type.FLOAT;
            }else if(t.equals("boolean")){
                this.tipo=Type.BOOLEAN;
            }else{
                this.tipo=Type.UNDEFINED;
            }
        }

	@Override
	public <T> T accept(ASTVisitor<T> v) {
		return v.visit(this);
	}
}
