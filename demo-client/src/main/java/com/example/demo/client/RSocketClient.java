package com.example.demo.client;

import com.example.demo.model.CompanyFileModel;
import com.example.demo.model.Customer;
import com.example.demo.model.RSocketRequest;
import com.example.demo.model.RSocketResponse;
import io.rsocket.core.RSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.rsocket.RSocketConnectorConfigurer;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.util.retry.Retry;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Duration;

@Controller
public class RSocketClient {

    private static final Logger logger = LoggerFactory.getLogger(RSocketClient.class);

    private RSocketRequester rsocketRequester;

    @Value("${microservice.demo.host}")
    private String demoWebHost;

    @Value("${FILE_DEST_FOLDER:/home/Downloads}")
    private String fileDestFolder;

    @Autowired
    public void init(RSocketRequester.Builder rsocketRequesterBuilder) {
        this.rsocketRequester = rsocketRequesterBuilder
                .rsocketConnector(new RSocketConnectorConfigurer() {
                                      @Override
                                      public void configure(RSocketConnector rSocketConnector) {
                                          rSocketConnector.reconnect(Retry.fixedDelay(10, Duration.ofSeconds(2)));
                                      }
                                  }
                )
                .tcp(demoWebHost, 7000);
    }

    @GetMapping("testRsocket")
    public ResponseEntity<String> testRsoscket(){
        rsocketRequester
                .route("rsocket-test")
                .data(new RSocketRequest(1234567))
                .retrieveFlux(RSocketResponse.class)
                .subscribe(er -> logger.info("Response received: {}", er.getData()));
        logger.info("Streaming is starting...");
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

    @GetMapping("companyFile")
    public ResponseEntity<String> companyFile(@RequestParam int id) throws IOException {
        File file = new File(fileDestFolder+ "/" + id + ".mp4");
        FileChannel channel = new FileOutputStream(file, true).getChannel();

        rsocketRequester
                .route("company-file")
                .data(new Integer(id))
                .retrieveMono(ByteBuffer.class)
                .subscribe(m ->{
                            try {
                                channel.write(m);
                            } catch (IOException e) {
                                logger.error("error");
                            }
                        }
                );
        logger.info("Streaming is starting...");
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

    @PostMapping("uploadCompanyFile")
    public ResponseEntity<String> uploadCompanyFile(@RequestBody CompanyFileModel companyFileModel) throws IOException {
        rsocketRequester
                .route("upload-company-file")
                .data(companyFileModel)
                .retrieveMono(Boolean.class)
                .subscribe(c -> logger.info(c.toString()));
        logger.info("Streaming is starting...");

        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

    @GetMapping("allCustomer")
    public ResponseEntity<String> allCustomer(@RequestParam int limit) throws IOException {
        rsocketRequester
            .route("customers")
            .data(limit)
            .retrieveFlux(Customer.class)
            .subscribe(c -> logger.info(c.toString()));
        logger.info("Streaming is starting...");

        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }
}
