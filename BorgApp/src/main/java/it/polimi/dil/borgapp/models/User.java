package it.polimi.dil.borgapp.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class contains user persistence object
 * 
 * @author Andrea Ceresetti
 * @author Antonio De Santis
 * @author Riccardo Campi
 * @author Tommaso Terenghi
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy="user")
	private List<Reservation> reservations;
	
}