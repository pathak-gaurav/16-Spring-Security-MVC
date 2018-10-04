package com.atos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String showHome() {
		return "home";
	}
	
	@RequestMapping("/showLoginPage")
	public String showLoginPage(){
		return "login-page";
	}
}
