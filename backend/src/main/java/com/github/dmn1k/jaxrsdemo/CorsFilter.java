package com.github.dmn1k.jaxrsdemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

// CAUTION: May not be what you want in production since it allows ALL origins
@Provider
public class CorsFilter implements ContainerResponseFilter {

    public final static String DEFAULT_ALLOWED_HEADERS = "origin,accept,content-type";

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        final MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Headers", getRequestedAllowedHeaders(requestContext));
        headers.add("Access-Control-Expose-Headers", getRequestedExposedHeaders(requestContext));
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.add("Access-Control-Max-Age", "216000");
    }

    private String getRequestedAllowedHeaders(ContainerRequestContext responseContext) {
        List<String> headers = responseContext.getHeaders().getOrDefault("Access-Control-Allow-Headers", new ArrayList<>());
        return createHeaderList(headers, DEFAULT_ALLOWED_HEADERS);
    }

    private String getRequestedExposedHeaders(ContainerRequestContext responseContext) {
        List<String> headers = responseContext.getHeaders().getOrDefault("Access-Control-Expose-Headers", new ArrayList<>());
        return createHeaderList(headers, DEFAULT_ALLOWED_HEADERS);
    }

    private String createHeaderList(List<String> headers, String defaultHeaders) {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append(defaultHeaders);
        headers.forEach(h -> resultBuilder.append(",").append(h));
        return resultBuilder.toString();
    }

}
