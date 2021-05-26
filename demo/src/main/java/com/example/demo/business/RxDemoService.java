package com.example.demo.business;

import com.example.demo.business.domain.CompanyFileModel;
import com.example.demo.repository.rx.entities.CompanyFiles;
import com.example.demo.repository.rx.entities.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;

public interface RxDemoService {
    Flux<CompanyFileModel> getAllCompanyFiles();
    Mono<CompanyFiles> uploadCompanyFile(CompanyFileModel file);
    Flux<CompanyFiles> getAllCompanyFilesNonBlocking();
    Mono<CompanyFiles> getById(Integer id);
    Flux<ByteBuffer> getByteFiles(int id);
    Flux<Customer> getAllCustomer(int limit);

}
