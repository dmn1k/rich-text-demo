package com.github.dmn1k.jaxrsdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dmn1k.jaxrsdemo.delta.Delta;
import com.github.dmn1k.jaxrsdemo.delta.DeltaAttributes;
import com.github.dmn1k.jaxrsdemo.delta.DeltaInsertOperation;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.Arrays;

@Path("/todos")
@Stateless
public class TodoResource {
    @Inject
    private TodoItems todoItems;

    @Inject
    private ObjectMapper objectMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response find(@PathParam("id") long id) {
        return todoItems.find(id)
                .map(v -> Response.ok().entity(v).build())
                .orElse(Response.noContent().build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(TodoItemDto item) {
        try {
            String deltaAsString = objectMapper.writeValueAsString(item.getDescription());

            TodoItem todoItem = new TodoItem(deltaAsString, item.getDueDate());
            todoItems.persist(todoItem);

            System.out.println(item.getDescription().toString());

            Delta delta = objectMapper.readValue(todoItem.getDescription(), Delta.class);

            return Response.ok()
                    .entity(new TodoItemDto(delta, todoItem.getDueDate()))
                    .build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

}
