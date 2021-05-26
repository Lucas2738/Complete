package com.example.demo.repository.primary;

import com.example.demo.repository.primary.entities.joinedtable.ProductJtTable;
import org.springframework.data.repository.CrudRepository;

public interface JoinedTableRepository extends CrudRepository<ProductJtTable, Integer> {
}
