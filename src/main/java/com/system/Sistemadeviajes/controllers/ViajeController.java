package com.system.Sistemadeviajes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.system.Sistemadeviajes.entities.Viaje;
import com.system.Sistemadeviajes.helpers.ViewRouteHelpers;
import com.system.Sistemadeviajes.models.ViajeModel;
import com.system.Sistemadeviajes.services.IClienteService;
import com.system.Sistemadeviajes.services.IEmpleadoService;
import com.system.Sistemadeviajes.services.IViajeService;

@Controller
@RequestMapping("/viajes")
public class ViajeController {

	@Autowired
	@Qualifier("viajeService")
	private IViajeService viajeService;
	
	@Autowired
	@Qualifier("empleadoService")
	private IEmpleadoService empleadoService;
	
	@Autowired
	@Qualifier("clienteService")
	private IClienteService clienteService;

	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.TRAVEL_INDEX);
		mAV.addObject("viajes", viajeService.getAll());
		mAV.addObject("empleados", empleadoService.getAll());
		mAV.addObject("clientes", clienteService.getAll());
		
		return mAV;
	}

	@PostMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") long idViaje) {
				
		return new RedirectView(ViewRouteHelpers.TRAVEL_ROOT);
	}
	
	@PostMapping("/add")
	public RedirectView create(ViajeModel viajeModel) {
		viajeService.insertOrUpdate(viajeModel);
		return new RedirectView(ViewRouteHelpers.TRAVEL_ROOT);
	}
	

	@PostMapping("/update")
	public RedirectView update(ViajeModel viajeModel) {
		
		viajeModel.setCliente(clienteService.findByIdPersona(viajeModel.getCliente().getIdPersona()));
		viajeModel.setEmpleado(empleadoService.findByIdPersona(viajeModel.getEmpleado().getIdPersona()));
		
		viajeService.insertOrUpdate(viajeModel);

		return new RedirectView(ViewRouteHelpers.TRAVEL_ROOT);
	}	
	
	@GetMapping("/{id}")
	@ResponseBody
	public Viaje get(@PathVariable("id") @RequestBody long idViaje) {
	    return viajeService.findByIdViaje(idViaje);
	}


}//fin class
