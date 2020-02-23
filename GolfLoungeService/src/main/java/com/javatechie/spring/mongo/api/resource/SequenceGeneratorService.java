package com.javatechie.spring.mongo.api.resource;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.javatechie.spring.mongo.api.model.DatabaseSequence;

@Service
public class SequenceGeneratorService {
	
	@Autowired
	private MongoOperations mongoOperation;
	
	public int generateSequence(String seqName) {
	    DatabaseSequence counter = mongoOperation.findAndModify(query(where("_id").is(seqName)),
	      new Update().inc("seq",1), options().returnNew(true).upsert(true),
	      DatabaseSequence.class);
	    return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}
}
