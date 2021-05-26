package com.example.demo.repository.primary;

import com.example.demo.repository.primary.entities.singletable.ProductSingleTableMotorcyle;
import org.springframework.data.repository.CrudRepository;

public interface SingleTableMotorcycleRepository extends CrudRepository<ProductSingleTableMotorcyle, Integer> {
}
