package com.erip.service;

import com.erip.entity.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Flux<Customer> get();

    Mono<Customer> save(Customer user);

    Mono<Void> testReactive();

    Flux<Customer> testReactive2();
}
