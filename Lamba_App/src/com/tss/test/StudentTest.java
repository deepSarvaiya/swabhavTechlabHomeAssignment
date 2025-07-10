package com.tss.test;

import com.tss.model.Student;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class StudentTest {

    public static void main(String[] args) {
        
        List<Student> students = Arrays.asList(
            new Student("A", 1, 90),
            new Student("B", 2, 65),
            new Student("C", 3, 78),
            new Student("D", 4, 55),
            new Student("E", 5, 80),
            new Student("F", 6, 72),
            new Student("G", 7, 88),
            new Student("H", 8, 49),
            new Student("I", 9, 95),
            new Student("J", 10, 67)
        );

        Predicate<Student> isHighScorer = student -> student.getMarks() > 75;

        Consumer<Student> congratulate = student ->
            System.out.println("Congratulations " + student.getName() + 
                "! You scored " + student.getMarks() + " marks.");

        System.out.println("Students who scored more than 75:");
        students.stream()
                .filter(isHighScorer)
                .forEach(congratulate);
    }
}
