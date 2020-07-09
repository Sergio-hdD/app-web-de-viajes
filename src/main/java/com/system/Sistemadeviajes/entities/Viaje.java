package com.system.Sistemadeviajes.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;


@Entity
public class Viaje {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idViaje;
	
	@Column(name = "fecha")
	@CreationTimestamp
	private Date fecha;
	
	@Column(name = "direccion")
	private String direccion;	
	
	@Column(name = "localidad")
	private String localidad;
	
	@Column(name = "importe")
	private double importe;	
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Empleado empleado;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Cliente cliente;
    
	@Nullable
	@Column(name = "detalle")
	private String detalle;
	
	
	
	public Viaje() { }


	public Viaje(long idViaje, Date fecha, String direccion, String localidad, double importe, Empleado empleado,
			Cliente cliente) {
		super();
		this.idViaje = idViaje;
		this.fecha = fecha;
		this.direccion = direccion;
		this.localidad = localidad;
		this.importe = importe;
		this.empleado = empleado;
		this.cliente = cliente;
	}


	public long getIdViaje() {
		return idViaje;
	}



	public void setIdViaje(long idViaje) {
		this.idViaje = idViaje;
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



	public Empleado getEmpleado() {
		return empleado;
	}



	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public String getDetalle() {
		return detalle;
	}



	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	

}//fin class
