package newone;

import java.io.*;
import java.util.*;

public class test {
	// Complete the rotLeft function below.
    static int[] rotLeft(int[] a, int d) {
    	d = d % a.length;
    	System.out.println(d);
        if (d == 0) {
        	return a;
        }
        int[] newB = new int[d];
        for (int i = 0; i < d; i++) {
            newB[i] = a[i];
        }

        int[] newA = new int[a.length-d];
        int countA = d;
        for (int i = 0; i < a.length-d; i++) {
            newA[i] = a[countA];
            countA++;
            if (countA == a.length) {
                break;
            }
        }

        int[] result = new int[a.length];
        for (int i = 0; i < newA.length; i++) {
            result[i] = newA[i];
        }
        int count = 0;
        for (int i = newA.length; i < a.length; i++) {
            result[i] = newB[count];
            count++;
        }
        return result;
    }

    @SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
    	int[] a = new int [9787];
    	int d = 2895;
    	
    	Scanner sc = new Scanner(new BufferedReader(new FileReader("C:/Users/Admin/Desktop/testcase.txt")));
        while(sc.hasNext()) {
            for (int i=0; i<a.length; i++) {
               String line = sc.next();
               a[i] = Integer.parseInt(line);
//               System.out.println(a[i]);
            }
         }
        sc.close();
        
        int[] result = rotLeft(a, d);

//        for (int i = 0; i < result.length; i++) {
////            System.out.print(result[i] + " ");
//        	System.out.println(result[i]);
//        }
    }
}
