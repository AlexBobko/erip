//package com.erip
//
//import com.erip.dsl.FlowData
//
//
//abstract class AbstractWidgetSpec {
//    WidgetName name
//
//    abstract List<AbstractWidgetSpec> create(FlowData flowData)
//
//    Widget<FlowData> create(FlowData flowData, WidgetBuilder builder) {
//        create(flowData).first().create(flowData, builder)
//    }
//}
