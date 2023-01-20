package com.kitaplik.libraryservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBookRequest {

    @NotBlank(message = "Book id is not allowed to be empty")
    private String id;

    @NotBlank(message = "Isbn number is not allowed to be empty")
    private String isbn;
}
