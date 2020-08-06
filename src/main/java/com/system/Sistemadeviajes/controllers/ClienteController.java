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

import com.system.Sistemadeviajes.helpers.ViewRouteHelpers;
import com.system.Sistemadeviajes.models.ClienteModel;
import com.system.Sistemadeviajes.services.IClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	@Qualifier("clienteService")
	private IClienteService clienteService;
	
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.CLIENT_INDEX);
		mAV.addObject("clientes", clienteService.getAll());
		
		return mAV;
	}
	
	
	@PostMapping("/delete")
	public RedirectView eliminar(ClienteModel clienteModel) {
		clienteService.remove(clienteModel.getIdPersona());
		
		return new RedirectView(ViewRouteHelpers.CLIENT_ROOT);
	}
	
	@PostMapping("/create")//add
	public RedirectView agregar(ClienteModel cliente) {
		clienteService.insertOrUpdate(cliente);
		
		return new RedirectView(ViewRouteHelpers.CLIENT_ROOT);
	}
	
	
	@PostMapping("/update")
	public RedirectView update(ClienteModel clienteModel) {
		clienteService.insertOrUpdate(clienteModel);
		
		return new RedirectView(ViewRouteHelpers.CLIENT_ROOT);
	}	
	
	
	@GetMapping("/{id}")
	@ResponseBody
	public ClienteModel get(@PathVariable("id") @RequestBody long idPersona) {
		return clienteService.findByIdPersona(idPersona);
	}
	
	
}//fin class	
