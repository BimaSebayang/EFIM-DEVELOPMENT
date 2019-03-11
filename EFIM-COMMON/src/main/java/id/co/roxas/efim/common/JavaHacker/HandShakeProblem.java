package id.co.roxas.efim.common.JavaHacker;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class HandShakeProblem {
	/*
     * Complete the handshake function below.
     */
	static int pairs(int k, int[] arr) {
		int i = 0;
		int j = 0;
		int count = 0;
	
		while(i<arr.length) {
			while(arr[i]+arr[j]==k) {
				count++;
			}
			System.err.println(i+","+j);
			j++;
			i++;
		}
//        int count = 0;
//		for (int arri : arr) {
//			for(int arrj : arr) {
//				 if(arri-arrj==k) {
//					 count++;
//				 }
//			}
//		}
        return count;
    }

	  private static final Scanner scanner = new Scanner(System.in);
	  public static void main(String[] args) throws IOException {
	        String[] nk = scanner.nextLine().split(" ");

	        int n = Integer.parseInt(nk[0]);

	        int k = Integer.parseInt(nk[1]);

	        int[] arr = new int[n];

	        String[] arrItems = scanner.nextLine().split(" ");
	        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	        for (int i = 0; i < n; i++) {
	            int arrItem = Integer.parseInt(arrItems[i]);
	            arr[i] = arrItem;
	        }

	        int result = pairs(k, arr);
            System.err.println(result);

	        scanner.close();
	    }
}
