package com.system.Sistemadeviajes.repositories;


 import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.system.Sistemadeviajes.entities.Cliente;


@Repository("clienteRepository")
public interface IClienteRepository extends JpaRepository<Cliente, Serializable>{
	public abstract Cliente findByIdPersona(int id);

}//Fin class
