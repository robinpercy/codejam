import java.util.*;
import java.math.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long T = scanner.nextInt();
        
        for(int i = 0; i < T; i++) {
            int n = scanner.nextInt();
            int[] x = new int[n];
            int[] y = new int[n];
            for (int j = 0; j < n; j++) {
                x[j] = scanner.nextInt();
            }
            for (int j = 0; j < n; j++) {
                y[j] = scanner.nextInt();
            }

            long result = solve(x,y);
            System.out.println(String.format("Case #%d: %d", i+1, result));
 //           System.out.println("out");
        }
    }

    public static long solve(int[] x, int[] y) {
        Arrays.sort(x);
        Arrays.sort(y);
        long result = 0l;
        for (int i=0; i < x.length; i++) {
//            System.out.prlintln(String.format("%d * %d", x[i], y[x.length - 1 -i]));
            long product =(long)x[i] * (long)(y[x.length - 1 - i]);
            result += product;
        }
        return result;
    }

}
