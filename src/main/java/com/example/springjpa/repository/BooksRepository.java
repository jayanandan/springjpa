package com.example.springjpa.repository;

import com.example.springjpa.entity.Books;
import org.springframework.data.repository.CrudRepository;

public interface BooksRepository extends CrudRepository<Books,Long> {

}
