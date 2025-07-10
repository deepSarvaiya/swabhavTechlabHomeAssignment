package com.tss.basics.string.assignment;

import java.util.Scanner;

public class ShiftCharacterOfString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        System.out.print("Enter number to shift: ");
        int shift = scanner.nextInt();

        String shifted = shiftCharacters(input, shift);

        System.out.println("Shifted string: " + shifted);
        
        scanner.close();
    }

    public static String shiftCharacters(String input, int shift) {
        String result = "";

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                result += (char) ((ch - 'a' + shift) % 26 + 'a');
            } else if (ch >= 'A' && ch <= 'Z') {
                result += (char) ((ch - 'A' + shift) % 26 + 'A');
            } else {
                result += ch; 
            }
        }

        return result;
    }
}
