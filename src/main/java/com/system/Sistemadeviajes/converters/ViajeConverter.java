package com.system.Sistemadeviajes.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.system.Sistemadeviajes.entities.Viaje;
import com.system.Sistemadeviajes.models.ViajeModel;

@Component("viajeConverter")
public class ViajeConverter {
	
	@Autowired
	@Qualifier("clienteConverter")
	private ClienteConverter clienteConverter;
	
	@Autowired
	@Qualifier("empleadoConverter")
	private EmpleadoConverter empleadoConverter;
	
	public ViajeModel entityToModel(Viaje viaje) {
		return new ViajeModel(viaje.getIdViaje(),viaje.getFecha(),viaje.getDireccion(),viaje.getLocalidad(),viaje.getImporte(),empleadoConverter.entityToModel(viaje.getEmpleado()),clienteConverter.entityToModel(viaje.getCliente()),viaje.getDetalle(),viaje.isContado(),viaje.isEntregado(),viaje.getDescuento(),viaje.getNeto());
	}
	
	public Viaje modelToEntity(ViajeModel viajeModel) {
		return new Viaje(viajeModel.getIdViajes(),viajeModel.getFecha(),viajeModel.getDireccion(),viajeModel.getLocalidad(),viajeModel.getImporte(),empleadoConverter.modelToEntity(viajeModel.getEmpleado()),clienteConverter.modelToEntity(viajeModel.getCliente()),viajeModel.getDetalle(),viajeModel.isContado(),viajeModel.isEntregado(),viajeModel.getDescuento(),viajeModel.getNeto());
	}

}
