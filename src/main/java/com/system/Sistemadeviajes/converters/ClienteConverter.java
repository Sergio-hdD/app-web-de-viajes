package com.system.Sistemadeviajes.converters;


import org.springframework.stereotype.Component;

import com.system.Sistemadeviajes.entities.Cliente;
import com.system.Sistemadeviajes.models.ClienteModel;


@Component("clienteConverter")
public class ClienteConverter {
	
	public ClienteModel entityToModel(Cliente cliente) {
		return new ClienteModel(cliente.getIdPersona(),cliente.getDireccion(),cliente.getTelCel(),cliente.getCuit(),cliente.getRazonSocial());
	}
	
	public Cliente modelToEntity(ClienteModel clienteModel) {
		return new Cliente(clienteModel.getIdPersona(),clienteModel.getDireccion(),clienteModel.getTelCel(),clienteModel.getCuit(),clienteModel.getRazonSocial());
	}

}
