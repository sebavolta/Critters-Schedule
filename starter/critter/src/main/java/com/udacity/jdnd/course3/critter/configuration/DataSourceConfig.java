package com.udacity.jdnd.course3.critter.configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource getDataSource() {
        DataSourceBuilder dsb = DataSourceBuilder.create();
        dsb.username("Sebastián");
        dsb.password("seba1234");
        dsb.url("jdbc:mysql://localhost:3306/critters");
        return dsb.build();
    }
}

