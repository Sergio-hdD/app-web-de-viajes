package com.system.Sistemadeviajes.models;

public class ClienteModel extends PersonaModel{
	
	private String cuit;
	
	private String razonSocial;

    public ClienteModel(){}

    
    
	public ClienteModel(long idPersona, String direccion, String telCel, String cuit, String razonSocial) {
		super(idPersona, direccion, telCel);
		this.cuit = cuit;
		this.razonSocial = razonSocial;
	}



	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	@Override
	public String toString() {
		return "ClienteModel [cuit=" + cuit + ", razonSocial=" + razonSocial + "]";
	}
    
}//fin class
