package com.erip.dsl;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public abstract class FlowData {

    @Id
    private ObjectId id;
}
