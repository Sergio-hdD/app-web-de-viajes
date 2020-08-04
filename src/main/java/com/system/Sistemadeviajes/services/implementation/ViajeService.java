package com.system.Sistemadeviajes.services.implementation;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.system.Sistemadeviajes.converters.ViajeConverter;
import com.system.Sistemadeviajes.entities.Viaje;
import com.system.Sistemadeviajes.models.ClienteModel;
import com.system.Sistemadeviajes.models.EmpleadoModel;
import com.system.Sistemadeviajes.models.ViajeModel;
import com.system.Sistemadeviajes.repositories.IViajeRepository;
import com.system.Sistemadeviajes.services.IViajeService;

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
		viajeModel.setNeto(viajeModel.getImporte() - viajeModel.getDescuento());
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
	public List<Viaje> traerViajesDelEmpleadoEntreFechas(ClienteModel cliente,EmpleadoModel empleado,Date fecha1,Date fecha2){
		 List<Viaje> viajes = viajeRepository.viajesDelEmpladoEntreFachas(cliente.getIdPersona(),empleado.getIdPersona(), fecha1, fecha2);
		 return viajes;
	}
	
	@Override
	public double totalBrutoEntreFechas(ClienteModel cliente,EmpleadoModel empleado,Date fecha1,Date fecha2){
		 List<Viaje> viajes = viajeRepository.viajesDelEmpladoEntreFachas(cliente.getIdPersona(),empleado.getIdPersona(), fecha1, fecha2);
		 double bruto =0; 
		 for(Viaje viaje:viajes) {
			 bruto += viaje.getImporte();
		 }
		 return bruto;
	}
	
	@Override
	public double totalDescuentoEntreFechas(ClienteModel cliente,EmpleadoModel empleado,Date fecha1,Date fecha2){
		 List<Viaje> viajes = viajeRepository.viajesDelEmpladoEntreFachas(cliente.getIdPersona(),empleado.getIdPersona(), fecha1, fecha2);
		 double descuento =0; 
		 for(Viaje viaje:viajes) {
			 descuento += viaje.getDescuento();
		 }
		 return descuento;
	}

	@Override
	public double totalNetoEntreFechas(ClienteModel cliente,EmpleadoModel empleado,Date fecha1,Date fecha2){
		 List<Viaje> viajes = viajeRepository.viajesDelEmpladoEntreFachas(cliente.getIdPersona(),empleado.getIdPersona(), fecha1, fecha2);
		 double neto =0; 
		 for(Viaje viaje:viajes) {
			 neto += viaje.getNeto();
		 }
		 return neto;
	}
	
	

	@Override
	public List<Viaje> traerViajesDelClienteEntreFechas(ClienteModel cliente,Date fecha1,Date fecha2){
		 List<Viaje> viajes = viajeRepository.viajesDelClienteEntreFachas(cliente.getIdPersona(), fecha1, fecha2);
		 return viajes;
	}

	@Override
	public double totalAFacturarEntreFechas(ClienteModel cliente,Date fecha1,Date fecha2){
		 List<Viaje> viajes = viajeRepository.viajesDelClienteEntreFachas(cliente.getIdPersona(), fecha1, fecha2);
		 double total =0; 
		 for(Viaje viaje:viajes) {
			 total += viaje.getImporte();
		 }
		 return total;
	}
	
	@Override
	public double getGananciaViajes() {
		double ganancia = 0;
		
		for(Viaje v : this.getAll()) {
			ganancia += v.getImporte();
		}
		
		return ganancia;
	}
	
	public double getGananciaDelMes() {
		double ganancia = 0;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M");
		
		for(Viaje v : this.getAll()) {
			
			if(Integer.parseInt(simpleDateFormat.format(v.getFecha()))  == LocalDate.now().getMonthValue()) {
				ganancia += v.getImporte();
				
			}
		}
		
		return ganancia;
	}
	

	public double getGananciaEntreFechas(LocalDate fecha1,LocalDate fecha2) {
		double ganancia = 0;
		for(Viaje v : viajeRepository.viajesEntreFachas(fecha1, fecha2)) {			
				ganancia += v.getImporte();
		}
		
		return ganancia;
	}
    
	
	@Override
	public int getCantidadViajesEmpleado(long idPersona) {
		int cantidad = 0;
		
		for(Viaje v : this.getAll()) {
			if(v.getEmpleado().getIdPersona() == idPersona) {
				cantidad ++;
			}
		}
		
		return cantidad;
	}
	
	@Override
	public int getCantidadViajesCliente(long idPersona) {
		int cantidad = 0;
		
		for(Viaje v : this.getAll()) {
			if(v.getCliente().getIdPersona() == idPersona) {
				cantidad ++;
			}
		}
				
		return cantidad;
	}
	
	
}// fin class 