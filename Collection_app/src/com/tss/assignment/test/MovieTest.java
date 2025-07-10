package com.tss.assignment.test;

import java.io.*;
import java.util.Scanner;

import com.tss.assignment.exception.MovieListEmptyException;
import com.tss.assignment.exception.MovieListFullException;
import com.tss.assignment.modal.Movie;

public class MovieTest {
    private static final int MAX_MOVIES = 5;
    private static final String FILE_NAME = "movies.ser";
    private static Movie[] movies = new Movie[MAX_MOVIES];

    public static void main(String[] args) {
        loadMovies();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Movie Store Menu ---");
            System.out.println("1. Display Movies");
            System.out.println("2. Add Movie");
            System.out.println("3. Delete Movie by ID");
            System.out.println("4. Clear All Movies");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            try {
                switch (choice) {
                    case 1 -> displayMovies();
                    case 2 -> addMovie(scanner);
                    case 3 -> deleteMovie(scanner);
                    case 4 -> clearAll();
                    case 5 -> {
                        saveMovies();
                        System.out.println("Exiting. Movie data saved.");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (MovieListFullException e) {
                System.out.println("Error: " + e.message());
            } catch (MovieListEmptyException e) {
                System.out.println("Error: " + e.message());
            }

        } while (choice != 5);

        scanner.close();
    }

    private static void displayMovies() {
        System.out.println("\n--- Movie List ---");
        boolean isEmpty = true;

        for (int i = 0; i < MAX_MOVIES; i++) {
            System.out.print("Slot " + i + ": ");
            if (movies[i] != null) {
                System.out.println(movies[i]);
                isEmpty = false;
            } else {
                System.out.println("[Empty]");
            }
        }

        if (isEmpty) {
            throw new MovieListEmptyException();
        }
    }

    private static void addMovie(Scanner scanner) {
        int index = -1;
        for (int i = 0; i < MAX_MOVIES; i++) {
            if (movies[i] == null) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new MovieListFullException();
        }

        System.out.print("Enter movie name: ");
        String name = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); 

        Movie newMovie = new Movie(name, genre, year);

        for (Movie m : movies) {
            if (m != null && m.getMovieId().equals(newMovie.getMovieId())) {
                System.out.println("Duplicate Movie ID found. Movie not added.");
                return;
            }
        }

        movies[index] = newMovie;
        System.out.println("Movie added at slot " + index + ": " + newMovie);
    }

    private static void deleteMovie(Scanner scanner) {
        System.out.print("Enter Movie ID to delete: ");
        String movieId = scanner.nextLine();

        boolean found = false;

        for (int i = 0; i < MAX_MOVIES; i++) {
            if (movies[i] != null && movies[i].getMovieId().equalsIgnoreCase(movieId)) {
                System.out.println("Deleted movie: " + movies[i]);
                movies[i] = null;
                found = true;
                break;
            }
        }

        if (!found) {
            throw new MovieListEmptyException();
        }
    }

    private static void clearAll() {
        for (int i = 0; i < MAX_MOVIES; i++) {
            movies[i] = null;
        }
        System.out.println("All movie slots cleared.");
    }

    private static void saveMovies() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(movies);
        } catch (IOException e) {
            System.out.println("Error saving movie list: " + e.getMessage());
        }
    }

    private static void loadMovies() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            movies = (Movie[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading movie list: " + e.getMessage());
        }
    }
}
