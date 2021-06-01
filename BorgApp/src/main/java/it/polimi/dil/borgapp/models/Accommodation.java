package it.polimi.dil.borgapp.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class contains accommodations persistence object
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
public class Accommodation implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=IDENTITY)
	private Long id;
	
	@ManyToOne(optional=false)
	private Village village;
	
	@OneToMany(mappedBy="accommodation")
	private List<Reservation> reservations;
	
}
