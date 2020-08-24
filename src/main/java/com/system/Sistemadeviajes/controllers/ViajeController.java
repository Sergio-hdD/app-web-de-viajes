package com.system.Sistemadeviajes.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	

//------------------------------BLOQUE DE COONSULTAS E IMPRIMIR/DESCARGAR PDF -----------------------------------	
//---------------------------------consulta por empleado, cliente y fechas--------------------------------------
	@GetMapping("/getCliEmplYFechas")
	public ModelAndView pedirCliEmpleYFecha() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.TRAVEL_PEDIRFECHAS_CLI_EMPL);
		mAV.addObject("empleados", empleadoService.getAll());
		mAV.addObject("clientes",clienteService.getAll());
		return mAV;
	}	
    
	long idCliGlobal=0;
	long idEmpleGlobal=0;
	LocalDate fecha1Global;
	LocalDate fecha2Global;
	@RequestMapping(value = "/cliEmpleYFechas", method = RequestMethod.POST)
	public ModelAndView traerCliEmpleYFechas(@ModelAttribute("clienEmpleModel") ClienEmpleModel clienEmpleModel,
			@RequestParam("fecha1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha1,
			@RequestParam("fecha2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha2, Model model) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.TRAVEL_CLI_EMPL_ENTRE_FECHAS);
		fecha1Global = fecha1;   fecha2Global = fecha2; 
		idCliGlobal=clienEmpleModel.getCliente().getIdPersona();  idEmpleGlobal=clienEmpleModel.getEmpleado().getIdPersona();
		ClienteModel cliente = clienteService.findByIdPersona(clienEmpleModel.getCliente().getIdPersona());
		EmpleadoModel empleado = empleadoService.findByIdPersona(clienEmpleModel.getEmpleado().getIdPersona());
		mAV.addObject("empleado",empleado);
		mAV.addObject("cliente",cliente);
		List<Viaje> viajes = viajeService.traerViajesDeCliEmpleEntreFechas(cliente,empleado,fecha1, fecha2); 
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
    
	@RequestMapping(value = "/devolverClienteYFechas", method = RequestMethod.POST)
	public ModelAndView traerClieYFechas(@ModelAttribute("clienteModel") ClienteModel clienteModel,
			@RequestParam("fecha1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha1,
			@RequestParam("fecha2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha2, Model model) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.TRAVEL_CLI_ENTRE_FECHAS);
		idCliGlobal = clienteModel.getIdPersona();   fecha1Global = fecha1;   fecha2Global = fecha2;
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
	
//---------------------------------consulta por empleado y fechas--------------------------------------
	@GetMapping("/empleadoYFechas")
	public ModelAndView pedirEmpleYFecha() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.TRAVEL_PEDIRFECHAS_EMPLE);
		mAV.addObject("empleados", empleadoService.getAll());
		return mAV;
	}	

	@RequestMapping(value = "/devolverEmpleadoYFechas", method = RequestMethod.POST)
	public ModelAndView traerEmpleYFechas(@ModelAttribute("clienteModel") EmpleadoModel empleadoModel,
			@RequestParam("fecha1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha1,
			@RequestParam("fecha2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha2, Model model) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.TRAVEL_RES_EMPLE_ENTRE_FECHAS);
		idCliGlobal = empleadoModel.getIdPersona();   fecha1Global = fecha1;   fecha2Global = fecha2;
		EmpleadoModel empleado = empleadoService.findByIdPersona(empleadoModel.getIdPersona());
		mAV.addObject("empleado",empleado);
		List<Viaje> viajes = viajeService.resumenViajesDelEmpleadoEntreFechas(empleado,fecha1, fecha2);
		Viaje viaje = viajeService.totalesResumenViajes(empleado, fecha1, fecha2);
		mAV.addObject("fecha1",fecha1.getDayOfMonth()+"/"+fecha1.getMonthValue()+"/"+fecha1.getYear());//para que la fecha se muestre "dd/mm/yyyy" y no "yyyy-mm-dd"
		mAV.addObject("fecha2",fecha2.getDayOfMonth()+"/"+fecha2.getMonthValue()+"/"+fecha2.getYear());//para que la fecha se muestre "dd/mm/yyyy" y no "yyyy-mm-dd"
		mAV.addObject("viajes",viajes);
		mAV.addObject("viajeResumen",viaje);
		return mAV;
	}
	
	@GetMapping("/paraConsulta/{id}")
	public ModelAndView traerCliAndEmpleYFechas(@PathVariable("id") long idViaje) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.TRAVEL_CLI_EMPL_ENTRE_FECHAS);
		Viaje viaje = viajeService.findByIdViaje(idViaje);
		idCliGlobal= viaje.getCliente().getIdPersona();     idEmpleGlobal= viaje.getEmpleado().getIdPersona();//AMBAS (idCliGlobal y idEmpleGlobal) LAS USO EN OTRA FUNCIÓN además de en esta
		ClienteModel cliente = clienteService.findByIdPersona(idCliGlobal);
		EmpleadoModel empleado = empleadoService.findByIdPersona(idEmpleGlobal);
		mAV.addObject("empleado",empleado);
		mAV.addObject("cliente",cliente);
		List<Viaje> viajes = viajeService.traerViajesDeCliEmpleEntreFechas(cliente,empleado,fecha1Global, fecha2Global); 
	    mAV.addObject("cantidad",viajes.size());
	    mAV.addObject("bruto", viajeService.totalBrutoEntreFechas(cliente,empleado,fecha1Global, fecha2Global));
	    mAV.addObject("descuento", viajeService.totalDescuentoEntreFechas(cliente,empleado,fecha1Global, fecha2Global));
	    mAV.addObject("neto", viajeService.totalNetoEntreFechas(cliente,empleado,fecha1Global, fecha2Global));
		mAV.addObject("fecha1",fecha1Global.getDayOfMonth()+"/"+fecha1Global.getMonthValue()+"/"+fecha1Global.getYear());//para que la fecha se muestre "dd/mm/yyyy" y no "yyyy-mm-dd"
		mAV.addObject("fecha2",fecha2Global.getDayOfMonth()+"/"+fecha2Global.getMonthValue()+"/"+fecha2Global.getYear());//para que la fecha se muestre "dd/mm/yyyy" y no "yyyy-mm-dd"
		mAV.addObject("viajes",viajes);
		return mAV;
	}
	
//-----------------------------fin consulta por empleado y fechas--------------------------------------	

	
//-------------------------------para imprimir/descargar------------------------
	@GetMapping("/imprDowlCliEmpl")
	public ModelAndView printDownloadCliEmp() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.TRAVEL_P_D_CLIE_EMPL);
		ClienteModel cliente = clienteService.findByIdPersona(idCliGlobal);
		EmpleadoModel empleado = empleadoService.findByIdPersona(idEmpleGlobal);
		mAV.addObject("empleado",empleado);
		mAV.addObject("cliente",cliente);
		List<Viaje> viajes = viajeService.traerViajesDeCliEmpleEntreFechas(cliente,empleado,fecha1Global, fecha2Global);
	    mAV.addObject("cantidad",viajes.size());
	    mAV.addObject("bruto", viajeService.totalBrutoEntreFechas(cliente,empleado,fecha1Global, fecha2Global));
	    mAV.addObject("descuento", viajeService.totalDescuentoEntreFechas(cliente,empleado,fecha1Global, fecha2Global));
	    mAV.addObject("neto", viajeService.totalNetoEntreFechas(cliente,empleado,fecha1Global, fecha2Global));
		mAV.addObject("fecha1",fecha1Global.getDayOfMonth()+"/"+fecha1Global.getMonthValue()+"/"+fecha1Global.getYear());//para que la fecha se muestre "dd/mm/yyyy" y no "yyyy-mm-dd"
		mAV.addObject("fecha2",fecha2Global.getDayOfMonth()+"/"+fecha2Global.getMonthValue()+"/"+fecha2Global.getYear());//para que la fecha se muestre "dd/mm/yyyy" y no "yyyy-mm-dd"
        mAV.addObject("listaDeListasViajes",crearListaDeListaViaje(viajes));
		return mAV;
	}
	
	@GetMapping("/imprDowlCliente")
	public ModelAndView printDownloadCliente() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.TRAVEL_P_D_CLIENTE);
		ClienteModel cliente = clienteService.findByIdPersona(idCliGlobal);		
		mAV.addObject("cliente",cliente);
		List<Viaje> viajes = viajeService.traerViajesDelClienteEntreFechas(cliente,fecha1Global, fecha2Global); 
	    mAV.addObject("cantidad",viajes.size());
	    mAV.addObject("imporTotal", viajeService.totalAFacturarEntreFechas(cliente,fecha1Global, fecha2Global));
		mAV.addObject("fecha1",fecha1Global.getDayOfMonth()+"/"+fecha1Global.getMonthValue()+"/"+fecha1Global.getYear());//para que la fecha se muestre "dd/mm/yyyy" y no "yyyy-mm-dd"
		mAV.addObject("fecha2",fecha2Global.getDayOfMonth()+"/"+fecha2Global.getMonthValue()+"/"+fecha2Global.getYear());//para que la fecha se muestre "dd/mm/yyyy" y no "yyyy-mm-dd"
        mAV.addObject("listaDeListasViajes",crearListaDeListaViaje(viajes));
		return mAV;
	}
	
	public List <ArrayList<Viaje>> crearListaDeListaViaje(List<Viaje> viajes){//separo los viajes en grupos de 23 el primer grupo, los siguentes de 29
		// Creo una lista de listas de Viajes. Cada lista tendrá hasta max 23 items el primer grupo, los siguentes de 29 (seran 23 o 29) Viajes por hoja
        List <ArrayList<Viaje>> listaDeListas = new ArrayList<ArrayList<Viaje>>();
         int cursorListas = -1;
         for(int i=0; i<viajes.size(); i++) {     
              if(i == 0 || i == 23) {//cuando i es 0 y cuando llega a 23 
                   listaDeListas.add(new ArrayList<Viaje>());//crea una nuava lista (dónde se van a guardar 23 viajes en la que se crea en 0) y la agrega a la listaDeListas
                   cursorListas++;
              } else if((i-23) % 29 == 0) {//cuando llega a 23, creo la lista y empieza a guardar de a 29 y legado a ese 29 crea otra lista a la que también le cargará 29 viajes
	                listaDeListas.add(new ArrayList<Viaje>());//crea una nuava lista (dónde se van a guardar 29 viajes) y la agrega a la listaDeListas
	                cursorListas++;
              }
              listaDeListas.get( cursorListas ).add( viajes.get(i) );//se agrega un viaje a la lista que le corresponde en la listaDeListas
          //EJEMPLO: si son 90 viajes, crea LISTA, en el viaje 0 (con 23), en el viaje 23 (con 29), en el viaje 52 (con 29), en el viaje 81 (con los 9 restante)... y esas listas las agrega a "listaDeListas"   
         }	
       return listaDeListas;  
	}
	
//---------------------------fin para imprimir/descargar------------------------				
//------------------------------FIN BLOQUE DE COONSULTAS E IMPRIMIR/DESCARGAR PDF -----------------------------------	
	
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
	public ResponseEntity<ArrayList<CantidadViajes>> cantidadViajesClientes(){
		ArrayList<CantidadViajes> lstCantidadViajes = new ArrayList<CantidadViajes>();
				
		for(Cliente c : clienteService.getAll()) {
			if(viajeService.getCantidadViajesCliente(c.getIdPersona())> 0) {
				lstCantidadViajes.add(new CantidadViajes(c.getRazonSocial(),viajeService.getCantidadViajesCliente(c.getIdPersona())));
			}
		}
		
		return new ResponseEntity<ArrayList<CantidadViajes>>(lstCantidadViajes, HttpStatus.OK);
	}
	
	
	
	
}//fin class
