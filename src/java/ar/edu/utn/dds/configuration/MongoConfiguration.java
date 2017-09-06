package ar.edu.utn.dds.configuration;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "INVERSIONES";
    }

    @Override
    public MongoClient mongo() throws Exception {
        return new MongoClient("localhost", 27017);
    }
}