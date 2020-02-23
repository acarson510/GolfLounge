package com.javatechie.spring.mongo.api.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection="Golfer")
public class Golfer {
 
	@Transient
	public static final String SEQUENCE_NAME = "golfers_sequence";
	  
	@Id
	private int id;	
	private String firstName;
	private String lastName;
	private String userName;
	private String gender;
	private int age;
	private String school;
	private String resides;
	private String height;
	private String weight;
	private String sponsor;	
	private int handicap; 
	private List<Score> scores;	

	 public int getHandicap() {
	        return this.handicap;
	    }
	 
	 public void setHandicap() {
	        this.handicap = calculateHandicap(this.scores);
	    }
	 
	public int calculateHandicap(List<Score> scores) {
						
		ArrayList<Float> grossScores = calculateGrossScores(getSortedScores(scores));							    
		
		return (int)((sumScores(grossScores)/grossScores.size())*.96);
	}
	
	public List<Score> getSortedScores(List<Score> scores){
		return scores.stream()
				  .sorted(Comparator.comparing(Score::getScore))
				  .limit(20)
				  .collect(Collectors.toList());
	}
	
	public ArrayList<Float> calculateGrossScores(List<Score> scores){
		
		ArrayList<Float> result = new ArrayList<Float>();
		
		for (int i = 0; i < scores.size(); i++) {
			float calculated = 
			    (
			    		(((scores.get(i).getScore())
			    		-
			    		(scores.get(i).getCourse().getCourseRating()))
			    		*113)
			    		/(scores.get(i).getCourse().getCourseSlope())
			     );
			
			result.add(calculated);
		}
		
		return result;
	}
	
	public double sumScores(ArrayList<Float> scores) {
		double result = 0;
		for(int i = 0; i < scores.size(); i++)
		    result += scores.get(i);	
		
		return result;
	}
	

	
	
	
	  
}
