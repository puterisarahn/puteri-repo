package com.tiket.flight.rest;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiket.flight.document.Airport;
import com.tiket.flight.repo.AirportRepository;
import com.tiket.flight.service.Publisher;

@RestController
@RequestMapping("/airport")
public class AirportServiceController {

	private AirportRepository airportRepository;
	@Autowired
	Publisher publisher;
	
	
	public AirportServiceController(AirportRepository airportRepository) {
		this.airportRepository = airportRepository;
	}

	
/*	@Autowired
	public AirportServiceController(Publisher publisher) {
		this.publisher = publisher;
	}
*/
/*	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestParam("message") String message){
		this.publisher.sendMessage(message);
	}
*/
	
	
	@RequestMapping(value = "/publish-airport", method = RequestMethod.POST)
	public void sendAirportObjToKafkaTopic(@RequestBody Airport airport){
		airport.set_id(ObjectId.get());
		airportRepository.save(airport);
		this.publisher.sendAirportObj(airport);
	}

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Airport> getAllAirport() {
      return airportRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Airport getAirportById(@PathVariable("id") ObjectId id) {
      return airportRepository.findBy_id(id);
    }
  
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyAirportById(@PathVariable("id") ObjectId id, @RequestBody Airport airport) {
      airport.set_id(id);
      airportRepository.save(airport);
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Airport createAirport(@RequestBody Airport airport) {
      airport.set_id(ObjectId.get());
      airportRepository.save(airport);
      return airport;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePet(@PathVariable ObjectId id) {
    	airportRepository.delete(airportRepository.findBy_id(id));
    }
 

}