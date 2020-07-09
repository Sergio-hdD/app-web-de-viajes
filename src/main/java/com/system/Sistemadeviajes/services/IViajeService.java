package com.system.Sistemadeviajes.services;

import java.util.List;

import com.system.Sistemadeviajes.entities.Viaje;
import com.system.Sistemadeviajes.models.ViajeModel;

public interface IViajeService {
	
	public abstract List<Viaje> getAll();
	
	public ViajeModel insertOrUpdate(ViajeModel viajeModel);
	
	public Viaje findByIdViaje(long idViaje);

	public boolean remove(long idViaje);
	

}//fin interface

