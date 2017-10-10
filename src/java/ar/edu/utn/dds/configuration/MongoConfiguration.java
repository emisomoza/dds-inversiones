package ar.edu.utn.dds.configuration;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Value("${mongo.url}")
    private String mongoHost;

    @Override
    protected String getDatabaseName() {
        return "INVERSIONES";
    }

    @Override
    public MongoClient mongo() throws Exception {
        return new MongoClient(mongoHost, 27017);
    }
}