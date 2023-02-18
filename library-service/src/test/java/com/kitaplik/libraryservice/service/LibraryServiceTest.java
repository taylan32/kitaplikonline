package com.kitaplik.libraryservice.service;

import com.kitaplik.libraryservice.client.BookServiceClient;
import com.kitaplik.libraryservice.dto.BookDto;
import com.kitaplik.libraryservice.dto.BookIdDto;
import com.kitaplik.libraryservice.dto.LibraryDto;
import com.kitaplik.libraryservice.exception.LibraryNotFoundException;
import com.kitaplik.libraryservice.model.Library;
import com.kitaplik.libraryservice.repository.LibraryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {

    private LibraryService libraryService;

    private LibraryRepository libraryRepository;
    private BookServiceClient bookServiceClient;

    @BeforeEach
    void setUp() {
        libraryRepository = Mockito.mock(LibraryRepository.class);
        bookServiceClient = Mockito.mock(BookServiceClient.class);
        libraryService = new LibraryService(libraryRepository, bookServiceClient);
    }

    @AfterEach
    void tearDown() {

    }
    @DisplayName("Test get all books in library by id when library id exists should return book list dto")
    @Test
    void testGetAllBooksInLibraryById_whenLibraryIdExists_shouldReturnDetailedBookListWithBookDto() {
        String id = "libraryId";
        List<String> userBook = Arrays.asList("book1", "book2", "book3");
        Library library = new Library(id, userBook);
        BookDto book1 = new BookDto(new BookIdDto("book1", "isbn1"), "title1", 2023, "author1", "press1");
        BookDto book2 = new BookDto(new BookIdDto("book2", "isbn2"), "title2", 2023, "author2", "press2");
        BookDto book3 = new BookDto(new BookIdDto("book3", "isbn3"), "title3", 2023, "author3", "press3");

        List<BookDto> bookDtoList = Arrays.asList(book1, book2, book3);
        LibraryDto expectedResult = new LibraryDto(id, bookDtoList);

        Mockito.when(libraryRepository.findById(id)).thenReturn(Optional.of(library));
        Mockito.when(bookServiceClient.getBookById("book1")).thenReturn(ResponseEntity.ok(book1));
        Mockito.when(bookServiceClient.getBookById("book2")).thenReturn(ResponseEntity.ok(book2));
        Mockito.when(bookServiceClient.getBookById("book3")).thenReturn(ResponseEntity.ok(book3));

        LibraryDto result = libraryService.getAllBooksInLibraryById(id);

        assertEquals(expectedResult, result);

        Mockito.verify(libraryRepository).findById(id);
        Mockito.verify(bookServiceClient).getBookById("book1");
        Mockito.verify(bookServiceClient).getBookById("book2");
        Mockito.verify(bookServiceClient).getBookById("book3");
        Mockito.verify(bookServiceClient, new Times(3)).getBookById(Mockito.any(String.class));

    }

    @DisplayName("test get all books in library by id should throw LibraryNotFound Exception when parameter of libraryId does not exist")
    @Test
    void testGetAllBooksInLibrary_whenLibraryIdDoesNotExist_shouldThrowLibraryNotFoundException() {
        String id = "libraryId";

        Mockito.when(libraryRepository.findById(id)).thenReturn(Optional.empty());

        //assertThrows(LibraryNotFoundException.class, () -> libraryService.getAllBooksInLibraryById(id));

        Assertions.assertThatThrownBy(() -> libraryService.getAllBooksInLibraryById(id))
                .isInstanceOf(LibraryNotFoundException.class)
                .hasMessageContaining("Library could not found by id " + id);

        Mockito.verify(libraryRepository).findById(id);
        Mockito.verifyNoInteractions(bookServiceClient);


    }


}