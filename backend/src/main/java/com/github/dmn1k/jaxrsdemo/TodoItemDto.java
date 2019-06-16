package com.github.dmn1k.jaxrsdemo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dmn1k.jaxrsdemo.delta.Delta;

import java.time.LocalDate;

public class TodoItemDto {
    private Delta description;
    private LocalDate dueDate;

    @JsonCreator
    public TodoItemDto(@JsonProperty("description") Delta description, @JsonProperty("dueDate") LocalDate dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    public Delta getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
