package com.punyadev.batch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    DataSource dataSource;

    public DataSourceConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public DataSourceInitializer databasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("org/springframework/batch/core/schema-h2.sql"));
//        populator.addScript(new ClassPathResource("/org/springframework/batch/core/schema-mariadb.sql"));
//        populator.addScript(new ClassPathResource("/org/springframework/batch/core/schema-mysql.sql"));
//        populator.addScript(new ClassPathResource("/org/springframework/batch/core/schema-oracle.sql"));
//        populator.addScript(new ClassPathResource("/org/springframework/batch/core/schema-postgresql.sql"));

//        start: add initial sql script
        populator.addScript(new ClassPathResource("sql/schema.sql"));
        populator.addScript(new ClassPathResource("sql/data.sql"));
//        end: add initial sql script

        populator.setContinueOnError(false);
        populator.setIgnoreFailedDrops(false);
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(populator);
        return dataSourceInitializer;
    }

}