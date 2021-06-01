package it.polimi.dil.borgapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.polimi.dil.borgapp.models.Village;

/**
 * This class is an interface to the village database with already implemented CRUD operations
 * 
 * @author Andrea Ceresetti
 * @author Antonio De Santis
 * @author Riccardo Campi
 * @author Tommaso Terenghi
 * 
 */

@Repository
public interface VillageRepository extends JpaRepository<Village, Long> {

}
