package com.kitaplik.libraryservice.client;

import com.kitaplik.libraryservice.dto.BookDto;
import com.kitaplik.libraryservice.dto.BookIdDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "book-service", path = "/api/v1/book")
public interface BookServiceClient {

    Logger logger = LoggerFactory.getLogger(BookServiceClient.class);

    @GetMapping("/isbn/{isbn}")
    @CircuitBreaker(name = "getBookByIsbnCircuitBreaker", fallbackMethod = "getBookByIsbnFallBack")
    ResponseEntity<BookIdDto> getBookByIsbn(@PathVariable String isbn);


    // book not found by isbn. hata vermek yerine default nesne yaratabiliriz.
    // method adı ile circuitbreaker fallbackMethod adı aynı olmalı
    // parametre olarak methodun aldığı parametreler ve exception alır
    default ResponseEntity<BookIdDto> getBookByIsbnFallBack(String isbn, Exception exception) {
        logger.info("Book not found by isbn: " + isbn + ", returning default BookDto object");
        return ResponseEntity.ok(new BookIdDto("default-book", "default-isbn"));
    }

    @GetMapping("/{bookId}")
    @CircuitBreaker(name = "getBookByIdCircuitBreaker", fallbackMethod = "getBookByIdFallBack")
    ResponseEntity<BookDto> getBookById(@PathVariable String bookId);

    default ResponseEntity<BookDto> getBookByIdFallBack(String bookId, Exception exception) {
        return ResponseEntity.ok(new BookDto(
                new BookIdDto("default-book", "default-isbn"),
                "default-title",
                0,
                "default-author",
                "default-press-name"
        ));
    }

}
