//package com.erip
//
//import com.erip.dsl.FlowName
//
//abstract class MainScriptClass extends Script {
//
////    StepBuilder stepBuilder
////    WidgetBuilder widgetBuilder
////    List<PageSpec> pageSpecs = []
//    List<FlowName> flowSpecs = []
//    List<String> flows = []
//    Map<FlowName, List<String>> specs = new HashMap<>()
////    List<String> flowSpecs = []
////    List<AbstractWidgetSpec> widgetSpecs = []
//
//
//    /*def spec(def obj, Closure valueClosure) {
//        def specs = []
//        def code = valueClosure.rehydrate(specs, this, this)
//        code.resolveStrategy = Closure.DELEGATE_ONLY
//        flowSpecs = code()
//    }*/
//
//    def spec(def obj, @DelegatesTo(FlowSpecs) Closure valueClosure) {
//        def specs = new FlowSpecs()
////        def specs = []
//        def code = valueClosure.rehydrate(specs, this, this)
//        code.resolveStrategy = Closure.DELEGATE_ONLY
//        flowSpecs = code()
////        code()
//    }
//
//    class FlowSpecs {
//        def flow(FlowName type, Closure flowClosure) {
//            def flows2 = []
////        def specs = []
//            def code = flowClosure.rehydrate(flows2, this, this)
//            code.resolveStrategy = Closure.DELEGATE_ONLY
////        flowSpecs = specs
//            List<String> f = code()
//            specs.put(type, f)
//            f
////            code()
//        }
//
///*        private <T> T fill(T data, Closure closure) {
//            def code = closure.rehydrate(data, this, this)
//            code.resolveStrategy = Closure.DELEGATE_ONLY
//            code()
//            data
//        }*/
//    }
//
//
//}
