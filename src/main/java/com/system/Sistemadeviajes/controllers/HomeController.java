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
		//LocalDate fechaActual = LocalDate.of(2020, 5, 19);//LA USÉ PARA PROBAR
		LocalDate fechaActual = LocalDate.now(); //obtengo la fecha actual
		LocalDate inicioSem = fechaActual.minusDays(fechaActual.getDayOfWeek().getValue()-1);// si el número de día de la semana de la fecha actual es x yo quiero que a la fecha actual le reste x-1 (para tomar el primero de la semana)
		LocalDate finSem = fechaActual.plusDays(7-fechaActual.getDayOfWeek().getValue());// si el número de día de la semana de la fecha actual es x yo quiero que a la fecha actual le sume 7-x (para tomar el último de la semana) 
	
		mAV.addObject("inicioSemana",inicioSem);
		mAV.addObject("finSemana",finSem);
		mAV.addObject("mes", LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")));
		mAV.addObject("gananciaSemanal", viajeService.getGananciaEntreFechas(inicioSem, finSem));
		mAV.addObject("gananciaMes", viajeService.getGananciaDelMes());
		mAV.addObject("gananciaTotal", viajeService.getGananciaViajes());
		
		
		return mAV;
	}
	
	@GetMapping({"/test/template"})
	public ModelAndView testTemplate() {
		ModelAndView modelAndView = new ModelAndView("home/testTemplate");	
		return modelAndView;
	}
	
}//fin class