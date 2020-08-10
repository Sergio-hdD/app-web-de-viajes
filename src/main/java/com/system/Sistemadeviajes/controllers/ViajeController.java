package com.system.Sistemadeviajes.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
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

import com.system.Sistemadeviajes.entities.Cliente;
import com.system.Sistemadeviajes.entities.Empleado;
import com.system.Sistemadeviajes.entities.Viaje;
import com.system.Sistemadeviajes.helpers.ViewRouteHelpers;
import com.system.Sistemadeviajes.models.CantidadViajes;
import com.system.Sistemadeviajes.models.ClienEmpleModel;
import com.system.Sistemadeviajes.models.ClienteModel;
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

	@PostMapping("/delete")
	public RedirectView delete(ViajeModel viajeModel) {
		viajeService.remove(viajeModel.getIdViaje());
				
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
	
	
//---------------------------------consulta por empleado, cliente y fechas--------------------------------------
	@GetMapping("/getCliEmplYFechas")
	public ModelAndView pedirCliEmpleYFecha() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.TRAVEL_PEDIRFECHAS_CLI_EMPL);
		mAV.addObject("empleados", empleadoService.getAll());
		mAV.addObject("clientes",clienteService.getAll());
		return mAV;
	}	
    
	ClienEmpleModel cliEmplGlobal = new ClienEmpleModel();
	LocalDate fecha1Global;
	LocalDate fecha2Global;
	@RequestMapping(value = "/cliEmpleYFechas", method = RequestMethod.POST)
	public ModelAndView traerCliEmpleYFechas(@ModelAttribute("clienEmpleModel") ClienEmpleModel clienEmpleModel,
			@RequestParam("fecha1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha1,
			@RequestParam("fecha2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha2, Model model) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.TRAVEL_CLI_EMPL_ENTRE_FECHAS);
		cliEmplGlobal = clienEmpleModel;   	fecha1Global = fecha1;   fecha2Global = fecha2;
		ClienteModel cliente = clienteService.findByIdPersona(clienEmpleModel.getCliente().getIdPersona());
		EmpleadoModel empleado = empleadoService.findByIdPersona(clienEmpleModel.getEmpleado().getIdPersona());
		mAV.addObject("empleado",empleado);
		mAV.addObject("cliente",cliente);
		List<Viaje> viajes = viajeService.traerViajesDelEmpleadoEntreFechas(cliente,empleado,fecha1, fecha2); 
	    mAV.addObject("cantidad",viajes.size());
	    mAV.addObject("bruto", viajeService.totalBrutoEntreFechas(cliente,empleado,fecha1, fecha2));
	    mAV.addObject("descuento", viajeService.totalDescuentoEntreFechas(cliente,empleado,fecha1, fecha2));
	    mAV.addObject("neto", viajeService.totalNetoEntreFechas(cliente,empleado,fecha1, fecha2));
		mAV.addObject("fecha1",fecha1.getDayOfMonth()+"/"+fecha1.getMonthValue()+"/"+fecha1.getYear());//para que la fecha se muestre "dd/mm/yyyy" y no "yyyy-mm-dd"
		mAV.addObject("fecha2",fecha2.getDayOfMonth()+"/"+fecha2.getMonthValue()+"/"+fecha2.getYear());//para que la fecha se muestre "dd/mm/yyyy" y no "yyyy-mm-dd"
		mAV.addObject("viajes",viajes);
		return mAV;
	}
//-----------------------------fin consulta por empleado, cliente y fechas--------------------------------------
	
//---------------------------------consulta por cliente y fechas--------------------------------------
	@GetMapping("/clienteYFechas")
	public ModelAndView pedirClieYFecha() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.TRAVEL_PEDIRFECHAS_CLI);
		mAV.addObject("clientes", clienteService.getAll());
		return mAV;
	}	
    long idCliGoblal=0;
	@RequestMapping(value = "/devolverClienteYFechas", method = RequestMethod.POST)
	public ModelAndView traerClieYFechas(@ModelAttribute("clienteModel") ClienteModel clienteModel,
			@RequestParam("fecha1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha1,
			@RequestParam("fecha2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha2, Model model) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.TRAVEL_CLI_ENTRE_FECHAS);
		idCliGoblal = clienteModel.getIdPersona();   fecha1Global = fecha1;   fecha2Global = fecha2;
		ClienteModel cliente = clienteService.findByIdPersona(clienteModel.getIdPersona());
		mAV.addObject("cliente",cliente);
		List<Viaje> viajes = viajeService.traerViajesDelClienteEntreFechas(cliente,fecha1, fecha2); 
	    mAV.addObject("cantidad",viajes.size());
	    mAV.addObject("imporTotal", viajeService.totalAFacturarEntreFechas(cliente,fecha1, fecha2));
		mAV.addObject("fecha1",fecha1.getDayOfMonth()+"/"+fecha1.getMonthValue()+"/"+fecha1.getYear());//para que la fecha se muestre "dd/mm/yyyy" y no "yyyy-mm-dd"
		mAV.addObject("fecha2",fecha2.getDayOfMonth()+"/"+fecha2.getMonthValue()+"/"+fecha2.getYear());//para que la fecha se muestre "dd/mm/yyyy" y no "yyyy-mm-dd"
		mAV.addObject("viajes",viajes);
		return mAV;
	}
//-----------------------------fin consulta por cliente y fechas--------------------------------------
	
//-------------------------------para imprimir/descargar------------------------
	@GetMapping("/imprDowlCliEmpl")
	public ModelAndView printDownloadCliEmp() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.TRAVEL_P_D_CLIE_EMPL);
		ClienteModel cliente = clienteService.findByIdPersona(cliEmplGlobal.getCliente().getIdPersona());
		EmpleadoModel empleado = empleadoService.findByIdPersona(cliEmplGlobal.getEmpleado().getIdPersona());
		mAV.addObject("empleado",empleado);
		mAV.addObject("cliente",cliente);
		List<Viaje> viajes = viajeService.traerViajesDelEmpleadoEntreFechas(cliente,empleado,fecha1Global, fecha2Global); 
	    mAV.addObject("cantidad",viajes.size());
	    mAV.addObject("bruto", viajeService.totalBrutoEntreFechas(cliente,empleado,fecha1Global, fecha2Global));
	    mAV.addObject("descuento", viajeService.totalDescuentoEntreFechas(cliente,empleado,fecha1Global, fecha2Global));
	    mAV.addObject("neto", viajeService.totalNetoEntreFechas(cliente,empleado,fecha1Global, fecha2Global));
		mAV.addObject("viajes",viajes);
		mAV.addObject("fecha1",fecha1Global.getDayOfMonth()+"/"+fecha1Global.getMonthValue()+"/"+fecha1Global.getYear());//para que la fecha se muestre "dd/mm/yyyy" y no "yyyy-mm-dd"
		mAV.addObject("fecha2",fecha2Global.getDayOfMonth()+"/"+fecha2Global.getMonthValue()+"/"+fecha2Global.getYear());//para que la fecha se muestre "dd/mm/yyyy" y no "yyyy-mm-dd"
		return mAV;
	}
	
	@GetMapping("/imprDowlCliente")
	public ModelAndView printDownloadCliente() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.TRAVEL_P_D_CLIENTE);
		ClienteModel cliente = clienteService.findByIdPersona(idCliGoblal);		
		mAV.addObject("cCcliente",cliente);
		List<Viaje> viajes = viajeService.traerViajesDelClienteEntreFechas(cliente,fecha1Global, fecha2Global); 
	    mAV.addObject("cCcantidad",viajes.size());
	    mAV.addObject("imporTotal", viajeService.totalAFacturarEntreFechas(cliente,fecha1Global, fecha2Global));
		mAV.addObject("cCviajes",viajes);
		mAV.addObject("fecha1",fecha1Global.getDayOfMonth()+"/"+fecha1Global.getMonthValue()+"/"+fecha1Global.getYear());//para que la fecha se muestre "dd/mm/yyyy" y no "yyyy-mm-dd"
		mAV.addObject("fecha2",fecha2Global.getDayOfMonth()+"/"+fecha2Global.getMonthValue()+"/"+fecha2Global.getYear());//para que la fecha se muestre "dd/mm/yyyy" y no "yyyy-mm-dd"
		return mAV;
	}
	
	
