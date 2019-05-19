package com.tiket.flight.repo; 

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.tiket.flight.document.Airport;
import com.tiket.flight.document.AirportSubscriber;

public interface AirportSubscriberRepository extends MongoRepository<AirportSubscriber, String>{
	 // AirportSubscriber findBy_id(ObjectId _id);
	  AirportSubscriber findByAirportCode(String airportCode);
}
