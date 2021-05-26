package com.example.demo.business.impl;

import com.example.demo.business.RxDemoService;
import com.example.demo.business.domain.CompanyFileModel;
import com.example.demo.repository.rx.CompanyFileRepository;
import com.example.demo.repository.rx.CustomerRepository;
import com.example.demo.repository.rx.entities.CompanyFiles;
import com.example.demo.repository.rx.entities.Customer;
import com.example.demo.repository.rx.impl.CompanyFileRepositoryImpl;
import com.example.demo.util.mappers.CompanyFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.FetchSpec;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.util.Map;

@Service
public class RxDemoServiceImpl implements RxDemoService {

    @Autowired
    CompanyFileRepository companyFileRepository;

    @Autowired
    CompanyFileRepositoryImpl companyFileRepositoryImpl;

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    CompanyFileMapper mapper;

    @Override
    public Flux<CompanyFileModel> getAllCompanyFiles(){
        return companyFileRepository.findAll().map(f -> mapper.entityToModel(f));
    }

    @Override
    public Mono<CompanyFiles> getById(Integer id){
        return companyFileRepository.findById(id);
    }

    @Override
    public Mono<CompanyFiles> uploadCompanyFile(CompanyFileModel file){
        return Mono.just(mapper.modelToEntity(file))
                .flatMap(companyFileRepository::save);
    }

    @Override
    public Flux<CompanyFiles> getAllCompanyFilesNonBlocking(){
        return companyFileRepository.findAll();
    }

    @Override
    public Flux<ByteBuffer> getByteFiles(int id){
        return companyFileRepositoryImpl.findFileById(id);
    }

    @Override
    public Flux<Customer> getAllCustomer(int limit) {
        if(limit < -1){
            return customerRepo.findAll();
        }else {
            return customerRepo.findAll().take(limit);
        }
    }
}
