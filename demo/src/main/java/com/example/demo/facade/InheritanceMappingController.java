package com.example.demo.facade;

import com.example.demo.business.InheritanceMappingService;
import com.example.demo.repository.primary.entities.joinedtable.ProductJtMotorcycleTable;
import com.example.demo.repository.primary.entities.joinedtable.ProductJtTable;
import com.example.demo.repository.primary.entities.mappedsuperclass.ProductMscMotorcycleTable;
import com.example.demo.repository.primary.entities.singletable.ProductSingleTable;
import com.example.demo.repository.primary.entities.singletable.ProductSingleTableMotorcyle;
import com.example.demo.repository.primary.entities.tableperclass.ProductTpcMotorcycleTable;
import com.example.demo.repository.primary.entities.tableperclass.ProductTpcTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inheritance")
public class InheritanceMappingController {

    @Autowired
    InheritanceMappingService service;

    @GetMapping("/msc-motorcycle")
    public Iterable<ProductMscMotorcycleTable> msc() {
        return service.getAllMTRViaMappedSuperClass();
    }

    @GetMapping("/singletable-motorcycle")
    public Iterable<ProductSingleTableMotorcyle> singleTableMtr() {
        return service.getAllMTRViaSingleTable();
    }

    @GetMapping("/singletable")
    public Iterable<ProductSingleTable> singleTable() {
        return service.getAllViaSingleTable();
    }

    @GetMapping("/tableperclass-motorcycle")
    public Iterable<ProductTpcMotorcycleTable> tablePerClassMtr() {
        return service.getAllMTRViaTablePerClass();
    }

    @GetMapping("/tableperclass")
    public Iterable<ProductTpcTable> tablePerClass() {
        return service.getAllViaTablePerClass();
    }

    @GetMapping("/joinedtable-motorcycle")
    public Iterable<ProductJtMotorcycleTable> joinedTableMtr() {
        return service.getAllMTRViaJoinedTable();
    }

    @GetMapping("/joinedtable")
    public Iterable<ProductJtTable> joinedTable() {
        return service.getAllViaJoinedTable();
    }
}
