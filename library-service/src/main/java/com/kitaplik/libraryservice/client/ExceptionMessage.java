package com.kitaplik.libraryservice.client;

import org.springframework.http.HttpStatus;

public record ExceptionMessage(
        String timestamp,
        HttpStatus httpstatus,
        String error,
        String message,
        String path
) {
}
