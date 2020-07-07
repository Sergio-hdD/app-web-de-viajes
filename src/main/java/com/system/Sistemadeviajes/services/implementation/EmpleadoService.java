package com.system.Sistemadeviajes.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.system.Sistemadeviajes.entities.Empleado;
import com.system.Sistemadeviajes.repositories.IEmpleadoRepository;
import com.system.Sistemadeviajes.services.IEmpleadoService;

@Service("empleadoService")
public class EmpleadoService implements IEmpleadoService{
	
	@Autowired
	@Qualifier("empleadoRepository")
	private IEmpleadoRepository empleadoRepository;

	@Override
	public List<Empleado> getAll() {
		// TODO Auto-generated method stub
		return empleadoRepository.findAll();
	}

}
