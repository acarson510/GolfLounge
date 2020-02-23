package com.javatechie.spring.mongo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.javatechie.spring.mongo.api.model.Lounge;

public interface LoungeRepository extends MongoRepository<Lounge, Integer>{

}