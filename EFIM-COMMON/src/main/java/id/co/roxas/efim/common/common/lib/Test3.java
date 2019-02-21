package id.co.roxas.efim.common.common.lib;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Test3 {
	//Complete the minimumSwaps function below.
	static int minimumSwaps(int[] arr) {
		int countMinimumSwaps = 0;
		while (isSequences(arr)) {
			int[] tempSwapValue = new int[arr.length];
			int tempX = 0;
			int tempY = 0;
			int counter = 1;
			// untuk mencari apakah benar
			for (int i = 0; i < arr.length; i++) {
				if (counter != arr[i]) {
					tempX = i;
					break;
				}
				counter++;
			}
			// untuk mencari posisi mana yang benar
			for (int i = 0; i < arr.length; i++) {
				if (counter == arr[i]) {
					tempY = i;
					break;
				}
			}
			
			for (int i = 0; i < arr.length; i++) {
				if (i != tempX && i != tempY) {
					tempSwapValue[i] = arr[i];
				} else {
					tempSwapValue[tempX] = arr[tempY];
					tempSwapValue[tempY] = arr[tempX];
				}
			}
			for (int i = 0; i < arr.length; i++) {
				arr[i] = tempSwapValue[i];
			}

			countMinimumSwaps++;
		}
		return countMinimumSwaps;
	}

	public static boolean isSequences(int[] a) {
		int counter = 1;
		for (int i : a) {
			if (counter != i) {
				return true;
			}
			counter++;
		}
		return false;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		int n = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		int[] arr = new int[n];

		String[] arrItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < n; i++) {
			int arrItem = Integer.parseInt(arrItems[i]);
			arr[i] = arrItem;
		}

		int res = minimumSwaps(arr);
		System.err.println("res : " + res);
		scanner.close();
	}
}
