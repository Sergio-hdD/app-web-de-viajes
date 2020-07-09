package com.system.Sistemadeviajes.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.lang.Nullable;


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
	
	
	@Nullable
	@OneToMany(fetch=FetchType.LAZY,mappedBy="viaje")
	private Set<Viaje> viajes=new HashSet<Viaje>();
	
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

	public Set<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(Set<Viaje> viajes) {
		this.viajes = viajes;
	}

	@Override
	public String toString() {
		return "Empleado [nombre=" + nombre + ", apellido=" + apellido + ", tipoDocumento=" + tipoDocumento
				+ ", nroDocumento=" + nroDocumento + ", idPersona=" + idPersona + ", direccion=" + direccion
				+ ", telCel=" + telCel + "]";
	}
	
	
	
	

}
