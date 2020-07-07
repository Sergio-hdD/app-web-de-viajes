package com.system.Sistemadeviajes.models;

public abstract class PersonaModel {
	
protected long idPersona;
protected String direccion;
protected String telCel;

public PersonaModel() {}

public PersonaModel(long idPersona, String direccion, String telCel) {
	super();
	this.idPersona = idPersona;
	this.direccion = direccion;
	this.telCel = telCel;
}

public long getIdPersona() {
	return idPersona;
}

public void setIdPersona(int idPersona) {
	this.idPersona = idPersona;
}

public String getDireccion() {
	return direccion;
}

public void setDireccion(String direccion) {
	this.direccion = direccion;
}

public String getTelCel() {
	return telCel;
}

public void setTelCel(String telCel) {
	this.telCel = telCel;
}

@Override
public String toString() {
	return "PersonaModel [idPersona=" + idPersona + ", direccion=" + direccion + ", telCel=" + telCel + "]";
}

}//fin class
