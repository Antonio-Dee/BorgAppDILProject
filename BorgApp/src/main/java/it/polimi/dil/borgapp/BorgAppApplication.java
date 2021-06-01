package it.polimi.dil.borgapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class contains the main function from where the server starts
 * 
 * @author Andrea Ceresetti
 * @author Antonio De Santis
 * @author Riccardo Campi
 * @author Tommaso Terenghi
 * 
 */

@SpringBootApplication
public class BorgAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BorgAppApplication.class, args);
	}

}