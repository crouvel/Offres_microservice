package com.example.offres_microservice;

import com.example.offres_microservice.repository.OffreRepository;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

// trouver moyen de modifier le port où lancer l'application

@SpringBootApplication
public class OffresMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OffresMicroserviceApplication.class, args);
    }

    private static final Logger log = LoggerFactory.getLogger(OffresMicroserviceApplication.class);

    @Bean
    public ConnectionFactory connectionFactory() {
        log.info("------------------- ICI ---------------------------");
        //return ConnectionFactories.get("r2dbc:postgresql://postgresql-stagerouvel.alwaysdata.net:5432/stagerouvel_iwa");
        ConnectionFactory connectionFactory = ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(DRIVER, "postgresql")
                .option(HOST, "postgresql-stagerouvel.alwaysdata.net")
                .option(USER, "stagerouvel")
                .option(PASSWORD, "josee1998")
                .option(DATABASE, "stagerouvel_iwa")
                .build()
        );
        log.info("connection established");
        return connectionFactory;
    }

    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        //initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));

        return initializer;
    }

}
