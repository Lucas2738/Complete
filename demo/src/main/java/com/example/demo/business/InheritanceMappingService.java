package com.example.demo.business;

import com.example.demo.repository.primary.entities.joinedtable.ProductJtMotorcycleTable;
import com.example.demo.repository.primary.entities.joinedtable.ProductJtTable;
import com.example.demo.repository.primary.entities.mappedsuperclass.ProductMscMotorcycleTable;
import com.example.demo.repository.primary.entities.singletable.ProductSingleTable;
import com.example.demo.repository.primary.entities.singletable.ProductSingleTableMotorcyle;
import com.example.demo.repository.primary.entities.tableperclass.ProductTpcMotorcycleTable;
import com.example.demo.repository.primary.entities.tableperclass.ProductTpcTable;

public interface InheritanceMappingService {
    public Iterable<ProductMscMotorcycleTable> getAllMTRViaMappedSuperClass();

    public Iterable<ProductSingleTableMotorcyle> getAllMTRViaSingleTable();
    public Iterable<ProductSingleTable> getAllViaSingleTable();

    public Iterable<ProductTpcMotorcycleTable> getAllMTRViaTablePerClass();
    public Iterable<ProductTpcTable> getAllViaTablePerClass();

    public Iterable<ProductJtMotorcycleTable> getAllMTRViaJoinedTable();
    public Iterable<ProductJtTable> getAllViaJoinedTable();
}
