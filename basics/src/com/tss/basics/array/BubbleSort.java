package com.tss.basics.array;

import java.util.Scanner;

public class BubbleSort {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter size of array:");
		int n = scanner.nextInt();
		int array[] = new int[n];

		for (int i = 0; i < n; i++) {
			System.out.println("Enter element " + i + ":");
			array[i] = scanner.nextInt();
		}

		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - 1 - i; j++) {
				if (array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}

		System.out.println("Sorted array in ascending order:");
		for (int i = 0; i < n; i++) {
			System.out.print(array[i] + " ");
		}
		scanner.close();

	}
}
