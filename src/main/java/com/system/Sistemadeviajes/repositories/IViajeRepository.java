package com.system.Sistemadeviajes.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.Sistemadeviajes.entities.Viaje;

@Repository("viajeRepository")
public interface IViajeRepository extends JpaRepository<Viaje, Serializable>{
	
	public abstract Viaje findByIdViaje(long idViaje);

	
}
