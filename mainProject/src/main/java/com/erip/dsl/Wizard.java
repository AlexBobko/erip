package com.erip.dsl;

public interface Wizard<T extends FlowData> extends Flow<T> {

    T replay();
}
