package com.system.Sistemadeviajes.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Empleado extends Persona{
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="tipoDocumento")
	private String tipoDocumento;
	
	@Column(name="nroDocumento")
	private long nroDocumento;
	
	
	public Empleado() {
		super();
	}

	public Empleado(long idPersona, String direccion, String telCel, String nombre, String apellido,
			String tipoDocumento, long nroDocumento) {
		super(idPersona, direccion, telCel);
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public long getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(long nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	@Override
	public String toString() {
		return "Empleado [nombre=" + nombre + ", apellido=" + apellido + ", tipoDocumento=" + tipoDocumento
				+ ", nroDocumento=" + nroDocumento + ", idPersona=" + idPersona + ", direccion=" + direccion
				+ ", telCel=" + telCel + "]";
	}
	
	
	
	

}
