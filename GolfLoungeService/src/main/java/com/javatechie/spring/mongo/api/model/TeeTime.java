package com.javatechie.spring.mongo.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection="TeeTime")
public class TeeTime {
 
	@Transient
	public static final String SEQUENCE_NAME = "teeTime_sequence";
	  
	@Id
	private int id;	
	private String course;
	private String dateTime;
	private int availableSlots;
	private Float price;
	private boolean walking;
	
	 public int getId() {
	        return this.id;
	    }
	 
	 public void setId(int id) {
	        this.id = id;
	    }
}
