package com.example.springjpa.service;

import com.example.springjpa.entity.PayloadEntity;
import com.example.springjpa.model.PayloadDetails;
import com.example.springjpa.repository.PayloadDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
public class PayloadDetailsService {

    private PayloadDetailsRepository payloadDetailsRepository;

    public PayloadDetailsService(PayloadDetailsRepository payloadDetailsRepository) {
        this.payloadDetailsRepository = payloadDetailsRepository;
    }

    @Transactional
    public void savePayloadDetails(PayloadDetails payloadDetails){

        PayloadEntity payloadEntity = new PayloadEntity();
        payloadEntity.setRequestUri(payloadDetails.getRequestUri());
        payloadEntity.setRequestMethod(payloadDetails.getRequestMethod());
        payloadEntity.setRequestHeaders(payloadDetails.getRequestHeaders());
        payloadEntity.setRequestPayload(payloadDetails.getRequestPayload());
        payloadEntity.setResponseHeaders(payloadDetails.getResponseHeaders());
        payloadEntity.setResponsePayload(payloadDetails.getResponsePayload());
        payloadEntity.setResponseStatus(payloadDetails.getResponseStatus());
        payloadDetailsRepository.save(payloadEntity);
    }

    public Optional<PayloadDetails> getPayloadDetails(Long id){
        Optional<PayloadEntity> payload = payloadDetailsRepository.findById(id);

        if(payload.isPresent()) {
            PayloadEntity p = payload.get();
            PayloadDetails payloadDetails = new PayloadDetails();
            payloadDetails.setRequestUri(p.getRequestUri());
            payloadDetails.setRequestMethod(p.getRequestMethod());
            payloadDetails.setRequestHeaders(p.getRequestHeaders());
            payloadDetails.setRequestPayload(p.getRequestPayload());
            payloadDetails.setResponseHeaders(p.getResponseHeaders());
            payloadDetails.setResponsePayload(p.getResponsePayload());
            payloadDetails.setResponseStatus(p.getResponseStatus());
            return Optional.of(payloadDetails);
        }else{
            return Optional.empty();
        }
    }
}
