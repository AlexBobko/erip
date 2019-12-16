package com.erip.controller;

import com.erip.entity.Customer;
import com.erip.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class CustomerController {

    private static final Logger logger = LogManager.getLogger();

    private CustomerService userService;

    public CustomerController(CustomerService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public Mono<String> sayHi() {
        return Mono.just("Hi");
    }

  /*  @PutMapping("/upload")
    public Mono<String> uploadFile(@RequestPart("file") FilePart file) {

        return Mono.just("Hi");
    }*/

    @GetMapping("/api/test")
    public Flux<Customer> testIk() {
        logger.info("==={}===========START testIk =====================", Thread.currentThread().getId());
        Flux<Customer> mono = userService.testReactive2()
//            .cache()
            .doOnComplete(
                new Thread() {
                    @Override
                    public void run() {
                        logger.info("***{}***[doOnComplete]****complete TestFlux********[doOnComplete]******",
                            Thread.currentThread().getId());
                        super.run();
                    }
                }
            ).cache();

        mono.subscribe(i -> logger.info("[subscribe] thread {} msg: last: [{}] first: {} ",
            Thread.currentThread().getId(), i.getLastName(), i.getFirstName()));
//        mono.subscribe(i -> logger.info("[subscribe] msg: last: [{}] first: {} ", i.getLastName(), i.getFirstName()));
//        mono.subscribe(i -> logger.info("[subscribe] msg: last: [{}] first: {} ", i.getLastName(), i.getFirstName()));
        logger.info("=={}==========END testIk=============", Thread.currentThread().getId());
        return mono;
    }

    @PostMapping
    public Mono<Customer> post(@RequestBody Customer user) {
        return userService.save(user);
    }

    @GetMapping
    public Flux<Customer> get() {
        return userService.get();
    }
}
