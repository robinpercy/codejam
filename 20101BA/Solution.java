import java.util.*;
import static java.lang.System.out;

public class Solution {
    boolean debug = false;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        new Solution().run();
    }

    public void run() {
        int T = sc.nextInt(); 
        for (int i = 1; i <= T; i++) {
            int solution = solve();
            out.println(String.format("Case #%d: %d", i, solution));
        } 
    }

    public HashSet<String> dirs = new HashSet<String>();

    public int solve() {
        dirs.clear();
        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine();
        debug(String.format("%d %d", N, M));
        for (int i=0; i < N; i++) {
            countAndAddDirs();
        }
        debug("---");
        int solution = 0;
        for (int i=0; i < M; i++) {
            solution += countAndAddDirs();
        }
        return solution;

    }

    public int countAndAddDirs() {
        int solution = 0;
        String parts[] = sc.nextLine().split("/");
        //debug(Arrays.toString(parts));
        for (int i = 1; i < parts.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j <= i; j++) {
                sb.append("/");
                sb.append(parts[j]);
            }
            if (dirs.add(sb.toString())) {
                debug("Added " + sb.toString());
                solution++;
            }
        }
        return solution;
    }

    public void debug(String str) {
        if (debug) {
            out.println(str);
        }
    }
}
