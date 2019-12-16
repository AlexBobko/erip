package com.erip.config;

import com.erip.repository.ReactivePersonRepository;
import com.erip.service.CustomerService;
import com.erip.service.DefaultCustomerService;
import com.erip.service.Parser;
import com.erip.service.ParserXlsx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@Configuration
public class AppConfig {

    @Autowired
    private ReactivePersonRepository reactivePersonRepository;
    @Autowired
    private GridFsTemplate gridFsTemplate;
//    @Bean
//    CustomerController controller() {
//        return new CustomerController(customerService());
//    }


    @Bean
    CustomerService customerService() {
        return new DefaultCustomerService(reactivePersonRepository, gridFsTemplate);
    }

    @Bean
    Parser parserXlsx() {
        return new ParserXlsx();
    }
}
