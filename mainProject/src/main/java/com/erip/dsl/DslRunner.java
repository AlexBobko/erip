package com.erip.dsl;

import static java.util.Optional.ofNullable;

import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DslRunner {
    private static final Logger logger = LogManager.getLogger();
    public static void invokeDsl() {
        BasicDslDispatcher dslDispatcher = new BasicDslDispatcher();

        ofNullable(dslDispatcher.getFlowMap().get(DefaultFlowName.BASIC)).ifPresent(d -> logger.info("result {}", d));
        dslDispatcher.getFlowMap().keySet().forEach(
            k ->
                logger.info("flow {} steps {}", k.name(), String.join("; ", dslDispatcher.getFlowMap().get(k)))
        );

        logger.info("result {}",
            dslDispatcher.getFlowMap().keySet().stream()
                .map(FlowName::name)
                .collect(Collectors.joining("; ")));
    }
}
