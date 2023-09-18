package com.example.springjpa;

import com.example.springjpa.entity.Books;
import com.example.springjpa.events.EventPublisher;
import com.example.springjpa.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableJpaRepositories
public class SpringjpaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringjpaApplication.class, args);
	}

/*	@Autowired
	BooksRepository booksRepository;

	@Autowired
	EventPublisher eventPublisher;*/

	@Override
	@Transactional
	public void run(String... args) throws Exception {

        /*Books book = new Books();
		book.setTitle("MyFirstBook");
		book.setRating(Long.valueOf(5));
		book.setAuthorFirstName("Jayanandan");
		book.setAuthorLastName("T");

		booksRepository.save(book);*/

	/*	Iterable<Books> books = booksRepository.findAll();
		System.out.println(books);

		for (Books book : books) {
			eventPublisher.publishEvent(book);
		}*/

	}
}
