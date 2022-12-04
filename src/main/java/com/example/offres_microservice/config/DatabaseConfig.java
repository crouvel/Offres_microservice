package com.example.offres_microservice.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

//@Configuration
public class DatabaseConfig extends AbstractR2dbcConfiguration {


    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        System.out.println("------------------- ICI ---------------------------");
        //return ConnectionFactories.get("r2dbc:postgresql://postgresql-stagerouvel.alwaysdata.net:5432/stagerouvel_iwa");
        ConnectionFactory connectionFactory = ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(DRIVER, "postgresql")
                .option(HOST, "postgresql-stagerouvel.alwaysdata.net")
                .option(USER, "stagerouvel")
                .option(PASSWORD, "josee1998")
                .option(DATABASE, "stagerouvel_iwa")
                .build()
        );
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
