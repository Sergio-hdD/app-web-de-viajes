package com.system.Sistemadeviajes.services;

import java.util.List;

import com.system.Sistemadeviajes.entities.Empleado;

public interface IEmpleadoService {
	
	public List<Empleado> getAll();
	
	public void remove(long idPersona);

}
