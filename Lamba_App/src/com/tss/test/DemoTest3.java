package com.tss.test;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class DemoTest3 {

    public static void main(String[] args) {

        Consumer<Integer> factorial = (n) -> {
            int fact = 1;
            for (int i = 1; i <= n; i++) {
                fact *= i;
            }
            System.out.println("Factorial of " + n + ": " + fact);
        };
        factorial.accept(5);
        
        Function<Integer , Integer> factorial2 = (number) -> {
        	  int fact = 1;
              for (int i = 1; i <= number; i++) {
                  fact *= i;
              }
              return fact;	
        };
        
       System.out.println("function"+ factorial2.apply(20)); 
        
        

        Supplier<Integer> randomNumberSupplier = () -> {
            Random random = new Random();
            return random.nextInt(100); 
        };
        System.out.println("Random number: " + randomNumberSupplier.get());
        
        
        BiFunction<Integer, Integer, Integer> addition = (a, b) -> a + b;
        System.out.println("Addition of 10 + 20: " + addition.apply(10, 20));

        
        Predicate<Integer> isEven = (n) -> n % 2 == 0;

        int number = 8;

        boolean result = isEven.test(number);
        
        System.out.println("Is " + number + " even? " + result);
        
        
        
        
        Consumer<Integer> pattern = (rows) -> {
            int num = 1;
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= i; j++) {
                    System.out.print(num + " ");
                    num++;
                }
                System.out.println();
            }
        };
        System.out.println("Pattern:");
        pattern.accept(3);
    }
}
