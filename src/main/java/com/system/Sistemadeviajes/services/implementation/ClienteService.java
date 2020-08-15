package com.system.Sistemadeviajes.services.implementation;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.system.Sistemadeviajes.converters.ClienteConverter;
import com.system.Sistemadeviajes.entities.Cliente;
import com.system.Sistemadeviajes.entities.Viaje;
import com.system.Sistemadeviajes.models.ClienteModel;
import com.system.Sistemadeviajes.repositories.IClienteRepository;
import com.system.Sistemadeviajes.repositories.IViajeRepository;
import com.system.Sistemadeviajes.services.IClienteService;
import com.system.Sistemadeviajes.services.IViajeService;


@Service("clienteService")
public class ClienteService implements IClienteService{

	@Autowired
	@Qualifier("clienteRepository")
	private IClienteRepository clienteRepository;
	
	@Autowired
	@Qualifier("clienteConverter")
	private ClienteConverter clienteConverter;

	@Autowired
	@Qualifier("viajeRepository")
	private IViajeRepository viajeRepository;
	
	@Autowired
	@Qualifier("viajeService")
	private IViajeService viajeService;

	@Override
	public List<Cliente> getAll() {
		return clienteRepository.findAll();
	}

	@Override
	public ClienteModel insertOrUpdate(ClienteModel clienteModel) {
		Cliente cliente = clienteRepository.save(clienteConverter.modelToEntity(clienteModel));
		return clienteConverter.entityToModel(cliente);
	}
	
	@Override
	public ClienteModel findByIdPersona(long id) {
		return clienteConverter.entityToModel(clienteRepository.findByIdPersona(id));
	}
 
	@Override
	public void remove(long id) {
		eliminarViajesDelCliente(id);//elimino los viajes hechos para el cliente que quiero eliminar
	    clienteRepository.deleteById(id);//elimino el cliente
	}

	public void eliminarViajesDelCliente(long idPersona) {
		List<Viaje> viajes = viajeRepository.viajesDelCliente(idPersona); 
		if(viajes!=null) {
			for (Viaje v : viajes ) {
				viajeService.remove(v.getIdViaje());
			}	
		}	
	}

}
