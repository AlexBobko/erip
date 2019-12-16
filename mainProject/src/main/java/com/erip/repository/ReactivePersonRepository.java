package com.erip.repository;


import com.erip.entity.Customer;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactivePersonRepository extends ReactiveCrudRepository<Customer, String> {

    Flux<Customer> findByLastName(Mono<String> lastName);

    @Query("{ 'firstName': ?0, 'lastName': ?1}")
    Mono<Customer> findByFirstNameAndLastname(String firstName, String lastName);
}


