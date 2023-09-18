package com.example.springjpa.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "BOOKS")
@Setter
@Getter
@Builder
public class Books {


    @Id
    @Column(name="book_id")
    @SequenceGenerator(name = "booksequence",allocationSize = 1,sequenceName = "books_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "booksequence")
    private Long bookId;
    @Column(name = "title")
    private String title;
    @Column(name = "author_last_name")
    private String authorLastName;
    @Column(name = "author_first_name")
    private String authorFirstName;
    @Column(name = "rating")
    private Long rating;

    @Override
    public String toString() {
        return "Books{" +
                "title='" + title + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", rating=" + rating +
                '}';
    }
}
