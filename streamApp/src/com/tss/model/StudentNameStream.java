package com.tss.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentNameStream {

    public static void main(String[] args) {

        List<String> nameList = Arrays.asList("Jay", "Nimesh", "Mark", "Mahesh", "Ramesh");

        List<String> first3Sorted = nameList.stream()
                .limit(3)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("a. First 3 sorted: " + first3Sorted);

        List<String> filteredWithA = nameList.stream()
                .limit(3)
                .filter(name -> name.toLowerCase().contains("a"))
                .sorted()
                .collect(Collectors.toList());
        System.out.println("b. First 3 sorted with 'a': " + filteredWithA);

        List<String> sortedDesc = nameList.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("c. All names descending: " + sortedDesc);

        List<String> first3Chars = nameList.stream()
                .map(name -> name.length() >= 3 ? name.substring(0, 3) : name)
                .collect(Collectors.toList());
        System.out.println("d. First 3 characters: " + first3Chars);

        List<String> shortNames = nameList.stream()
                .filter(name -> name.length() <= 4)
                .collect(Collectors.toList());
        System.out.println("e. Names <= 4 characters: " + shortNames);
    }
}
