package com.system.Sistemadeviajes.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ViajeModel {

	private long idViaje;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;
	private String direccion;
	private String localidad;
	private double importe;
	private EmpleadoModel empleado;
	private ClienteModel cliente;
	private String detalle;
	
	
	public ViajeModel() { }


	public ViajeModel(long idViaje, Date fecha, String direccion, String localidad, double importe,
			EmpleadoModel empleado, ClienteModel cliente, String detalle) {
		super();
		this.idViaje = idViaje;
		this.fecha = fecha;
		this.direccion = direccion;
		this.localidad = localidad;
		this.importe = importe;
		this.empleado = empleado;
		this.cliente = cliente;
		this.detalle = detalle;
	}


	public long getIdViajes() {
		return idViaje;
	}


	public void setIdViajes(long idViajes) {
		this.idViaje = idViajes;
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


	public String getDetalle() {
		return detalle;
	}


	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}


	@Override
	public String toString() {
		return "ViajeModel [idViajes=" + idViaje + ", fecha=" + fecha + ", direccion=" + direccion + ", localidad="
				+ localidad + ", importe=" + importe + ", cliente=" + cliente + ", detalle="+ detalle + "]";
	}
	
	
}//fin class