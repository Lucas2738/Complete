package com.example.demo.conf.persistences;

import com.example.demo.repository.rx.CompanyFileRepository;
import com.example.demo.repository.rx.CustomerRepository;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.core.DefaultReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.dialect.PostgresDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.r2dbc.core.DatabaseClient;

@Configuration
@EnableR2dbcRepositories("com.example.demo.repository.rx")
public class ReactivePersistenceConfig extends AbstractR2dbcConfiguration {

        @Value("${spring.r2dbc.host}")
        private String host;
        @Value("${spring.r2dbc.port}")
        private int port;
        @Value("${spring.r2dbc.username}")
        private String username;
        @Value("${spring.r2dbc.password}")
        private String password;
        @Value("${spring.r2dbc.database}")
        private String database;

        //Because Spring JPA by default cannot work with reactive one. You can get this error: Reactive Repositories are not supported by JPA.
        //Let’s create R2DatabaseConfiguration in config package and configure databaseClient
        @Bean
        public CompanyFileRepository companyFileRepository() {
                return r2dbcRepositoryFactory().getRepository(CompanyFileRepository.class);
        }

        @Bean
        public CustomerRepository customerRepository() {
                return r2dbcRepositoryFactory().getRepository(CustomerRepository.class);
        }

        @Bean
        public R2dbcRepositoryFactory r2dbcRepositoryFactory() {
                RelationalMappingContext context = new RelationalMappingContext();
                context.afterPropertiesSet();
                return new R2dbcRepositoryFactory(r2dbcDatabaseClient(),
                        new DefaultReactiveDataAccessStrategy(new PostgresDialect()));
        }

        @Bean
        public DatabaseClient r2dbcDatabaseClient() {
                return DatabaseClient.create(connectionFactory());
        }
        //end


        @Bean
        @Override
        public PostgresqlConnectionFactory connectionFactory() {
            return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration
                    .builder()
                    .host(host)
                    .port(port)
                    .username(username)
                    .password(password)
                    .database(database)
                    .build());
        }


}
