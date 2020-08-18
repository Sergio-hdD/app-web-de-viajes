package com.system.Sistemadeviajes.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.system.Sistemadeviajes.converters.EmpleadoConverter;
import com.system.Sistemadeviajes.entities.Empleado;
import com.system.Sistemadeviajes.models.EmpleadoModel;
import com.system.Sistemadeviajes.repositories.IEmpleadoRepository;
import com.system.Sistemadeviajes.repositories.IViajeRepository;
import com.system.Sistemadeviajes.services.IEmpleadoService;
import com.system.Sistemadeviajes.services.IViajeService;
import com.system.Sistemadeviajes.entities.Viaje;
@Service("empleadoService")
public class EmpleadoService implements IEmpleadoService{
	
	@Autowired
	@Qualifier("empleadoRepository")
	private IEmpleadoRepository empleadoRepository;
	
	@Autowired
	@Qualifier("empleadoConverter")
	private EmpleadoConverter empleadoConverter;

	@Autowired
	@Qualifier("viajeRepository")
	private IViajeRepository viajeRepository;
	
	@Autowired
	@Qualifier("viajeService")
	private IViajeService viajeService;
	
	@Override
	public List<Empleado> getAll() {
		// TODO Auto-generated method stub
		return empleadoRepository.findAll();
	}

	@Override
	public void remove(long idPersona) {
		eliminarViajesDelEmpleado(idPersona);//elimino los viajes hechos por el empleado que quiero eliminar
		empleadoRepository.deleteById(idPersona);//elimino el empleado
	}

	public void eliminarViajesDelEmpleado(long idPersona) {
    	List<Viaje> viajes = viajeRepository.viajesDelEmpleado(idPersona); 
		if(viajes!=null) {
			for (Viaje v : viajes ) {
				viajeService.remove(v.getIdViaje());
			}	
		}
	}
	
	@Override
	public EmpleadoModel insertOrUpdate(EmpleadoModel empleadoModel) {
		// TODO Auto-generated method stub
		Empleado e = empleadoRepository.save(empleadoConverter.modelToEntity(empleadoModel));
		
		return empleadoConverter.entityToModel(e);
	}

	@Override
	public EmpleadoModel findByIdPersona(long idPersona) {
		// TODO Auto-generated method stub
		return empleadoConverter.entityToModel(empleadoRepository.findByIdPersona(idPersona)) ;
	}

}
