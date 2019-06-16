package com.github.dmn1k.jaxrsdemo.delta;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum DeltaListType {
    UNORDERED("bullet");

    private String jsonValue;

    DeltaListType(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    @JsonCreator
    public static DeltaListType parse(String input) {
        return Arrays.stream(DeltaListType.values())
                .filter(v -> v.jsonValue.equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(input));
    }

    @JsonValue
    @Override
    public String toString() {
        return jsonValue;
    }
}
