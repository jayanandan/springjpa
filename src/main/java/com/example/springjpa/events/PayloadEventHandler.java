package com.example.springjpa.events;

import com.example.springjpa.model.PayloadDetails;
import com.example.springjpa.service.PayloadDetailsService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class PayloadEventHandler {

    PayloadDetailsService payloadDetailsService;

    public PayloadEventHandler(PayloadDetailsService payloadDetailsService) {
        this.payloadDetailsService = payloadDetailsService;
    }

    @Async
    @EventListener({PayloadEvent.class})
    public void onPayloadEvent(PayloadEvent payloadEvent){
        PayloadDetails payloadDetails = payloadEvent.getPayloadDetails();
        payloadDetailsService.savePayloadDetails(payloadDetails);

    }

}
