package com.kitaplik.bookservice.data;

import com.kitaplik.bookservice.model.Book;
import com.kitaplik.bookservice.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInit implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInit(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Book book1 = new Book(null, "Dünyanın Gözü", 2000, "Robert Jordan", "İthaki Yayınevi","123456");
        Book book2 = new Book(null, "Yüzüklerin Efendisi", 1960, "J.R.R. Tolkien ", "Metis Yayıncılık","456789");
        Book book3 = new Book(null, "Harry Potter ve Felsefe Taşı", 1997, "J. K. Rowling", "YKB Yayınları","987654");

        List<Book> bookList = bookRepository.saveAll(Arrays.asList(book1,book2,book3));
        System.out.println(bookList);

    }
}
