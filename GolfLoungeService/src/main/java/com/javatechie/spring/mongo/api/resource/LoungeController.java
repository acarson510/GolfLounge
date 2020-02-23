package com.javatechie.spring.mongo.api.resource;

import java.util.List;
import java.util.Optional;

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
import com.javatechie.spring.mongo.api.model.Lounge;
import com.javatechie.spring.mongo.api.repository.LoungeRepository;

import lombok.var;

@RestController
public class LoungeController {
	
	@Autowired
	private LoungeRepository repository;
	@Autowired
	SequenceGeneratorService sequenceGenerator;
	
	@PostMapping("/create/lounge")
	public ResponseEntity<Lounge> saveGolfer(@RequestBody Lounge lounge) {	
		lounge.setId(sequenceGenerator.generateSequence(lounge.SEQUENCE_NAME));
		repository.save(lounge);
		return new ResponseEntity<Lounge>(lounge,HttpStatus.OK);
	}
	
	@GetMapping("/get/lounges")
	public ResponseEntity<List<Lounge>> getLounges() {		
		var lounges = repository.findAll();
		
		if(lounges == null) {
			return new ResponseEntity<List<Lounge>>(HttpStatus.NOT_FOUND);
		}			
		return new ResponseEntity<List<Lounge>>(lounges,HttpStatus.OK);
	}
	
	@GetMapping("/get/lounge/{id}")
	public ResponseEntity<Lounge> getLounge(@PathVariable int id) {		
		var lounge = repository.findById(id);
		
		if(lounge == null) {
			return new ResponseEntity<Lounge>(HttpStatus.NOT_FOUND);
		}			
		return new ResponseEntity<Lounge>(lounge.get(),HttpStatus.OK);
	}
	
	@PutMapping("/update/Lounge")
	public ResponseEntity<Lounge> updateGolfer(@RequestBody Lounge lounge) {			
		repository.save(lounge);		
		return new ResponseEntity<Lounge>(lounge,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/lounge/{id}")
	public String deleteLounge(@PathVariable int id) {		
		repository.deleteById(id);
		return "Lounge deleted with id : " +id;
	}
}

