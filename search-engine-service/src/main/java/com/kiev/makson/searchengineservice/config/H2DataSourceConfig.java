package com.kiev.makson.searchengineservice.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("h2")
public class H2DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "datasource.test.h2")
    public DataSource h2DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:dataSource;Mode=Oracle;DB_CLOSE_ON_EXIT=FALSE")
                .username("sa")
                .driverClassName("org.h2.Driver")
                .build();
    }

}
