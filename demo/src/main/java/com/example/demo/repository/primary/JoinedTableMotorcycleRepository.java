package com.example.demo.repository.primary;

import com.example.demo.repository.primary.entities.joinedtable.ProductJtMotorcycleTable;
import org.springframework.data.repository.CrudRepository;

public interface JoinedTableMotorcycleRepository extends CrudRepository<ProductJtMotorcycleTable, Integer> {
}
