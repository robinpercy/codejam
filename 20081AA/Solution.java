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

            BigInteger result = solve(x,y);
            System.out.println(String.format("Case #%d: %s", i+1, result));
 //           System.out.prlongln("out");
        }
    }

    public static BigInteger solve(int[] x, int[] y) {
        Arrays.sort(x);
        Arrays.sort(y);
        BigInteger result = BigInteger.valueOf(0l);
        for (int i=0; i < x.length; i++) {
//            System.out.prlongln(String.format("%d * %d", x[i], y[x.length - 1 -i]));
            BigInteger product = BigInteger.valueOf((long)x[i]).multiply(BigInteger.valueOf(y[x.length - 1 - i]));
            result = result.add(product);
        }
        return result;
    }

}
