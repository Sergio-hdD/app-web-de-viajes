package com.system.Sistemadeviajes.services;

import java.time.LocalDate;
import java.util.List;

import com.system.Sistemadeviajes.entities.Viaje;
import com.system.Sistemadeviajes.models.ClienteModel;
import com.system.Sistemadeviajes.models.EmpleadoModel;
import com.system.Sistemadeviajes.models.ViajeModel;


public interface IViajeService {
	
	public abstract List<Viaje> getAll();
	
	public ViajeModel insertOrUpdate(ViajeModel viajeModel);
	
	public Viaje findByIdViaje(long idViaje);

	public boolean remove(long idViaje);
	
	public List<Viaje> traerViajesDeCliEmpleEntreFechas(ClienteModel cliente,EmpleadoModel empleado, LocalDate fecha1,LocalDate fecha2);
	
	public List<Viaje> resumenViajesDelEmpleadoEntreFechas(EmpleadoModel empleado,LocalDate fecha1,LocalDate fecha2);
	
	public List<Viaje> viajesEntreFechas(LocalDate fecha1,LocalDate fecha2);
		
	public Viaje totalesResumenViajes(EmpleadoModel empleado, LocalDate fecha1,LocalDate fecha2);
	
	public double totalBrutoEntreFechas(ClienteModel cliente,EmpleadoModel empleado,LocalDate fecha1,LocalDate fecha2);	
	
	public double totalDescuentoEntreFechas(ClienteModel cliente,EmpleadoModel empleado,LocalDate fecha1,LocalDate fecha2);
	
	public double totalNetoEntreFechas(ClienteModel cliente,EmpleadoModel empleado,LocalDate fecha1,LocalDate fecha2);
	
	public List<Viaje> traerViajesDelClienteEntreFechas(ClienteModel cliente,LocalDate fecha1,LocalDate fecha2);
	
	public double totalAFacturarEntreFechas(ClienteModel cliente,LocalDate fecha1,LocalDate fecha2);
	
	public double getGananciaViajes();
	
	public int getCantidadViajesEmpleado(long idPersona);
	
	public int getCantidadViajesCliente(long idPersona);
	
	public double getGananciaDelMes();
	
	public double getGananciaEntreFechas(LocalDate fecha1,LocalDate fecha2); 	
	
	
}//fin interface