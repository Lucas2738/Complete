package com.example.demo.business.impl;

import com.example.demo.business.InheritanceMappingService;
import com.example.demo.repository.primary.*;
import com.example.demo.repository.primary.entities.joinedtable.ProductJtMotorcycleTable;
import com.example.demo.repository.primary.entities.joinedtable.ProductJtTable;
import com.example.demo.repository.primary.entities.mappedsuperclass.ProductMscMotorcycleTable;
import com.example.demo.repository.primary.entities.singletable.ProductSingleTable;
import com.example.demo.repository.primary.entities.singletable.ProductSingleTableMotorcyle;
import com.example.demo.repository.primary.entities.tableperclass.ProductTpcMotorcycleTable;
import com.example.demo.repository.primary.entities.tableperclass.ProductTpcTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InheritanceMappingServiceImpl implements InheritanceMappingService {

    @Autowired
    MappedSuperClassMotorcycleRepository repoMTRMSC;

    @Autowired
    SingleTableMotorcycleRepository repoMTRSingleTable;

    @Autowired
    SingleTableRepository repoSingleTable;

    @Autowired
    TablePerClassMotorcycleRepository repoMTRTablePerClass;

    @Autowired
    JoinedTableMotorcycleRepository repoMTRJoinedTable;

    @Autowired
    JoinedTableRepository repoJoinedTable;

    @Override
    public Iterable<ProductMscMotorcycleTable> getAllMTRViaMappedSuperClass(){
        return repoMTRMSC.findAll();
    }

    @Override
    public Iterable<ProductSingleTableMotorcyle> getAllMTRViaSingleTable(){
        return repoMTRSingleTable.findAll();
    }

    @Override
    public Iterable<ProductSingleTable> getAllViaSingleTable() {
        return repoSingleTable.findAll();
    }

    @Override
    public Iterable<ProductTpcMotorcycleTable> getAllMTRViaTablePerClass(){
        return repoMTRTablePerClass.findAll();
    }

    @Override
    public Iterable<ProductTpcTable> getAllViaTablePerClass() {
        return repoMTRTablePerClass.findAllBase();
    }

    @Override
    public Iterable<ProductJtMotorcycleTable> getAllMTRViaJoinedTable(){
        return repoMTRJoinedTable.findAll();
    }

    @Override
    public Iterable<ProductJtTable> getAllViaJoinedTable() {
        return repoJoinedTable.findAll();
    }

}
