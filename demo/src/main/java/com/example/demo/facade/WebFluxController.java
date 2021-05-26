package com.example.demo.facade;

import com.example.demo.business.RxDemoService;
import com.example.demo.business.domain.CompanyFileModel;
import com.example.demo.repository.rx.entities.CompanyFiles;
import com.example.demo.util.validations.BasicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rx")
public class WebFluxController {

    @Autowired
    RxDemoService service;

    @GetMapping("/files")
    public Flux<CompanyFileModel> greeting() {
        return service.getAllCompanyFiles();
    }

    @GetMapping("/fileById")
    public Mono<CompanyFiles> upload(@RequestParam Integer id){

        Mono<CompanyFiles> result = service.getById(id);

        return result;
    }

    @GetMapping("/filesNonBlocking")
    public Flux<CompanyFiles> filesNonBlocking() {
        return service.getAllCompanyFilesNonBlocking();
    }

    @PostMapping("/upload")
    public Mono<CompanyFiles> upload(@RequestBody @Validated(BasicInfo.class) CompanyFileModel model){
        return service.uploadCompanyFile(model);
    }
}
