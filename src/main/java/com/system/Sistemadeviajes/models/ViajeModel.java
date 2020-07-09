package com.system.Sistemadeviajes.models;

import java.util.Date;

import org.springframework.lang.Nullable;

public class ViajeModel {

	private long idViajes;
	private Date fecha;
	private String direccion;
	private String localidad;
	private double importe;
	private EmpleadoModel empleado;
	private ClienteModel cliente;
	@Nullable
	private String detalles;
	
	
	public ViajeModel() { }


	public ViajeModel(long idViajes, Date fecha, String direccion, String localidad, double importe,
			EmpleadoModel empleado, ClienteModel cliente) {
		super();
		this.idViajes = idViajes;
		this.fecha = fecha;
		this.direccion = direccion;
		this.localidad = localidad;
		this.importe = importe;
		this.empleado = empleado;
		this.cliente = cliente;
	}


	public long getIdViajes() {
		return idViajes;
	}


	public void setIdViajes(long idViajes) {
		this.idViajes = idViajes;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getLocalidad() {
		return localidad;
	}


	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}


	public double getImporte() {
		return importe;
	}


	public void setImporte(double importe) {
		this.importe = importe;
	}


	public EmpleadoModel getEmpleado() {
		return empleado;
	}


	public void setEmpleado(EmpleadoModel empleado) {
		this.empleado = empleado;
	}


	public ClienteModel getCliente() {
		return cliente;
	}


	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}


	public String getDetalles() {
		return detalles;
	}


	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}


	@Override
	public String toString() {
		return "ViajeModel [idViajes=" + idViajes + ", fecha=" + fecha + ", direccion=" + direccion + ", localidad="
				+ localidad + ", importe=" + importe + ", cliente=" + cliente + ", detalles="+ detalles + "]";
	}
	
	
}//fin class