package com.tss.test;

import com.tss.model.IFactorial;
import com.tss.model.IAddition;
import com.tss.model.IPattern;

public class DemoTest2 {

    public static void main(String[] args) {

        IFactorial factorial = (n) -> {
            int fact = 1;
            for (int i = 1; i <= n; i++) {
                fact *= i;
            }
            return fact;
        };
        System.out.println("Factorial of 5: " + factorial.compute(5));

        IAddition addition = (a, b) -> a + b;
        System.out.println("Addition of 10 + 20: " + addition.add(10, 20));

        IPattern pattern = (rows) -> {
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
        pattern.print(3);
    }
}
