package com.erip.service;

import static java.lang.Thread.sleep;
import static java.util.Arrays.asList;

import com.erip.SimpleHello;
import com.erip.entity.Customer;
import com.erip.repository.ReactivePersonRepository;
import io.vavr.control.Try;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class DefaultCustomerService implements CustomerService {

    private final ReactivePersonRepository reactivePersonRepository;
    private final GridFsTemplate gridFsTemplate;


    @Value("${custom.conf.vl}")
    private String valCustom;

    private static final Logger logger = LogManager.getLogger();
    private static final String HELLO_WORLD = "HelloWorld";

    public DefaultCustomerService(ReactivePersonRepository reactivePersonRepository,
        GridFsTemplate gridFsTemplate) {
        this.reactivePersonRepository = reactivePersonRepository;
        this.gridFsTemplate = gridFsTemplate;
    }

    @Override
    public Flux<Customer> get() {
        logger.info("valCustom: {}", valCustom);
        return reactivePersonRepository.findAll();
    }

    @Override
    public Mono<Customer> save(Customer user) {
        return reactivePersonRepository.save(user);
    }

    @Override
    public Mono<Void> testReactive() {
        return Mono.empty();
    }

    public Flux<Customer> testReactive2() {
        try {
            save();
        } catch (IOException e) {
            logger.error(" ERROR " + e);
        }
        /*StepVerifier.create(
            Flux.range(1, 10)
                .buffer(5, 3) //overlapping buffers
        )
            .expectNext(Arrays.asList(1, 2, 3, 4, 5))
            .expectNext(Arrays.asList(4, 5, 6, 7, 8))
            .expectNext(Arrays.asList(7, 8, 9, 10))
            .expectNext(Collections.singletonList(10))
            .verifyComplete();*/

//        return getByLastName("vasiaPup", 1000L);

        Flux<Customer> customerFlux = Flux.merge(
            getByLastName("One_1", 100L),
//            getByLastName("Two_2", 3000L),
            Flux.defer(() -> getByLastName("Two_2", 3000L)),
            getByLastName("Three_3", 10L),
            Flux.defer(() -> getByLastName("For_4", 2000L)),
            Flux.defer(() -> getByLastName("Five_5", 100L)),
            getByLastName("nothing", 50L)
        )
            .doOnEach(logger::info)
            .cache()
            /*.flatMap(customer -> {
                logger.info("[flatMap] LastName: [{}]", customer.getLastName());
                return Flux.just(customer);
            })*/;
        return customerFlux;
    }

    private Flux<Customer> getByLastName(String name, Long timeout) {
        try {
            logger.info("[getByLastName] [Start] LastName:[{}] : {}ms Thread: {}", name, timeout,
                Thread.currentThread().getId());
            sleep(timeout);
            logger.info("[getByLastName] [End] LastName:[{}] : {}ms Thread: {}", name, timeout,
                Thread.currentThread().getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return reactivePersonRepository.findByLastName(Mono.just(name));
    }

    private void invokeFlux() {

        Flux<Customer> flux = Flux.just(new Customer("Walter", "White"),
            new Customer("Skyler", "White"),
            new Customer("Saul", "Goodman"),
            new Customer("Jesse", "Pinkman"));

    }

    private String rndSuf() {
        return UUID.randomUUID().toString().substring(0, 3);
    }


    private void test() {

        reactivePersonRepository.saveAll(asList(new Customer("Bob_" + rndSuf(), "Smith"),
            new Customer("Bob_" + rndSuf(), "Smith")))
            .blockLast();
        reactivePersonRepository.save(new Customer("reactiveAlice", "reactiveSmith")).block();

        logger.info("Customers found with findAll():");
        logger.info("-------------------------------");
        get()
            .map(r -> {
                logger.info(r + "  ***customerService***");
                return r;
            })
            .blockLast();

        logger.info("--------------------------------");
        logger.info("--------------------------------");
        logger.info("Customer found with findByFirstName('Alice'):");
        logger.info("--------------------------------");
//        logger.info(repository.findByFirstName("Alice"));
        logger.info("Customers found with findByLastName('Smith'):");
        logger.info("--------------------------------");
    }


    public void main2() {

        invokeFlux();

        final Thread r1 = thread.apply("Thread_1", 1000L);
        final Thread r2 = thread.apply("Thread_2", 500L);
        SimpleHello simpleHello = new SimpleHello(3);
        logger.info(" Start thread:{}", Thread.currentThread().getName());
//        Mono.just()

        Try.of(() -> {
            logger.info(HELLO_WORLD);
            int t = 9 / 2;
            return HELLO_WORLD;
        })
            .andThen(r1::start)
            .andThen((Consumer<String>) System.out::println)
            .andThen(r2::start)
            .onSuccess(str -> {
                Try.of(() -> {
                    r1.join();
                    r2.join();
                    logger.info(
                        "r1:" + r1.getId() + " r2:" + r2.getId() + " joined to thread:" + Thread.currentThread()
                            .getName());
                    return "success";
                }).onFailure(logger::error);
                logger.info("success");
                logger.info(str);
            })
            .onFailure(str -> logger.error("error {}", str.getMessage()))
            .andFinally(() -> logger.info("finally. Finished thread:" + Thread.currentThread().getName()));


    }

    private void save() throws IOException {
        String newFileName = "mkyong-java-image";
//        File imageFile = new File("log4j2.yml");
        try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("flow/example.flow")) {
//        InputStream stream = new FileInputStream(imageFile)
//            fs.save(stream, createMetadata(name));
            gridFsTemplate.store(stream, "testFile1");
//        GridFS gfsPhoto = new GridFS(db, "photo");
//        GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);
//        gfsFile.setFilename(newFileName);
//        gfsFile.save();
        }
    }


    private final BiFunction<String, Long, Thread> thread = (name, timeout) ->
        new Thread(() -> {
            logger.info(" Start thread:" + name);
            Try.of(() -> {
                Thread.currentThread().setName(name);
                sleep(timeout);
                return Thread.currentThread().getName();
            });
            logger.info(" {}ms Current thread:{}", timeout, name);
        });


}
