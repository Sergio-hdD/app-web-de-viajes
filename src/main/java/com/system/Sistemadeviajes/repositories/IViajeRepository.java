package com.system.Sistemadeviajes.repositories;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.system.Sistemadeviajes.entities.Viaje;

@Repository("viajeRepository")
public interface IViajeRepository extends JpaRepository<Viaje, Serializable>{
	
	public abstract Viaje findByIdViaje(long idViaje);

	@Query(nativeQuery=true,value="select * from Viaje where empleado_id_persona=(:idEmpleado) and fecha BETWEEN (:fecha1) and (:fecha2)")
	public abstract List<Viaje> viajesDelEmpladoEntreFachas(long idEmpleado,Date fecha1,Date fecha2);
	
}
