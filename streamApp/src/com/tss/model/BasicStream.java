
package com.tss.model;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BasicStream {

    public static void main(String[] args) {
        
        List<Integer> numbers = Arrays.asList(2,3,4,5,6);
        
        numbers.stream().forEach(number -> System.out.println("All numbers :" + number));
        
        numbers.stream().forEach(number -> System.out.println(" Square of numbers :"+number*number));
        
        List<Integer> oddNumbers = numbers.stream()
                .filter(number -> number%2!=0)
                .collect(Collectors.toList());
        System.out.println("Only Odd Numbers :"+ oddNumbers);
        
        Set<Integer> squareNumbers = numbers.stream()
                .map(number -> number*number)
                .collect(Collectors.toSet());
        System.out.println("Squares of number in set :"+squareNumbers);
        
        List<Integer> evenNumbersSquare = numbers.stream().
                filter(number -> number%2==0)
                .map(number -> number*number)
                .collect(Collectors.toList());
        System.out.println("Even Numbers Square :"+evenNumbersSquare);
        
        int sum = numbers.stream().reduce(0,(number1,number2) -> number1 + number2);
        System.out.println("Sum of all numbers :"+sum);
        
        System.out.println("Count :"+numbers.stream().count());
        numbers.stream().limit(3).forEach(number -> System.out.println("Only First three :"+number ));
        
        numbers.stream().skip(2).forEach(number -> System.out.println(number));
        
        
        boolean anyMatch = numbers.stream().anyMatch(number -> number > 6);
        System.out.println("AnyMatch : "+anyMatch);
        
        boolean allMatch = numbers.stream().allMatch(number -> number >= 3);
        System.out.println("All Match :" + allMatch);
        
        List<Integer> SortedList = numbers.stream().sorted().collect(Collectors.toList());
        System.out.println("SortedList :" + SortedList);
        
        System.out.println(numbers.stream().min(Comparator.naturalOrder()));
        
        System.out.println(numbers.stream().max(Comparator.naturalOrder()));
        
        
    }

}
