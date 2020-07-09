package com.system.Sistemadeviajes.services.implementation;

 import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.system.Sistemadeviajes.converters.ViajeConverter;
import com.system.Sistemadeviajes.repositories.IViajeRepository;
import com.system.Sistemadeviajes.services.IViajeService;
import com.system.Sistemadeviajes.entities.Viaje;
import com.system.Sistemadeviajes.models.ViajeModel;

@Service("viajeService")
public class ViajeService implements IViajeService{
	
	@Autowired
	@Qualifier("viajeRepository")
	private IViajeRepository viajeRepository;
	
	@Autowired
	@Qualifier("viajeConverter")
	private ViajeConverter viajeConverter;


	@Override
	public List<Viaje> getAll() {
		return viajeRepository.findAll();
	}


	@Override
	public ViajeModel insertOrUpdate(ViajeModel viajeModel) {
		Viaje v = viajeRepository.save(viajeConverter.modelToEntity(viajeModel));
		return viajeConverter.entityToModel(v);
	}


	@Override
	public Viaje findByIdViaje(long idViaje) {
		return viajeRepository.findByIdViaje(idViaje);
	}


	@Override
	public boolean remove(long idViaje) {

		return false;
	}
	

}// fin class
