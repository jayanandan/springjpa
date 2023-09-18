package com.example.springjpa.events;

import com.example.springjpa.model.PayloadDetails;

public class PayloadEvent {

    private PayloadDetails payloadDetails;

    public PayloadEvent(PayloadDetails payloadDetails) {
        this.payloadDetails = payloadDetails;
    }

    public PayloadDetails getPayloadDetails() {
        return payloadDetails;
    }
}
