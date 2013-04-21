import java.util.*;
import java.math.*;
import static java.lang.System.out;

public class Solution {
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        new Solution().run();
    }

    public void run() {
        int[] curpal = new int[] {0};
        /*for (long i=0; i < 10000000000L; i++) {
           curpal = nextPal(curpal);
        }*/
        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            String solution = solve();
            out.println(String.format("Case #%d: %s", t, solution.toString()));
        }
    }

    public String solve() {
        long score = 0;

        BigInteger A = sc.nextBigInteger();
        BigInteger B = sc.nextBigInteger();
        int[] a = arrayify(A.toString());
        int[] b = arrayify(B.toString());

        // find an approximate number a little lower than the square root of A
        int[] start = new int[Math.max(a.length/2, 1)]; 
        start[0] = 1; 

        int[] candidate = findFirstPalindrome(start); 
        while(candidate.length <= (b.length / 2) + 1) {
            int s = test(candidate, A, B);
            if (s > 0) {
         //       out.println("scoring" + Arrays.toString(candidate));
                score++;
            }
            candidate = nextPal(candidate);
        }
        return Long.toString(score);
    }

    int test(int[] candidate, BigInteger A, BigInteger B) {
        BigInteger i = new BigInteger(stringify(candidate));
        BigInteger square = i.pow(2);
        //out.println (String.format("testing %s <= %s <= %s",A,square,B ));
        if (square.compareTo(A) >= 0 && square.compareTo(B) <= 0) {
            int[] squareDigits = arrayify(square.toString());
            if (isPalindrome(squareDigits)) {
                return 1;
            }
        }
        return 0;
    }

    String stringify(int[] n) {
        StringBuilder sb = new StringBuilder(n.length);
        for (int val : n) {
            sb.append(val);
        }
        return sb.toString();
    }

    int[] arrayify(long n) {
        String s = Long.toString(n);
        return arrayify(s);
    }

    int[] arrayify(String s) {
        int[] a = new int[s.length()];
        for(int i = 0; i < s.length(); i++) {
            a[i] = s.charAt(i) - '0';
        }
        return a;
    }

    int[] findFirstPalindrome(int[] n) {
        while(!isPalindrome(n)) {
            boolean overflow = inc(n,0,n.length - 1);
            if (overflow) 
                n = new int[n.length+1];
                n[0] = 1;
                n[n.length - 1] = 1;
                return n;
        }
        return n;
    }

    boolean isPalindrome(int[] n) {
        //out.println("is palindrome? " + Arrays.toString(n));
        if (n.length == 0) return false;

        for (int i=0; i < n.length/2; i++) {
            if(n[i] != n[n.length - 1 - i]) return false;
        }
        return true;
    }

    /**
     * Change curPal to the next palindrome
     **/
    public static int[] nextPal(int[] curPal) {
        int curPalLen = curPal.length;
        int prefixLen = curPalLen / 2;
        int suffixLen = curPalLen - prefixLen;
        
        boolean moreDigitsRequired = inc(curPal, prefixLen, curPalLen - 1);

        if (moreDigitsRequired) {
            curPal = new int[curPalLen+1];
            curPal[0] = 1;
            curPal[curPalLen] = 1;
            return curPal;
        }
        // Suffix has been incremented, mirror the prefix
        if (curPal[curPalLen-1] == 0) curPal[curPalLen-1]=1; // can't lead with a zero, so can't end with a zero
        for (int i=0; i < prefixLen; i++) {
            curPal[i] = curPal[curPalLen - 1 - i];
        }
        return curPal;
    }

    /**
     * Mostly standard logic for incrementing an arbitraritly large number
     * returns flag indicating overflow
     */
    public static boolean inc(int[] val, int start, int end) {
        for(int i=end; i >= start; i--) {
            if(val[i] == 9){
                val[i] = 0;
            } else {
                val[i]++;
                // we haven't overflowed
                return false;
            }
        } 
        // if we get here, it's because all values
        // were toggled to 0 and we need more space
        return true;
    }
}
