package com.system.Sistemadeviajes.services.implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.system.Sistemadeviajes.converters.ViajeConverter;
import com.system.Sistemadeviajes.repositories.IViajeRepository;
import com.system.Sistemadeviajes.services.IViajeService;
import com.system.Sistemadeviajes.entities.Viaje;
import com.system.Sistemadeviajes.models.EmpleadoModel;
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
		try {
			viajeRepository.deleteById(idViaje);
			return true;
		}
		catch(Exception e) {
			return false;
		}
			
	}

	@Override
	public List<Viaje> traerViajesDelEmpleadoEntreFechas(EmpleadoModel empleado,Date fecha1,Date fecha2){
		 List<Viaje> viajes = viajeRepository.viajesDelEmpladoEntreFachas(empleado.getIdPersona(), fecha1, fecha2);
		 return viajes;
	}
	
}// fin class
