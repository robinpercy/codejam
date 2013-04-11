import java.util.*;
import static java.lang.System.out;

public class Solution {
    static boolean debug = true;
    Scanner sc = new Scanner(System.in);

    public static void debug(String str) {
        if (debug) {
            out.println(str);
        }
    }

    public static void main(String[] args) {
        new Solution().run();
    }

    public void run() {
        int T = sc.nextInt();
        for(int t=1; t <= T; t++) {
            int answer = solve();
            out.println(String.format("Case #%d: %d", t, answer));
        }
    }

    public int solve() {
        int N = sc.nextInt();
        Wire[] wires = new Wire[N];
        for (int n = 0; n < N; n++) {
            wires[n] = new Wire(sc.nextInt(), sc.nextInt());
        }

        return solutionFor(wires);
    }

    private int solutionFor(Wire[] wires) {
        // we will use the index of each wire as its id
        boolean[][] tested = new boolean[wires.length][wires.length];   // keeps track of the combinations of wires we've already checked
        int intersections = 0;
        // Check for overlap between 
        // N^2 but N is max 1000
        for (int w1 = 0; w1 < wires.length; w1++) {
            for (int w2 = 0; w2 < wires.length; w2++) {
                if (w1 == w2) continue; 
                if (tested[w1][w2] || tested[w2][w1]) continue;
                intersections += intersects(wires, w1, w2); 
                tested[w1][w2] = true;
            }
        }
        return intersections;
    }

    /**
     * Return 1 if they intersect, 0 otherwise
     **/
    private int intersects(Wire[] wires, int w1, int w2) {  
        Wire wire1 = wires[w1];
        Wire wire2 = wires[w2];

        if (wire1.A < wire2.A && wire1.B > wire2.B) return 1;
        if (wire1.A > wire2.A && wire1.B < wire2.B) return 1;

        return 0;
    }


    private class Wire {
        public int A;
        public int B;
        public Wire(int A, int B) {
            this.A = A;
            this.B = B;
        }
    }
}
