package com.system.Sistemadeviajes.controllers;


import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.system.Sistemadeviajes.helpers.ViewRouteHelpers;
import com.system.Sistemadeviajes.services.IViajeService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	@Qualifier("viajeService")
	private IViajeService viajeService;
	
	@GetMapping({"/home", ""})
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelpers.HOME);	
		return modelAndView;
	}
	
	@GetMapping("/dashboard")
	public ModelAndView dashboard(){
		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.DASHBOARD_INDEX);	
		
		mAV.addObject("mes", LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")));
		mAV.addObject("gananciaMes", viajeService.getGananciaDelMes());
		mAV.addObject("gananciaTotal", viajeService.getGananciaViajes());
		
		
		return mAV;
	}
	
}//fin class