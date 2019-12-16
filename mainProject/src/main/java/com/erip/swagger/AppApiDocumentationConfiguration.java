//package com.erip.swagger;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@EnableSwagger2
//@Configuration
//public class AppApiDocumentationConfiguration {
//
//    private final DocketHelper docketHelper;
//
//    @Autowired
//    public AppApiDocumentationConfiguration(DocketHelper docketHelper) {
//        this.docketHelper = docketHelper;
//    }
//
//    @Bean
//    public Docket appEnApiDocket() {
//        return docketHelper.prepareNew("app", "en", AppService.class);
//    }
//
//    @Bean
//    public Docket appPlApiDocket() {
//        return docketHelper.prepareNew("app", "pl", AppService.class);
//    }
//
//    @Bean
//    public UiConfiguration uiConfig() {
//        // TODO: improve headers when this is fixed: https://github.com/swagger-api/swagger-ui/issues/4839
//        return UiConfigurationBuilder.builder()
//            .deepLinking(true)
//            .displayOperationId(false)
//            .defaultModelsExpandDepth(1)
//            .defaultModelExpandDepth(1)
//            .defaultModelRendering(ModelRendering.EXAMPLE)
//            .displayRequestDuration(false)
//            .docExpansion(DocExpansion.NONE)
//            .filter(false)
//            .maxDisplayedTags(null)
//            .operationsSorter(OperationsSorter.ALPHA)
//            .showExtensions(false)
//            .tagsSorter(TagsSorter.ALPHA)
//            .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
//            .validatorUrl(null)
//            .build();
//    }
//}
