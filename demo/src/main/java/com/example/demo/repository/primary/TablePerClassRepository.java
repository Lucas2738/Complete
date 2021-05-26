package com.example.demo.repository.primary;

import com.example.demo.repository.primary.entities.tableperclass.ProductTpcTable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface TablePerClassRepository<T extends ProductTpcTable> extends CrudRepository<T , Integer> {

    @Query("SELECT t FROM ProductTpcTable t")
    Iterable<ProductTpcTable> findAllBase();
}
