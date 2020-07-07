package com.system.Sistemadeviajes.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) 
public class Persona { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int idPersona;
	
	@Column(name = "direccion")
	protected String direccion;
	
	@Column(name = "apellido")
	protected String telCel;
	
	
	Persona(){}


	public Persona(int idPersona, String direccion, String telCel) {
		super();
		this.idPersona = idPersona;
		this.direccion = direccion;
		this.telCel = telCel;
	}


	public int getIdPersona() {
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
		return "Persona [idPersona=" + idPersona + ", direccion=" + direccion + ", telCel=" + telCel + "]";
	}

}
