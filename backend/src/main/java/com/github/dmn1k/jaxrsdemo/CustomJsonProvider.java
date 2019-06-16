package com.github.dmn1k.jaxrsdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomJsonProvider extends JacksonJaxbJsonProvider {
    @Inject
    private ObjectMapper objectMapper;

    @PostConstruct
    public void postConstruct() {
        setMapper(objectMapper);
    }
}

