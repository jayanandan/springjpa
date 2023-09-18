package com.example.springjpa.events;

import com.example.springjpa.entity.Books;
import com.example.springjpa.model.PayloadDetails;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    private ApplicationEventPublisher applicationEventPublisher;

    public EventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(Books book){
        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
    }

    public void publishPayloadEvent(PayloadDetails payloadDetails){
        this.applicationEventPublisher.publishEvent(new PayloadEvent(payloadDetails));
    }

}
