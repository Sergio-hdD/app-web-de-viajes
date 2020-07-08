package com.system.Sistemadeviajes.converters;

import org.springframework.stereotype.Component;

import com.system.Sistemadeviajes.entities.Empleado;
import com.system.Sistemadeviajes.models.EmpleadoModel;

@Component
public class EmpleadoConverter {
	
	public EmpleadoModel entityToModel(Empleado empleado) {
		return new EmpleadoModel(empleado.getIdPersona(),empleado.getDireccion(),empleado.getTelCel(),empleado.getNombre(),empleado.getApellido(),empleado.getTipoDocumento(),empleado.getNroDocumento());
	}
	
	public Empleado modelToEntity(EmpleadoModel empleadoModel) {
		return new Empleado(empleadoModel.getIdPersona(),empleadoModel.getDireccion(),empleadoModel.getTelCel(),empleadoModel.getNombre(),empleadoModel.getApellido(),empleadoModel.getTipoDocumento(),empleadoModel.getNroDocumento());
	}

}
