package com.example.demo.facade;

import com.example.demo.business.RxDemoService;
import com.example.demo.business.domain.CompanyFileModel;
import com.example.demo.business.domain.RSocketRequest;
import com.example.demo.business.domain.RSocketResponse;
import com.example.demo.repository.rx.entities.CompanyFiles;
import com.example.demo.repository.rx.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.text.MessageFormat;
import java.time.Duration;

@Controller
public class RSocketController {

    @Autowired
    RxDemoService service;

    @MessageMapping("rsocket-test")
    public Flux<RSocketResponse> testRsocket(RSocketRequest request) {

        String[] data = new String[]{"obj1" + request.getId(), "obj2" + request.getId(), "Obj3" + request.getId(),
                "Obj4" + request.getId(), "Obj5" + request.getId(), "Obj6" + request.getId(), "Obj7" + request.getId(),
                "Obj8" + request.getId(), "Obj9" + request.getId(), "Obj10" + request.getId(), "Obj11" + request.getId(),
                "Obj12" + request.getId(), "Obj13" + request.getId(), "Obj14" + request.getId(), "Obj15" + request.getId(),
                "Obj16" + request.getId(), "Obj17" + request.getId(), "Obj18" + request.getId(), "Obj19" + request.getId(),
                "Obj20" + request.getId(), "Obj21" + request.getId(), "Obj22" + request.getId(), "Obj23" + request.getId(),
                "Obj24" + request.getId(), "Obj25" + request.getId(), "Obj26" + request.getId(), "Obj27" + request.getId(),
                "Obj28" + request.getId(), "Obj29" + request.getId(), "Obj30" + request.getId(), "Obj31" + request.getId(),
                "Obj32" + request.getId(), "Obj33" + request.getId(), "Obj34" + request.getId(), "Obj35" + request.getId(),
                "Obj36" + request.getId(), "Obj37" + request.getId(), "Obj38" + request.getId(), "Obj39" + request.getId()};

        return Flux
                .interval(Duration.ofSeconds(1))
                .map(index -> new RSocketResponse(MessageFormat.format("Server say: Request {0}, It is {1}ms", request.getId(), System.currentTimeMillis())))

                //.fromIterable(Arrays.asList(data))
                //.delayElements(Duration.ofMillis(400))
                //.doOnNext(serviceA::someObserver)
                //.map(d -> RSocketResponse.builder().data(d).build())
                //.take(39)
                //.onErrorResumeWith(errorHandler::fallback)
                //.doAfterTerminate(serviceM::incrementTerminate)
                //.subscribe(System.out::println)
                ;
    }

    @MessageMapping("company-file")
    public Mono<ByteBuffer> companyFiles(int id) {
        Mono<CompanyFiles> result = service.getById(id);
        return result.map(c -> ByteBuffer.wrap(c.getFileData()));
    }

    @MessageMapping("customers")
    public Flux<Customer> allCutomer(int limit) {
        return service.getAllCustomer(limit);
    }

    @MessageMapping("upload-company-file")
    public Mono<Boolean> uploadCompanyFiles(CompanyFileModel companyFile) {
        return service.uploadCompanyFile(companyFile) != null ? Mono.just(Boolean.TRUE): Mono.just(Boolean.FALSE);
    }

}
