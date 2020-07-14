package com.system.Sistemadeviajes.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.system.Sistemadeviajes.entities.Viaje;
import com.system.Sistemadeviajes.helpers.ViewRouteHelpers;
import com.system.Sistemadeviajes.models.EmpleadoModel;
import com.system.Sistemadeviajes.models.ViajeModel;
import com.system.Sistemadeviajes.repositories.IEmpleadoRepository;
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
	@Qualifier("empleadoRepository")
	private IEmpleadoRepository empleadoRepository;
	
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
		viajeService.remove(idViaje);
				
		return new RedirectView(ViewRouteHelpers.TRAVEL_ROOT);
	}
	
	@PostMapping("/add")
	public RedirectView create(ViajeModel viajeModel) {
		viajeService.insertOrUpdate(viajeModel);
		return new RedirectView(ViewRouteHelpers.TRAVEL_ROOT);
	}
	

	@PostMapping("/update")
	public RedirectView update(ViajeModel viajeModel) {
		
		System.out.println(viajeModel.isContado());
		
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

	
	@GetMapping("/empleadoYFechas")
	public ModelAndView pedirLocalYFecha() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.TRAVEL_PEDIRFECHAS);
		mAV.addObject("empleados", empleadoService.getAll());
		return mAV;
	}	

	@RequestMapping(value = "/devolverEmpleadoYFechas", method = RequestMethod.POST)
	public ModelAndView traerLocalYFechas(@ModelAttribute("empleadoModel") EmpleadoModel empleadoModel,
			@RequestParam("fecha1") @DateTimeFormat(pattern = "yy-MM-dd") Date fecha1,
			@RequestParam("fecha2") @DateTimeFormat(pattern = "yy-MM-dd") Date fecha2, Model model) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.TRAVEL_EMPLEADO_ENTRE_FECHAS);
		EmpleadoModel empleado = empleadoService.findByIdPersona(empleadoModel.getIdPersona());
		mAV.addObject("fecha1",new SimpleDateFormat("dd-MM-yyyy").format(fecha1));//se mostraba "Fri  May  15    00:00:00" y yo quería "dd-mm-yyyy"
		mAV.addObject("fecha2",new SimpleDateFormat("dd-MM-yyyy").format(fecha2));//se mostraba "Fri  May  15    00:00:00" y yo quería "dd-mm-yyyy"
		mAV.addObject("empleado",empleado);
		List<Viaje> viajes = viajeService.traerViajesDelEmpleadoEntreFechas(empleado,fecha1, fecha2); 
	    mAV.addObject("cantidad",viajes.size());
		mAV.addObject("viajes",viajes);
		return mAV;
	}	
	
}//fin class
