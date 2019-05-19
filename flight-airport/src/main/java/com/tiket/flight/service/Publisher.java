package com.tiket.flight.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.tiket.flight.document.Airport;

@Service
public class Publisher {

	private static final Logger logger = LoggerFactory.getLogger(Publisher.class);
	private static final String TOPIC = "users";
	private static final String TOPICAIRPORT = "airport";

/*
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
		
	public void sendMessage(String message) {
		logger.info(String.format("$$ -> Producing message --> %s", message));
		this.kafkaTemplate.send(TOPIC, message);
	}
*/

	@Autowired
	private KafkaTemplate<String, Airport> kafkaTemplate;

	public void sendAirportObj(Airport airport) {
		Message<Airport> message = MessageBuilder
                .withPayload(airport)
                .setHeader(KafkaHeaders.TOPIC, TOPICAIRPORT)
                .setHeader("idAirport", airport.get_id())
                .build();
		
		this.kafkaTemplate.send(message);
	}

}