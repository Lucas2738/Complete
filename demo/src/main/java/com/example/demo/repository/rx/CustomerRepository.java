package com.example.demo.repository.rx;

import com.example.demo.repository.rx.entities.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {

}


