package com.example.week2_lab2.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;
import java.util.Date;

@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {
    final ObjectMapper mapper = new ObjectMapper();
    public ObjectMapperContextResolver() {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.findAndRegisterModules();
    }
    @Override
    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }
}
