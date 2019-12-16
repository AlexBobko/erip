//package com.erip
//
//import com.erip.dsl.FlowData
//import com.erip.dsl.Wizard
//
//import java.util.function.Function
//
//
//class FlowSpec extends AbstractFlowSpec {
//    private final StepBuilder stepBuilder
//    private final WidgetBuilder widgetBuilder
//    private Function<FlowData, List<WidgetName>> widgetSupplier
//
//    Closure<List<WidgetName>> steps = { [] }
//
//    FlowSpec(StepBuilder stepBuilder, WidgetBuilder widgetBuilder) {
//        this.stepBuilder = stepBuilder
//        this.widgetBuilder = widgetBuilder
//    }
//
//    def steps(@DelegatesTo(StepFunctionProcessor) closure) {
//        widgetSupplier = { x ->
//            def processor = new StepFunctionProcessor(x, stepBuilder)
//            def code = closure.rehydrate(processor, this, [])
//            code.resolveStrategy = Closure.DELEGATE_ONLY
//            code()
//        }
//    }
//
//    Wizard<FlowData> create(FlowData flowData) {
//        def instances
//        if (widgetSupplier != null) {
//            instances = stepBuilder.createWidgetSpec(flowData, widgetSupplier.apply(flowData))
//        } else {
//            instances = stepBuilder.createWidgetSpec(flowData, steps)
//        }
//        new BasicFlow<>(new StepContainer(widgetBuilder, flowData, instances), type, flowData)
//    }
//}
