package com.example.springjpa.entity.converter;

import com.example.springjpa.model.Header;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter
@Log4j2
public class HeaderToJsonConverter implements AttributeConverter<List<Header>,String> {


    @Override
    public String convertToDatabaseColumn(List<Header> headers) {
        return getObjectAsJsonString(headers);
    }

    @Override
    public List<Header> convertToEntityAttribute(String s) {
        return getObjectFromJsonString(s);
    }

    public String getObjectAsJsonString(Object obj){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Problem in serializing string to json");
        }
        return null;
    }

    public List<Header> getObjectFromJsonString(String headerStr){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(headerStr, new TypeReference<List<Header>>() {});
        } catch (JsonProcessingException e) {
            log.error("Problem in deserializing json to object");
        }
        return null;
    }
}
