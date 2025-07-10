package com.tss.assignment.exception;

public class MovieListFullException extends RuntimeException {
    public MovieListFullException() {
        super("Movie list is already full. Cannot add more than 5 movies.");
    }

    public String message() {
        return getMessage();
    }
}
