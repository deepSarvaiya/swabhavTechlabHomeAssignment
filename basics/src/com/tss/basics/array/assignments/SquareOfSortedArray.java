package com.tss.basics.array.assignments;

import java.util.Scanner;

public class SquareOfSortedArray {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter size of array:");
        int n = scanner.nextInt();
        int array[] = new int[n];

        System.out.println("Please enter the array in sorted order:");

        for (int i = 0; i < n; i++) {
            System.out.print("Enter element " + i + ": ");
            array[i] = scanner.nextInt();
        }

        int[] result = sortedSquares(array);

        System.out.println("Squares of array elements in sorted order:");
        for (int num : result) {
            System.out.print(num + " ");
        }

        scanner.close();
    }

    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0;
        int right = n - 1;
        int pos = n - 1;

        while (left <= right) {
            int leftSq = nums[left] * nums[left];
            int rightSq = nums[right] * nums[right];

            if (leftSq > rightSq) {
                result[pos] = leftSq;
                left++;
            } else {
                result[pos] = rightSq;
                right--;
            }
            pos--;
        }

        return result;
    }
}
