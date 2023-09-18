package com.example.springjpa.service;

import com.example.springjpa.entity.PayloadEntity;
import com.example.springjpa.model.PayloadDetails;
import com.example.springjpa.repository.PayloadDetailsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class PayloadInterceptorService {

    private PayloadDetailsRepository payloadDetailsRepository;

    public PayloadInterceptorService(PayloadDetailsRepository payloadDetailsRepository) {
        this.payloadDetailsRepository = payloadDetailsRepository;
    }

    @Transactional
    public void savePayloadDetails(PayloadDetails payloadDetails){

        PayloadEntity payloadEntity = new PayloadEntity();
        payloadEntity.setRequestUri(payloadDetails.getRequestUri());
        payloadEntity.setRequestMethod(payloadDetails.getRequestMethod());
        payloadEntity.setRequestHeaders(getObjectAsJsonString(payloadDetails.getRequestHeaders()) );
        payloadEntity.setRequestPayload(payloadDetails.getRequestPayload());
        payloadEntity.setResponseHeaders(getObjectAsJsonString(payloadDetails.getResponseHeaders()));
        payloadEntity.setResponsePayload(payloadDetails.getResponsePayload());
        payloadEntity.setResponseStatus(payloadDetails.getResponseStatus());

        payloadDetailsRepository.save(payloadEntity);

    }

    public String getObjectAsJsonString(Object obj){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Problem in processing json string");
        }
        return null;
    }

}
