package id.co.roxas.efim.common.common.lib;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Test1 {

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
       
    	int[][] subArr = new int[3][3];
    	
    	//menentukan banyak sub arr yang bisa didapat --start
    	int rowExist = arr.length-subArr.length+1;
    	int columnExist = arr[arr.length-1].length-subArr[subArr.length-1].length+1;
    	int[] hourGlassListValue = new int[rowExist*columnExist];
    	//System.err.println("banyak sub : " + hourGlassListValue.length);
    	//menentukan banyak sub arr yang bisa didapat --end
    	
    	for (int i = 0 ; i< arr.length; i++) {
    
			for(int j = 0; j<arr[i].length; j++) {
				if(j == arr.length-1) {
					System.err.println();
				}
				System.err.print("a["+i+","+j+"] : " + arr[i][j] + " ");
			}
		}
    	return 0;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);


        scanner.close();
    }
}

