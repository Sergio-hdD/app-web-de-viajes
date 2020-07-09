package com.system.Sistemadeviajes.services;

import java.util.List;
import com.system.Sistemadeviajes.entities.Cliente;
import com.system.Sistemadeviajes.models.ClienteModel;

public interface IClienteService {
	
	public abstract List<Cliente> getAll();

	public ClienteModel insertOrUpdate(ClienteModel clienteModel);

	public Cliente findByIdPersona(long id);

	public boolean remove(long id);

}
