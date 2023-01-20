package com.kitaplik.bookservice.service;

import com.kitaplik.bookservice.dto.BookDto;
import com.kitaplik.bookservice.dto.BookIdDto;
import com.kitaplik.bookservice.dto.converter.BookDtoConverter;
import com.kitaplik.bookservice.exception.BookNotFoundException;
import com.kitaplik.bookservice.model.Book;
import com.kitaplik.bookservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookDtoConverter converter;

    public BookService(BookRepository bookRepository, BookDtoConverter converter) {
        this.bookRepository = bookRepository;
        this.converter = converter;
    }


    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public BookIdDto findByIsbn(String isbn) {
        return bookRepository.findBookByIsbn(isbn)
                .map(book -> new BookIdDto(book.getId(), book.getIsbn()))
                .orElseThrow(() -> new BookNotFoundException("Book could not found by isbn: " + isbn));
    }

    public BookDto findBookDetailsById(String id) {
        return bookRepository.findById(id)
                .map(converter::convert)
                .orElseThrow(() -> new BookNotFoundException("Book could not found by id: " + id));
    }


}
