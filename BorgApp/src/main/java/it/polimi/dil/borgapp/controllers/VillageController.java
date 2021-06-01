package it.polimi.dil.borgapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.polimi.dil.borgapp.DTOs.responses.VillageResponse;
import it.polimi.dil.borgapp.services.VillageService;
import lombok.AllArgsConstructor;

/**
 * This class handles client requests related to villages
 * 
 * @author Andrea Ceresetti
 * @author Antonio De Santis
 * @author Riccardo Campi
 * @author Tommaso Terenghi
 * 
 */

@RestController
@AllArgsConstructor
public class VillageController {
	
	private final VillageService villageService;
	
	@GetMapping("/villages/suggested")
	public List<VillageResponse> getSuggestedVillages(){
		return villageService.getSuggestedVillages();
	}
	
}
