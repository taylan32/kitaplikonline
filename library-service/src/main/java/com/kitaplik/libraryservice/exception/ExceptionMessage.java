package com.kitaplik.libraryservice.exception;

public record ExceptionMessage(
        String timestamp,
        int httpstatus,
        String error,
        String message,
        String path
) {
}
