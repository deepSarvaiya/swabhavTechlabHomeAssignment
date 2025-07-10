package com.tss.test;

import java.io.*;
import java.util.*;
import com.tss.model.MusicPlaylist;

public class MusicPlaylistTest {

    private static final String FILE_NAME = "playlist.ser";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<MusicPlaylist> musicPlaylist = loadPlaylist();

        boolean loop = true;
        while (loop) {
            displayMenu();
            Integer choice = getChoice(scanner);

            switch (choice) {
                case 1 -> addSong(scanner, musicPlaylist);
                case 2 -> removeSong(scanner, musicPlaylist);
                case 3 -> searchSong(scanner, musicPlaylist);
                case 4 -> displayAll(musicPlaylist);
                case 5 -> shufflePlaylist(musicPlaylist);
                case 6 -> {
                    savePlaylist(musicPlaylist);
                    System.out.println("Playlist saved. Exiting...");
                    loop = false;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n+-------PlayList Manager-------+");
        System.out.println("| 1. Add a Song                |");
        System.out.println("| 2. Remove a Song             |");
        System.out.println("| 3. Search By Title           |");
        System.out.println("| 4. Display All Songs         |");
        System.out.println("| 5. Shuffle PlayList          |");
        System.out.println("| 6. Save & Exit               |");
        System.out.println("+------------------------------+");
        System.out.print("Enter Your Choice: ");
    }

    private static Integer getChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void shufflePlaylist(List<MusicPlaylist> musicPlaylist) {
        Collections.shuffle(musicPlaylist);
        displayAll(musicPlaylist);
    }

    private static void displayAll(List<MusicPlaylist> musicPlaylist) {
        if (musicPlaylist.isEmpty()) {
            System.out.println("Playlist is empty.");
            return;
        }
        System.out.printf("%-30s %-50s %-10s%n", "Title", "Artist", "Duration (min)");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        musicPlaylist.forEach(song ->
            System.out.printf("%-30s %-50s %-10.2f%n",
                song.getTitle(), song.getSinger(), song.getDuration()));
    }

    private static void addSong(Scanner scanner, List<MusicPlaylist> musicPlaylist) {
        scanner.nextLine();
        System.out.print("Enter Title of Song: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter Singer of Song: ");
        String singerName = scanner.nextLine().trim();
        System.out.print("Enter Duration of Song (minutes): ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid duration: ");
            scanner.next();
        }
        double duration = scanner.nextDouble();

        boolean exists = musicPlaylist.stream()
                .anyMatch(song -> song.getTitle().equalsIgnoreCase(title)
                               && song.getSinger().equalsIgnoreCase(singerName));

        if (exists) {
            System.out.println("Song already exists in the playlist.");
        } else {
            musicPlaylist.add(new MusicPlaylist(title, singerName, duration));
            System.out.println("Song added!");
        }
    }

    private static void removeSong(Scanner scanner, List<MusicPlaylist> musicPlaylist) {
        if (musicPlaylist.isEmpty()) {
            System.out.println("Playlist is empty!");
            return;
        }
        scanner.nextLine();
        System.out.print("Enter Title of Song to Remove: ");
        String title = scanner.nextLine().trim();

        boolean removed = musicPlaylist.removeIf(song -> song.getTitle().equalsIgnoreCase(title));

        if (removed) {
            System.out.println(title + " removed successfully!");
        } else {
            System.out.println(title + " does not exist in the playlist.");
        }
    }

    private static void searchSong(Scanner scanner, List<MusicPlaylist> musicPlaylist) {
        scanner.nextLine();
        System.out.print("Enter Title of Song to Search: ");
        String title = scanner.nextLine().trim();

        Optional<MusicPlaylist> result = musicPlaylist.stream()
                .filter(song -> song.getTitle().equalsIgnoreCase(title))
                .findFirst();

        if (result.isPresent()) {
            System.out.println("Found: " + result.get().getTitle() + " by " + result.get().getSinger());
        } else {
            System.out.println(title + " is not present in the playlist.");
        }
    }

    private static void savePlaylist(List<MusicPlaylist> musicPlaylist) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(musicPlaylist);
        } catch (IOException e) {
            System.out.println("Error saving playlist: " + e.getMessage());
        }
    }

    private static List<MusicPlaylist> loadPlaylist() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<MusicPlaylist>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading playlist: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}