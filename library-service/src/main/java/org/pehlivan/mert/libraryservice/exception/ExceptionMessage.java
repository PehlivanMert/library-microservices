package org.pehlivan.mert.libraryservice.exception;

public record ExceptionMessage(
        String timestamp,
        int status,
        String error,
        String message,
        String path
) {
}