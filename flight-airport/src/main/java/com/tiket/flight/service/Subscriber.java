package com.tiket.flight.service;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.tiket.flight.document.Airport;
import com.tiket.flight.document.AirportSubscriber;
import com.tiket.flight.repo.AirportRepository;
import com.tiket.flight.repo.AirportSubscriberRepository;

@Service
public class Subscriber {


	private final Logger logger = LoggerFactory.getLogger(Subscriber.class);

/*	@KafkaListener(topics = "users", groupId = "group_id")
	public void consume(String message){	
		logger.info(String.format("$$ -> Consumed Message -> %s",message));
	}
*/
	private AirportSubscriberRepository airportSubscriberRepository;
	
	public Subscriber(AirportSubscriberRepository airportSubscriberRepository) {
		this.airportSubscriberRepository = airportSubscriberRepository;
	}
	
	  @KafkaListener(topics = "airport")
	  public void consume(@Payload Airport data, @Headers MessageHeaders messageHeaders) {
	    for(String key : messageHeaders.keySet()){
	    	 if (key.equals("idAirport")){
	    		 String value = messageHeaders.get(key).toString();
	    		 AirportSubscriber aFound = airportSubscriberRepository.findByAirportCode(data.getAirportCode());
	    			if(aFound == null){
	    				data.set_id(new ObjectId(value));
	    			}else{
	    				data.set_id(new ObjectId(aFound.get_id()));
	    			}
	    			AirportSubscriber aSub = new AirportSubscriber(new ObjectId(data.get_id()), data.getAirportName(),data.getAirportCode(), data.getCityCode(), data.getIsActive() );
	    			 airportSubscriberRepository.save(aSub);
	         }
	    }
	    
	    logger.info("received airport='{}'", data);
	    //latch.countDown();
	  }
	
	
	
	/*	public static void main(String[] args){
				
			System.setProperty("java.net.preferIPv4Stack" , "true");
			MongoClientURI uri = new MongoClientURI(
			    "mongodb://admin:Puteri12345@Puteri01cluster-shard-00-00-np3tq.gcp.mongodb.net:27017,Puteri01cluster-shard-00-01-np3tq.gcp.mongodb.net:27017,Puteri01cluster-shard-00-02-np3tq.gcp.mongodb.net:27017/test?ssl=true&replicaSet=Puteri01Cluster-shard-0&authSource=admin&retryWrites=true");
	
			
			MongoClient mongoClient = new MongoClient(uri);
		    DB database = mongoClient.getDB("myMongoDb");	 
		    
		    	    DBCollection col = database.getCollection("airports");
		    
		    
		    Airport airport = createAirport();
		    DBObject doc = createDBObject(airport);
		    
		  //create airport
		  		WriteResult result = col.insert(doc);
		  		System.out.println(result.getUpsertedId());
		  		System.out.println(result.getN());
		  		System.out.println(result.isUpdateOfExisting());
		  	
		  		
		  		//read example
		  		DBObject query = BasicDBObjectBuilder.start().add("_id", airport.getId()).get();
		  		DBCursor cursor = col.find(query);
		  		while(cursor.hasNext()){
		  			System.out.println(cursor.next());
		  		}
		  		
		  		mongoClient.close();
		 
		}*/

	
}
