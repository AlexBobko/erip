//package com.erip.dsl;
//
//import static java.util.stream.Collectors.toList;
//import static java.util.stream.Stream.of;
//
//import com.erip.MainScriptClass;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import com.google.common.io.ByteStreams;
//import groovy.lang.GroovyClassLoader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.codehaus.groovy.control.CompilerConfiguration;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//
//public class BasicDslDispatcher {
//
//    public Map<FlowName, List<String>> getFlowMap() {
//        return flowMap;
//    }
//
//    //    private final Map<String, FlowName> flowMap;
//    private final Map<FlowName, List<String>> flowMap;
//
//    public List<String> getFlowList() {
//        return flowList;
//    }
//
//    private final List<String> flowList;
////    private final Map<Double, Long> flowMap;
//
//    private static final Logger logger = LogManager.getLogger(BasicDslDispatcher.class.getName());
//
//    public BasicDslDispatcher() {
//        CompilerConfiguration config = new CompilerConfiguration();
//        config.setScriptBaseClass(MainScriptClass.class.getName());
//
//        final ClassLoader classLoader = MainScriptClass.class.getClassLoader();
//
//        ClassLoader cl = getClass().getClassLoader();
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
//
//        try (GroovyClassLoader groovyClassLoader = new GroovyClassLoader(classLoader, config)) {
//            List<MainScriptClass> scripts = of(resolver.getResources("classpath*:/flow/**/*.flow"))
//                .peek(x -> logger.info("Detected configuration file: " + x.getFilename()))
//                .map(parseClass(groovyClassLoader))
//                .map(this::getInstance)
//                .collect(toList());
//            this.flowMap = Maps.newHashMap();
//            this.flowList = Lists.newArrayList();
//            logger.info("Find default logic: ");
//            this.flowMap.putAll(flows(scripts, MainScriptClass::getSpecs));
////            this.flowList.addAll(flows(scripts, MainScriptClass::getFlowSpecs));
//        } catch (IOException e) {
//            throw new IllegalStateException("Error during parse flow groovy dsl files", e);
//        }
//    }
//
//    private Function<Resource, Class> parseClass(GroovyClassLoader groovyClassLoader) {
//        return r -> {
//            try (InputStream stream = r.getInputStream()) {
//                return groovyClassLoader.parseClass(new String(ByteStreams.toByteArray(stream)));
//            } catch (IOException e) {
//                throw new IllegalStateException(
//                    "Error during parse groovy dsl file with resource path " + r.getFilename(), e);
//            }
//        };
//    }
//
//
//    private MainScriptClass getInstance(Class clazz) {
//        try {
//            final MainScriptClass scriptClass = (MainScriptClass) clazz.newInstance();
//            scriptClass.run();
//            return scriptClass;
//        } catch (InstantiationException | IllegalAccessException e) {
//            logger.error(e.getMessage());
//            throw new IllegalStateException("Error instantiate flow dsl clazz.", e);
//        }
//    }
//
//    /*private <T> List<T> flows(
////    private <T extends FlowName> Map<String, T> flows(
//        List<MainScriptClass> scripts,
//        Function<MainScriptClass, List<T>> getFlowSpecs) {
//        List<T> result =
//            scripts
//            .stream()
//            .map(getFlowSpecs)
//            .flatMap(Collection::stream)
//            .collect(toList())
////            .map(r -> r)
////            .collect(toMap(r -> r, t -> t))
//            ;
////        logger.info("* Detected flows: " + result.keySet());
////        return result;
//        return result;
//    }*/
//
//    private <T extends Map<FlowName, List<String>>> T flows(
////    private <T extends FlowName> Map<String, T> flows(
//        List<MainScriptClass> scripts,
//        Function<MainScriptClass, T> getFlows) {
//
//        T result = (T) new HashMap<FlowName, List<String>>();
//        scripts.forEach(item ->
//            Optional.ofNullable(item)
//                .map(getFlows)
//                .ifPresent(result::putAll));
//        logger.info("* Detected flows: " + result.keySet()
//            .stream()
//            .map(FlowName::name)
//            .collect(Collectors.joining(" ;")));
//
//        return result;
//    }
//
////    @Override
////    public Optional<FlowName> getFlowSpec(FlowName type) {
////        return ofNullable(flowMap.get(type.name()));
////    }
//
////    public Optional<FlowName> get(FlowName type) {
////        return ofNullable(flowMap.get(type.name()));
////    }
//
////    public Optional<Number> getFlowSpec(Double type) {
////        return ofNullable(flowMap.get(type));
////    }
//
//}
