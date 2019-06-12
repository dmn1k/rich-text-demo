package com.github.dmn1k.jaxrsdemo;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class JAXRSConfiguration extends ResourceConfig {
    public JAXRSConfiguration(){
        packages("com.github.dmn1k.jaxrsdemo");

        register(CustomJacksonFeature.class);
    }
}
