package com.system.Sistemadeviajes.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.system.Sistemadeviajes.entities.Viaje;

@Repository("viajeRepository")
public interface IViajeRepository extends JpaRepository<Viaje, Serializable>{
	
	public abstract Viaje findByIdViaje(long idViaje);

	@Query(nativeQuery=true,value="select * from Viaje where empleado_id_persona=(:idEmpleado) and cliente_id_persona=(:idCliente) and fecha BETWEEN (:fecha1) and (:fecha2)")
	public abstract List<Viaje> viajesDelEmpladoEntreFachas(long idCliente,long idEmpleado,LocalDate fecha1,LocalDate fecha2);
	
	@Query(nativeQuery=true,value="select * from Viaje where cliente_id_persona=(:idCliente) and fecha BETWEEN (:fecha1) and (:fecha2)")
	public abstract List<Viaje> viajesDelClienteEntreFachas(long idCliente,LocalDate fecha1,LocalDate fecha2);

	@Query(nativeQuery=true,value="select * from Viaje where fecha BETWEEN (:fecha1) and (:fecha2)")
	public abstract List<Viaje> viajesEntreFachas(LocalDate fecha1,LocalDate fecha2);
	
	@Query(nativeQuery=true,value="select * from Viaje where cliente_id_persona=(:idCliente)")
	public abstract List<Viaje> viajesDelCliente(long idCliente);
	
	@Query(nativeQuery=true,value="select * from Viaje where empleado_id_persona=(:idEmpleado)")
	public abstract List<Viaje> viajesDelEmpleado(long idEmpleado);
	
}//Fin class
