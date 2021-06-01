package it.polimi.dil.borgapp.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.polimi.dil.borgapp.DTOs.responses.VillageResponse;
import it.polimi.dil.borgapp.mappers.VillageMapper;
import it.polimi.dil.borgapp.models.Reservation;
import it.polimi.dil.borgapp.models.User;
import it.polimi.dil.borgapp.models.Village;
import it.polimi.dil.borgapp.repositories.ReservationRepository;
import it.polimi.dil.borgapp.repositories.UserRepository;
import it.polimi.dil.borgapp.repositories.VillageRepository;
import lombok.AllArgsConstructor;

/**
 * This class handles villages business logic
 * 
 * @author Andrea Ceresetti
 * @author Antonio De Santis
 * @author Riccardo Campi
 * @author Tommaso Terenghi
 * 
 */

@Service
@AllArgsConstructor
public class VillageService {
	
	final static Integer MAX_NUM_OF_SUGGESTED = 3;
	final static Integer MAX_REVIEW_RATING = 5;
	
	// weights of each factor in final score for suggested villages
	final static Integer VIEWS_WEIGHT_PERCENTAGE = 40;
	final static Integer REVIEWS_WEIGHT_PERCENTAGE = 30;
	final static Integer PARTICIPANTS_WEIGHT_PERCENTAGE = 30;

	private final VillageRepository villageRepository;
	private final UserRepository userRepository;
	private final ReservationRepository reservationRepository;
	private final VillageMapper villageMapper;
	
	@Transactional(readOnly = true)
	public List<VillageResponse> getSuggestedVillages() {
		
		List<Village> villages = villageRepository.findAll();
		List<Village> suggestedVillages = computeVillagesToBeSuggested(villages);	
		
		return villageMapper.toVillagesResponse(suggestedVillages);
	}
	
	private List<Village> computeVillagesToBeSuggested(List<Village> villages) {
		if(villages.isEmpty() || villages == null) {
			return List.of();
		}
		
		List<Village> suggestedVillages = new ArrayList<Village>();
		
		Integer numOfSuggested = Math.min(MAX_NUM_OF_SUGGESTED, villages.size());
		
		Float averageViews = Float.valueOf(villages.stream()
				.map(Village::getNumberOfViews)
				.reduce(0, Integer::sum)) / villages.size(); // compute average views
		
		Float averageParticipants = Float.valueOf(reservationRepository.findAll().stream()
				.map(Reservation::getNumOfParticipants)
				.reduce(0, Integer::sum)) / villages.size(); // compute average visitors
		
		// Assuming the logged user is the one with id=1
		User loggedUser = userRepository.findById(Long.valueOf(1)).orElseThrow();
		
		villages.removeAll(loggedUser.getReservations()
				.stream()
				.map(Reservation::getVillage)
				.collect(Collectors.toList())); // remove villages already visited
		
		for(Village village:villages) {
			// calculate scores
			Float viewsScore = (averageViews - village.getNumberOfViews()) / averageViews;
			
			Float participantsScore = (averageParticipants - reservationRepository.findByAccommodation_Village_IdAndStartDateGreaterThan(village.getId(), 
					LocalDate.now().minusMonths(3)) // considering just last 3 months
					.stream()
					.map(Reservation::getNumOfParticipants)
					.reduce(0,  Integer::sum)) / averageParticipants;
			
			Float reviewsScore = village.getAverageRating() / MAX_REVIEW_RATING;
			
			// combine scores
			Float finalScore = viewsScore * VIEWS_WEIGHT_PERCENTAGE +
					participantsScore * PARTICIPANTS_WEIGHT_PERCENTAGE + 
					reviewsScore * REVIEWS_WEIGHT_PERCENTAGE;
			
			village.setSuggestionScore(finalScore);
			
			if(suggestedVillages.size() < numOfSuggested) {
				suggestedVillages.add(village);		
			} else {
				// find village with minimum score and replace it if this village has better score
				Village minScore = null;
				for(Village suggested:suggestedVillages) {
					if(minScore == null || suggested.getSuggestionScore() < minScore.getSuggestionScore()) minScore = suggested;
				}
				if(minScore.getSuggestionScore() < finalScore) { 
					suggestedVillages.remove(minScore);
					suggestedVillages.add(village);
				}
			}
		}
		
		// sort by score
		Collections.sort(suggestedVillages, new Comparator<Village>() {
            @Override
            public int compare(Village v1, Village v2) {
                return Float.compare(v2.getSuggestionScore(), v1.getSuggestionScore());
            }
        });
		
		return suggestedVillages;
	}
}
