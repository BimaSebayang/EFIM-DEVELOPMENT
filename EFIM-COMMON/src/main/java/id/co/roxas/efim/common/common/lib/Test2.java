package id.co.roxas.efim.common.common.lib;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Test2 {

	// Complete the rotLeft function below.
	static int[] rotLeft(int[] a, int d) {
        
		int[] newRot = new int[a.length];
		
		while (d > 0) {
			for (int i = a.length - 1; i >= 0; i--) {
				if (i == a.length - 1) {
					newRot[a.length - 1] = a[0];
				} else {
					newRot[i] = a[i + 1];
				}
			}
			
			for(int j = 0; j<newRot.length; j++) {
				a[j] = newRot[j];
			}
			
			d--;
			
		}
		return a;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		String[] nd = scanner.nextLine().split(" ");

		int n = Integer.parseInt(nd[0]);

		int d = Integer.parseInt(nd[1]);

		int[] a = new int[n];

		String[] aItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < n; i++) {
			if(i<=aItems.length-1) {
			int aItem = Integer.parseInt(aItems[i]);
			a[i] = aItem;
			}
		}

		int[] result = rotLeft(a, d);

		for (int i = 0; i < result.length; i++) {
			System.err.println(result[i] + " ");
		}

		scanner.close();
	}
}
