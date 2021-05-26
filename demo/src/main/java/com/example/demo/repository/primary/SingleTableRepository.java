package com.example.demo.repository.primary;

import com.example.demo.repository.primary.entities.singletable.ProductSingleTable;
import org.springframework.data.repository.CrudRepository;

public interface SingleTableRepository extends CrudRepository<ProductSingleTable, Integer> {
}
