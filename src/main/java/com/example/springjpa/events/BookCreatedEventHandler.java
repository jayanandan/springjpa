package com.example.springjpa.events;

import com.example.springjpa.entity.Books;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class BookCreatedEventHandler {

    @Async
    @EventListener({BookCreatedEvent.class})
    public void onBookCreatedEvent(BookCreatedEvent bookCreatedEvent){

        Books books = bookCreatedEvent.getBooks();
        System.out.println(books);

    }

}
