package com.tss.assignment.exception;

public class MovieListEmptyException extends RuntimeException {
    public MovieListEmptyException() {
        super("Movie slot is already empty or movie ID not found.");
    }

    public String message() {
        return getMessage();
    }
}
