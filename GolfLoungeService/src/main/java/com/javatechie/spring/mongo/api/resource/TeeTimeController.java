package com.javatechie.spring.mongo.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.spring.mongo.api.model.TeeTime;
import com.javatechie.spring.mongo.api.repository.TeeTimeRepository;

import lombok.var;

@RestController
public class TeeTimeController {
	
	@Autowired
	private TeeTimeRepository repository;
	@Autowired
	SequenceGeneratorService sequenceGenerator;
	
	@PostMapping("/create/teeTime")
	public ResponseEntity<TeeTime> saveTeeTime(@RequestBody TeeTime teeTime) {					
		teeTime.setId(sequenceGenerator.generateSequence(teeTime.SEQUENCE_NAME));
		repository.save(teeTime);
		return new ResponseEntity<TeeTime>(teeTime,HttpStatus.OK);	
	}
	
	@GetMapping("/get/teeTimes")
	public ResponseEntity<List<TeeTime>> getTeeTimes() {		
		var teeTimes = repository.findAll();
		
		if(teeTimes == null) {
			return new ResponseEntity<List<TeeTime>>(HttpStatus.NOT_FOUND);
		}			
		return new ResponseEntity<List<TeeTime>>(teeTimes,HttpStatus.OK);
	}
	
	@GetMapping("/get/teeTime/{id}")
	public ResponseEntity<TeeTime> getTeeTime(@PathVariable int id) {		
		
		var teeTime = repository.findById(id);		
		
		if(!teeTime.isPresent()) {
			return new ResponseEntity<TeeTime>(HttpStatus.NOT_FOUND);
		}			
		return new ResponseEntity<TeeTime>(teeTime.get(),HttpStatus.OK);			
	}
	
	@PutMapping("/update/teeTime")
	public ResponseEntity<TeeTime> updateGolfer(@RequestBody TeeTime teeTime) {			
		repository.save(teeTime);
		return new ResponseEntity<TeeTime>(teeTime,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/teeTime/{id}")
	public String deleteTeeTime(@PathVariable int id) {		
		repository.deleteById(id);
		return "TeeTime deleted with id : " +id;
	}
}
