package com.tss.basics.array.assignments;

import java.util.Scanner;

public class FindPairOfAvarageOfArray {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter size of array:");
        int size = scanner.nextInt();
        int[] array = new int[size];
        int sum = 0;

        for (int i = 0; i < size; i++) {
            System.out.print("Enter element " + (i+1) + ":");
            array[i] = scanner.nextInt();
            sum += array[i];
        }

        int average = sum / size;  
        int targetSum = 2 * average;

        quickSort(array, 0, size - 1);

        int left = 0;
        int right = size - 1;
        boolean pairFound = false;

        System.out.println("Pairs with average = " + average);

        while (left < right) {
            int currentSum = array[left] + array[right];

            if (currentSum == targetSum) {
                System.out.println("(" + array[left] + ", " + array[right] + ")");
                pairFound = true;
                left++;
                right--;
            } else if (currentSum < targetSum) {
                left++;
            } else {
                right--;
            }
        }

        if (!pairFound) {
            System.out.println("No pairs found");
        }
		scanner.close();

    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
