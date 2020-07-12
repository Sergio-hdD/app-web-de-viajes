package com.system.Sistemadeviajes.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.system.Sistemadeviajes.helpers.ViewRouteHelpers;

@Controller
@RequestMapping("/")
public class HomeController {
	
		
	@GetMapping("/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelpers.HOME);	
		return modelAndView;
	}
}//fin class