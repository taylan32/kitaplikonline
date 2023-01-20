package com.kitaplik.bookservice.dto.converter;

import com.kitaplik.bookservice.dto.BookIdDto;
import org.springframework.stereotype.Component;

@Component
public class BookIdDtoConverter {

    public BookIdDto convert(String id, String isbn) {
        return new BookIdDto(id, isbn);
    }

}
