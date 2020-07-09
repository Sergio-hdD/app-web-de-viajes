package com.system.Sistemadeviajes.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
//@Table(name="cliente")//esto no hace falta si la tabla va a llevar el mismo nombre que la clase
public class Cliente extends Persona{

	@Column(name = "cuit")
	private String cuit;
	
	@Column(name = "razonSocial")
	private String razonSocial;
	
	public Cliente() {}


	public Cliente(long idPersona, String direccion, String telCel, String cuit, String razonSocial) {
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
		return "Cliente [cuit=" + cuit + ", razonSocial=" + razonSocial + "]";
	}
	
}//fin class