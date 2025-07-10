package com.tss.test;

import java.io.FileReader;
import java.io.IOException;

public class FileReadTest {

    public static void main(String[] args) {

        int charCount = 0;
        int lineCount = 0;
        int wordCount = 0;
        boolean inWord = false;

        try (FileReader reader = new FileReader("input.txt")) {
            int ch;

            while ((ch = reader.read()) != -1) {

//                if (ch != ' ' && ch != '\n' && ch != '\r' && ch != '\t' ) {
//                    charCount++;
//                }
              charCount++;

                if (ch == '\n') {
                    lineCount++;
                }

                if (Character.isWhitespace(ch)) {
                    inWord = false;
                } else if (!inWord) {
                    wordCount++;
                    inWord = true;
                }
            }

            if (charCount > 0 && lineCount == 0) {
                lineCount = 1;
            }

            System.out.println("Total number of lines: " + (lineCount+1));
            System.out.println("Total number of characters: " + charCount);
            System.out.println("Total number of words: " + wordCount);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
