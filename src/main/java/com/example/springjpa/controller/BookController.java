package com.example.springjpa.controller;

import com.example.springjpa.entity.Books;
import com.example.springjpa.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    BooksRepository booksRepository;

    @PostMapping(path = "/create",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Books> create(@RequestBody Books book){

        System.out.println(book);
        booksRepository.save(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);

    }

}
