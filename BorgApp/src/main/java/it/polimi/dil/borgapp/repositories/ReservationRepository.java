package it.polimi.dil.borgapp.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.polimi.dil.borgapp.models.Reservation;

/**
 * This class is an interface to the reservations database with already implemented CRUD operations
 * 
 * @author Andrea Ceresetti
 * @author Antonio De Santis
 * @author Riccardo Campi
 * @author Tommaso Terenghi
 * 
 */

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	List<Reservation> findByAccommodation_Village_IdAndStartDateGreaterThan(Long villageId, LocalDate startDate);

}