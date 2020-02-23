package com.javatechie.spring.mongo.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection="Course")
public class Course {
	@Id
	private int id;	
	private String courseName;
	private int courseSlope;
	private int courseRating;	
}
