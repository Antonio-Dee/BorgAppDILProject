package it.polimi.dil.borgapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.polimi.dil.borgapp.models.User;

/**
 * This class is an interface to the users database with already implemented CRUD operations
 * 
 * @author Andrea Ceresetti
 * @author Antonio De Santis
 * @author Riccardo Campi
 * @author Tommaso Terenghi
 * 
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}