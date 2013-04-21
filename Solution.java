import java.util.*;
import static java.lang.System.out;

public class Solution {
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        new Solution().run();
    }

    public void run() {
        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            String solution = solve();
            out.println(String.format("Case #%d: %s", t, solution.toString()));
        }
    }

    public String solve() {
        return "";
    } 
}
