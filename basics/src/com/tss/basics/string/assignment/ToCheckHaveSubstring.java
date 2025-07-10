package com.tss.basics.string.assignment;

import java.util.Scanner;

public class ToCheckHaveSubstring {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string: ");
        String originalString = scanner.next();

        System.out.println("Enter a Substring: ");
        String SubString = scanner.next();

        boolean IsSubstring = checkSubString(originalString, SubString); 
        
        if (IsSubstring) {
            System.out.println("SubString");
        } else {
            System.out.println("Not A Substring");
        }
		scanner.close();

    }

    public static boolean checkSubString(String originalString, String SubString) { 
        int originalLength = originalString.length();
        int subLength = SubString.length();

        if (subLength > originalLength) {
            return false;
        }

        for (int i = 0; i <= originalLength - subLength; i++) {
            int j;
            for (j = 0; j < subLength; j++) {
                if (originalString.charAt(i + j) != SubString.charAt(j)) {
                    break;
                }
            }
            if (j == subLength) {
                return true; 
            }
        }

        return false; 
    }
}
