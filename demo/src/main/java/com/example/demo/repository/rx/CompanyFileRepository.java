package com.example.demo.repository.rx;

import com.example.demo.repository.rx.entities.CompanyFiles;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CompanyFileRepository extends ReactiveCrudRepository<CompanyFiles, Integer> {

}


