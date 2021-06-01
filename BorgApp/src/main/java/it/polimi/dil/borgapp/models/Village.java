package it.polimi.dil.borgapp.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class contains villages persistence object
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
public class Village implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@Basic(fetch=FetchType.EAGER)	
	@Column(nullable=true)
	private String image;
	
	@Column(nullable = false)
	private Integer numberOfViews;
	
	@OneToMany(mappedBy="village")
	private List<Review> reviews;
	
	@OneToMany(mappedBy="village")
	private List<Accommodation> accommodations;
	
	@Transient
	private Float suggestionScore;
	
	public Float getAverageRating() {
		return Float.valueOf(reviews.stream()
				.map(Review::getRating)
				.reduce(0, Integer::sum)) / reviews.size();		
	}

}
