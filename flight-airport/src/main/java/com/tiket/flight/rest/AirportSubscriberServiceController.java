package com.tiket.flight.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tiket.flight.document.Airport;
import com.tiket.flight.document.AirportSubscriber;
import com.tiket.flight.repo.AirportRepository;
import com.tiket.flight.repo.AirportSubscriberRepository;

@RestController
@RequestMapping("/airport-subscriber")
public class AirportSubscriberServiceController {

	private AirportSubscriberRepository airportSubscriberRepository;
	
	public AirportSubscriberServiceController(AirportSubscriberRepository airportSubscriberRepository) {
		this.airportSubscriberRepository = airportSubscriberRepository;
	}

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<AirportSubscriber> getAllAirport() {
      return airportSubscriberRepository.findAll();
    }

	

}
