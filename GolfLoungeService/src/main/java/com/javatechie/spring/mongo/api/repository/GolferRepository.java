package com.javatechie.spring.mongo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.javatechie.spring.mongo.api.model.Golfer;

public interface GolferRepository extends MongoRepository<Golfer, Integer>{

}

