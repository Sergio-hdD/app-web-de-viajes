package com.system.Sistemadeviajes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.system.Sistemadeviajes.helpers.ViewRouteHelpers;
import com.system.Sistemadeviajes.services.IEmpleadoService;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
	
	@Autowired
	@Qualifier("empleadoService")
	private IEmpleadoService empleadoService;
	
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.EMPLOYEE_INDEX);
		
		return mAV;
	}

}
