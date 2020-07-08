package com.system.Sistemadeviajes.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.system.Sistemadeviajes.converters.EmpleadoConverter;
import com.system.Sistemadeviajes.entities.Empleado;
import com.system.Sistemadeviajes.models.EmpleadoModel;
import com.system.Sistemadeviajes.repositories.IEmpleadoRepository;
import com.system.Sistemadeviajes.services.IEmpleadoService;

@Service("empleadoService")
public class EmpleadoService implements IEmpleadoService{
	
	@Autowired
	@Qualifier("empleadoRepository")
	private IEmpleadoRepository empleadoRepository;
	
	@Autowired
	@Qualifier("empleadoConverter")
	private EmpleadoConverter empleadoConverter;

	@Override
	public List<Empleado> getAll() {
		// TODO Auto-generated method stub
		return empleadoRepository.findAll();
	}

	@Override
	public void remove(long idPersona) {
		// TODO Auto-generated method stub
		empleadoRepository.deleteById(idPersona);
	}

	@Override
	public EmpleadoModel insertOrUpdate(EmpleadoModel empleadoModel) {
		// TODO Auto-generated method stub
		Empleado e = empleadoRepository.save(empleadoConverter.modelToEntity(empleadoModel));
		
		return empleadoConverter.entityToModel(e);
	}

	@Override
	public Empleado findByIdPersona(long idPersona) {
		// TODO Auto-generated method stub
		return empleadoRepository.findByIdPersona(idPersona);
	}

}
