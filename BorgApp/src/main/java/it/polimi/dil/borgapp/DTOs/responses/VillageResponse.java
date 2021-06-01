package it.polimi.dil.borgapp.DTOs.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class contains the Village object that is sent to the client
 * 
 * @author Andrea Ceresetti
 * @author Antonio De Santis
 * @author Riccardo Campi
 * @author Tommaso Terenghi
 * 
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VillageResponse {
	private Long id;
	private String name;
	private String description;
	private String image;
}
