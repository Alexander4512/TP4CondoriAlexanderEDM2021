package ar.edu.unju.edm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping({"/","/login","/home", "/index","/login?error=true"})
	public String cargarHome() {
		return("home");
	}
	
}