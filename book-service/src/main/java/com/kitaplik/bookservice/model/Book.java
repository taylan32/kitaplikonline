package com.kitaplik.bookservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "book_year", nullable = false)
    private Integer bookYear;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "press_name", nullable = false)
    private String pressName;

    @Column(name = "isbn", nullable = false)
    private String isbn;




}
