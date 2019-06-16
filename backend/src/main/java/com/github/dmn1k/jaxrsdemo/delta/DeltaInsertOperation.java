package com.github.dmn1k.jaxrsdemo.delta;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeltaInsertOperation {
    private String insert;

    private DeltaAttributes attributes;

    @JsonCreator
    public DeltaInsertOperation(@JsonProperty("insert") String insert,
                                @JsonProperty("attributes") DeltaAttributes attributes) {
        this.insert = insert;
        this.attributes = attributes;
    }

    public String getInsert() {
        return insert;
    }

    public DeltaAttributes getAttributes() {
        return attributes == null ? new DeltaAttributes() : attributes;
    }

    @Override
    public String toString() {
        return insert + " [" + attributes + "]";
    }
}
