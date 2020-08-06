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
import com.system.Sistemadeviajes.models.EmpleadoModel;
import com.system.Sistemadeviajes.services.IEmpleadoService;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
	
	@Autowired
	@Qualifier("empleadoService")
	private IEmpleadoService empleadoService;
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.EMPLOYEE_INDEX);
		mAV.addObject("empleados", empleadoService.getAll());

		return mAV;
	}
	
	@PostMapping("/delete")
	public RedirectView delete(EmpleadoModel empleadoModel) {
		empleadoService.remove(empleadoModel.getIdPersona());
		
		return new RedirectView(ViewRouteHelpers.EMPLOYEE_ROOT);
	}
	
	@PostMapping("/add")
	public RedirectView create(EmpleadoModel empleadoModel) {
		empleadoService.insertOrUpdate(empleadoModel);
		
		return new RedirectView(ViewRouteHelpers.EMPLOYEE_ROOT);
	}
	
	@PostMapping("/update")
	public RedirectView update(EmpleadoModel empleadoModel) {
		empleadoService.insertOrUpdate(empleadoModel);
		
		return new RedirectView(ViewRouteHelpers.EMPLOYEE_ROOT);
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public EmpleadoModel get(@PathVariable("id") @RequestBody long idPersona) {
		return empleadoService.findByIdPersona(idPersona);
	}
	

}