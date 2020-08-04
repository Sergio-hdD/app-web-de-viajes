package com.system.Sistemadeviajes.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Viaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idViaje;
	
	@Column(name = "fecha")
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
    
	@Column(name = "detalle")
	private String detalle;
	
	@Column(name = "contado")
	private boolean contado;
	
	@Column(name = "entregado")
	private boolean entregado;
	
	@Column(name = "descuento")
	private double descuento;
	
	@Column(name = "neto")
	private double neto;
	
	
	public Viaje() { }


	public Viaje(long idViaje, Date fecha, String direccion, String localidad, double importe, Empleado empleado,
			Cliente cliente, String detalle, boolean contado, boolean entregado, double descuento,double neto) {
		super();
		this.idViaje = idViaje;
		this.fecha = fecha;
		this.direccion = direccion;
		this.localidad = localidad;
		this.importe = importe;
		this.empleado = empleado;
		this.cliente = cliente;
		this.detalle = detalle;
		this.contado = contado;
		this.entregado = entregado;
		this.descuento = descuento;
		this.neto = neto;
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


	public boolean isContado() {
		return contado;
	}


	public void setContado(boolean contado) {
		this.contado = contado;
	}


	public boolean isEntregado() {
		return entregado;
	}


	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}


	public double getDescuento() {
		return descuento;
	}


	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}


	public double getNeto() {
		return neto;
	}


	public void setNeto(double neto) {
		this.neto = neto;
	}
	
	

}//fin class