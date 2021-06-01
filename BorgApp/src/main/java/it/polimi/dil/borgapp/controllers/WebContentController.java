package it.polimi.dil.borgapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

/**
 * This class is used to show the web pages
 * 
 * @author Andrea Ceresetti
 * @author Antonio De Santis
 * @author Riccardo Campi
 * @author Tommaso Terenghi
 *
 */
@Controller
@AllArgsConstructor
public class WebContentController {
	
	@GetMapping("/")
	public String goToHome() {
		return "home";
	}
}