//---------------------------fin para imprimir/descargar------------------------				
	
	@GetMapping("/cantidadViajesEmpleados")
	@ResponseBody
	public ArrayList<CantidadViajes> cantidadViajesEmpleados(){
		ArrayList<CantidadViajes> lstCantidadViajes = new ArrayList<CantidadViajes>();
		
		for(Empleado e : empleadoService.getAll()) {
			if(viajeService.getCantidadViajesEmpleado(e.getIdPersona())> 0) {
				lstCantidadViajes.add(new CantidadViajes(e.getNombre() + " " + e.getApellido(),viajeService.getCantidadViajesEmpleado(e.getIdPersona())));
			}
		}
		
		
		return lstCantidadViajes;
	}
	
	@GetMapping("/cantidadViajesClientes")
	@ResponseBody
	public ArrayList<CantidadViajes> cantidadViajesClientes(){
		ArrayList<CantidadViajes> lstCantidadViajes = new ArrayList<CantidadViajes>();
		
		for(Cliente c : clienteService.getAll()) {
			if(viajeService.getCantidadViajesCliente(c.getIdPersona())> 0) {
				lstCantidadViajes.add(new CantidadViajes(c.getRazonSocial(),viajeService.getCantidadViajesCliente(c.getIdPersona())));
			}
		}
		
		return lstCantidadViajes;
	}
	
	
	
	
}//fin class
