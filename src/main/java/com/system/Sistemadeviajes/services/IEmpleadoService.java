package com.system.Sistemadeviajes.services;

import java.util.List;

import com.system.Sistemadeviajes.entities.Empleado;
import com.system.Sistemadeviajes.models.EmpleadoModel;

public interface IEmpleadoService {
	
	public List<Empleado> getAll();
	
	public void remove(long idPersona);
	
	public EmpleadoModel insertOrUpdate(EmpleadoModel empleadoModel);
	
	public EmpleadoModel findByIdPersona(long idPersona);

	

}
