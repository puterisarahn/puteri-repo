package com.tiket.flight.config;

import java.rmi.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.tiket.flight.document.Airport;
import com.tiket.flight.repo.AirportRepository;


@EnableMongoRepositories(basePackageClasses = AirportRepository.class)
@Configuration
@PropertySource("classpath:application.properties")
public class MongoDBConfig {

	
/*	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(), "rest_tutorial");
	}


	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
		
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}
*/
	
	/*    @Value("${spring.data.mongodb.authentication-database}")
	    private String database;
	    @Value("${spring.data.mongodb.host}")
	    private String host;
	    @Value("${spring.data.mongodb.port}")
	    private String port;
	    @Value("${spring.data.mongodb.username}")
	    private String username;
	    @Value("${spring.data.mongodb.password}")
	    private String password;

	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
		
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}


	@Override
	public MongoClient mongoClient() {
		List<MongoCredential> allCred = new ArrayList<MongoCredential>();
        allCred.add(MongoCredential.createCredential(username, database, password.toCharArray()));
       
        MongoClient client = new MongoClient((new ServerAddress(host, Integer.parseInt(port))), allCred);
        client.setWriteConcern(WriteConcern.ACKNOWLEDGED);        
        System.out.println("Godatabase : "+username+" "+database+" "+password+" "+host+" "+port);
        return client;
	}

	@Override
	protected String getDatabaseName() {
		return database;
	}	
*/
	
 /*   @Bean
    CommandLineRunner commandLineRunner(AirportRepository userRepository) {
        return strings -> {
            userRepository.save(new Airport(1, "Soekarno Hatta", "a001", "b001", true));
            userRepository.save(new Airport(2, "Halim", "a002", "b002", true));
        };
    }
 */   
	

    @Autowired
    Environment env;
    

    @Bean
    public MongoClient mongoClient() {
        System.setProperty("java.net.preferIPv4Stack" , "true");
       	MongoClientURI uri = new MongoClientURI(
   			    "mongodb://"
   			    		+env.getProperty("spring.data.mongodb.username")+":"
   			    		+env.getProperty("spring.data.mongodb.password")+"@"
   			    		+env.getProperty("spring.data.mongodb.host")+":"
   			    		+env.getProperty("spring.data.mongodb.port")+"/"
   			    		+env.getProperty("spring.data.mongodb.authentication-database")
   			    		+"?ssl=true&replicaSet=Puteri01Cluster-shard-0&authSource=admin&retryWrites=true");
       	MongoClient mongoClient = new MongoClient(uri);
        return mongoClient;
    }

    @Bean
    public MongoDbFactory mongoDbFactory()  {
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient(),
		env.getProperty("spring.data.mongodb.database"));
        return mongoDbFactory;

    }

    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }

    

}