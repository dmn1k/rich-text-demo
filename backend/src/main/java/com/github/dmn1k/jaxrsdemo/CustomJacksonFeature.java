package com.github.dmn1k.jaxrsdemo;

import com.fasterxml.jackson.jaxrs.base.JsonMappingExceptionMapper;
import com.fasterxml.jackson.jaxrs.base.JsonParseExceptionMapper;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.internal.util.PropertiesHelper;

import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

public class CustomJacksonFeature implements Feature {
    private static final String JSON_FEATURE = CustomJacksonFeature.class.getSimpleName();
    public static final String JSON_FEATURE_PROPERTY_NAME = "jersey.config.jsonFeature";

    public boolean configure(FeatureContext context) {
        Configuration config = context.getConfiguration();
        String jsonFeature = CommonProperties.getValue(config.getProperties(),
                config.getRuntimeType(), JSON_FEATURE_PROPERTY_NAME, JSON_FEATURE, String.class);
        if (!JSON_FEATURE.equalsIgnoreCase(jsonFeature)) {
            return false;
        } else {
            context.property(PropertiesHelper.getPropertyNameForRuntime(JSON_FEATURE_PROPERTY_NAME, config.getRuntimeType()), JSON_FEATURE);
            if (!config.isRegistered(CustomJsonProvider.class)) {
                context.register(JsonParseExceptionMapper.class);
                context.register(JsonMappingExceptionMapper.class);
                context.register(CustomJsonProvider.class, new Class[]{MessageBodyReader.class, MessageBodyWriter.class});
            }

            return true;
        }
    }
}
