package com.github.dmn1k.jaxrsdemo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class TodoItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    private String description;

    private LocalDate dueDate;

    public TodoItem() {
        // for jpa
    }

    public TodoItem(String description, LocalDate dueDate) {
        this.dueDate = dueDate;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                '}';
    }
}
