package com.example.springjpa.events;

import com.example.springjpa.model.PayloadDetails;
import com.example.springjpa.service.PayloadInterceptorService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class PayloadEventHandler {

    PayloadInterceptorService payloadInterceptorService;

    public PayloadEventHandler(PayloadInterceptorService payloadInterceptorService) {
        this.payloadInterceptorService = payloadInterceptorService;
    }

    @Async
    @EventListener({PayloadEvent.class})
    public void onPayloadEvent(PayloadEvent payloadEvent){
        PayloadDetails payloadDetails = payloadEvent.getPayloadDetails();
        payloadInterceptorService.savePayloadDetails(payloadDetails);

    }

}
