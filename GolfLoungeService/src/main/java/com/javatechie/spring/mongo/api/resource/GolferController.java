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

import com.javatechie.spring.mongo.api.model.Golfer;
import com.javatechie.spring.mongo.api.repository.GolferRepository;

import lombok.var;

@RestController
public class GolferController {
	
	@Autowired
	private GolferRepository repository;
	@Autowired
	SequenceGeneratorService sequenceGenerator;
	
	@PostMapping("/create/golfer")
	public ResponseEntity<Golfer> saveGolfer(@RequestBody Golfer golfer) {	
		golfer.setHandicap();
		golfer.setId(sequenceGenerator.generateSequence(golfer.SEQUENCE_NAME));
		repository.save(golfer);
		return new ResponseEntity<Golfer>(golfer,HttpStatus.OK);
	}
	
	@GetMapping("/get/golfers")
	public ResponseEntity<List<Golfer>> getGolfers() {		
		var golfers = repository.findAll();
		
		if(golfers == null) {
			return new ResponseEntity<List<Golfer>>(HttpStatus.NOT_FOUND);
		}			
		return new ResponseEntity<List<Golfer>>(golfers,HttpStatus.OK);
	}
	
	@GetMapping("/get/golfer/{id}")
	public ResponseEntity<Golfer> getGolfer(@PathVariable int id) {		
		
		var golfer = repository.findById(id);		
		
		if(!golfer.isPresent()) {
			return new ResponseEntity<Golfer>(HttpStatus.NOT_FOUND);
		}			
		return new ResponseEntity<Golfer>(golfer.get(),HttpStatus.OK);			
	}
	
	@PutMapping("/update/golfer")
	public ResponseEntity<Golfer> updateGolfer(@RequestBody Golfer golfer) {	
		golfer.setHandicap();
		repository.save(golfer);
		return new ResponseEntity<Golfer>(golfer,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/golfer/{id}")
	public String deleteGolfer(@PathVariable int id) {		
		repository.deleteById(id);
		return "Golfer deleted with id : " +id;
	}
}
