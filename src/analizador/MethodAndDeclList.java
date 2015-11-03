/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

/**
 *
 * @author alumno
 */
public class MethodAndDeclList {
    public String name;
    public String type;

    MethodAndDeclList(String id, String ty) {
        name=id;
        type=ty;
    }

    MethodAndDeclList() {
        name=null;
        type=null;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    @Override
	public String toString() {
            return "nombre: "+name+" - tipo: "+type;
	}
    
    
}
