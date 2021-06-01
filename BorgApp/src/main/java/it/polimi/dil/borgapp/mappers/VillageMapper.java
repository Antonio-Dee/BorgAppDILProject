package it.polimi.dil.borgapp.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.polimi.dil.borgapp.DTOs.responses.VillageResponse;
import it.polimi.dil.borgapp.models.Village;

/**
 * This class maps villages persistence model to villages data transfer objects
 * 
 * @author Andrea Ceresetti
 * @author Antonio De Santis
 * @author Riccardo Campi
 * @author Tommaso Terenghi
 * 
 */

@Mapper(componentModel = "spring")
public interface VillageMapper {

	@Mapping(target = "id", source="id")
	@Mapping(target = "name", source="name")
	@Mapping(target = "description", source="description")
	@Mapping(target = "image", source="image")
	public VillageResponse toVillageResponse(Village village);
	
	public List<VillageResponse> toVillagesResponse(List<Village> villages);

}
