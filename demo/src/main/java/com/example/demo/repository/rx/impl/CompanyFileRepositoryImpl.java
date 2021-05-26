package com.example.demo.repository.rx.impl;

import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.nio.ByteBuffer;

@Repository
public class CompanyFileRepositoryImpl {

    @Autowired
    PostgresqlConnectionFactory factory;


    public  Flux<ByteBuffer> findFileById(Integer id) {
        R2dbcEntityTemplate template = new R2dbcEntityTemplate(factory);

        return template.getDatabaseClient().sql("select file_data from company_files where id = " + id)
                .fetch()
                .first()
                .map(b -> b.entrySet().stream().findFirst().get().getValue())
                .map(b -> (ByteBuffer) b)
                .flux();
    }

}
