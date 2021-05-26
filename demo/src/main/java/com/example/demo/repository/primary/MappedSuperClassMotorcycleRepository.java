package com.example.demo.repository.primary;

import com.example.demo.repository.primary.entities.mappedsuperclass.ProductMscMotorcycleTable;
import org.springframework.data.repository.CrudRepository;

public interface MappedSuperClassMotorcycleRepository extends CrudRepository<ProductMscMotorcycleTable, Integer> {
}
