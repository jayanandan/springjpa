package com.example.springjpa.events;

import com.example.springjpa.entity.Books;

public class BookCreatedEvent{

    private Books books;
    public BookCreatedEvent(Books source) {
        this.books = source;
    }

    public Books getBooks() {
        return books;
    }
}
