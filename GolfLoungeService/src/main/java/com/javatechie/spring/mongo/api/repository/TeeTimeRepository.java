package com.javatechie.spring.mongo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.javatechie.spring.mongo.api.model.TeeTime;

public interface TeeTimeRepository extends MongoRepository<TeeTime, Integer>{

}