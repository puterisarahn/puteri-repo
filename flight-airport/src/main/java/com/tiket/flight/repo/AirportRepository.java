package com.tiket.flight.repo; 

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.tiket.flight.document.Airport;

public interface AirportRepository extends MongoRepository<Airport, String>{
	  Airport findBy_id(ObjectId _id);
}
