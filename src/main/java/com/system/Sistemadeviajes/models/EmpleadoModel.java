package com.system.Sistemadeviajes.models;

import java.util.Set;

import org.springframework.lang.Nullable;

import com.system.Sistemadeviajes.entities.Viaje;

public class EmpleadoModel extends PersonaModel{
	
	private String nombre;
	
	private String apellido;
	
	private String tipoDocumento;
	
	private long nroDocumento;
	
    @Nullable
    private Set<Viaje> viajes;
	
	public EmpleadoModel() {
		super();
	}

	public EmpleadoModel(long idPersona, String direccion, String telCel, String nombre, String apellido,
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
