package com.example.document_service.exception;

public class NotValidDataException extends RuntimeException{
    public NotValidDataException(String message) {
        super(message);
    }
}
