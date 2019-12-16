//package com.erip.config;
//
//
//import com.mongodb.MongoClient;
//import com.sun.istack.internal.NotNull;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
//import org.springframework.data.mongodb.gridfs.GridFsTemplate;
//import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
//
//@Configuration
//@EnableReactiveMongoRepositories
////@Qualifier("gridFs")
//public class GridFsConfiguration extends AbstractMongoConfiguration {
//
//    // … further configuration omitted
//
//    @Bean
//    public GridFsTemplate gridFsTemplate() throws Exception {
//        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
//    }
//
//    @Override
//    @NotNull
//    public MongoClient mongoClient() {
//        return new MongoClient();
//    }
//
//    @Override
//    protected String getDatabaseName() {
//        return "GridFs";
//    }
//}
//
//
////не используется?
///*@EnableReactiveMongoRepositories
//public class AppMongoConfig extends AbstractReactiveMongoConfiguration {
////public class AppMongoConfig extends AbstractReactiveMongoConfiguration {
//
//
//    @Bean
//    public MongoClient mongoClient() {
//        return reactiveMongoClient();
//    }
//
//    @Override
//    protected String getDatabaseName() {
//        return "reactive";
//    }
//
//    @Override
//    public com.mongodb.reactivestreams.client.MongoClient reactiveMongoClient() {
//        return null;
//    }
//
//    @Bean
//    public GridFsTemplate gridFsTemplate() throws Exception {
//        return new GridFsTemplate(reactiveMongoDbFactory(), mappingMongoConverter());
//    }
//}*/
