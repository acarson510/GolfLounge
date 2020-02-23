package com.javatechie.spring.mongo.api.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection="Lounge")
public class Lounge {
 
	@Transient
	public static final String SEQUENCE_NAME = "lounge_sequence";
	
	@Id
	private int id;	
	private String loungeName;
	private List<Golfer> golfers;		
}