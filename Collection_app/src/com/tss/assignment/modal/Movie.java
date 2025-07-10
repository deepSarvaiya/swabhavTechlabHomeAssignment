package com.tss.assignment.modal;

import java.io.Serializable;

public class Movie implements Serializable {
    private static final long serialVersionUID = 1L; 

    private String movieId, movieName, movieGenre;
    private int year;

    public Movie(String movieName, String movieGenre, int year) {
        this.movieName = movieName;
        this.movieGenre = movieGenre;
        this.year = year;
        this.movieId = generateId(movieName, movieGenre, year);
    }

    private String generateId(String name, String genre, int year) {
        String namePart = name.length() >= 2 ? name.substring(0, 2) : name;
        String genrePart = genre.length() >= 2 ? genre.substring(0, 2) : genre;
        String yearPart = String.valueOf(year);
        return namePart + genrePart + yearPart.substring(yearPart.length() - 2);
    }

    public String getMovieId() {
        return movieId;
    }

    public String toString() {
        return "Movie ID: " + movieId + ", Name: " + movieName + ", Genre: " + movieGenre + ", Year: " + year;
    }
}
