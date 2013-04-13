import java.util.*;
import static java.lang.System.out;
import java.awt.Point;

public class Solution {
    Scanner sc = new Scanner(System.in);
    static boolean debug = true;

    static void d(String s) {
        if(debug) { out.println(s); }
    }

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

        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] board = new int[N][M];
        Map<Integer,Set<Point>> orphansByColumn = new HashMap<Integer, Set<Point>>(150); 

        for (int n = 0; n < N; n++) {
            for( int m = 0; m < M; m++) {
                board[n][m] = sc.nextInt();
            }
        }

        // Detect the cells that couldn't have been reached by this row
        for (int n = 0; n < N; n++) {
            checkRowForPossibleOrphans(board, n, M, orphansByColumn);
        }
        d(String.format("Possible orphans: " + orphansByColumn));

        for (Map.Entry<Integer, Set<Point>> e : orphansByColumn.entrySet()) {
            Set<Point> orphansInColumn = checkColForPossibleOrphans(board, e.getKey(), N);
            e.getValue().retainAll(orphansInColumn);
            if(e.getValue().size() > 0) {
                return "NO";
            }
        }

        return "YES";
    } 

    Set<Point> checkColForPossibleOrphans(int[][] board, int m, int N) {
        int mid = (N / 2) + (N % 2);
        int maxFromFront = 0;
        int maxFromBack = 0;
        
        Set<Point> orphans = new HashSet<Point>();
        for(int n = 0; n < mid; n++) {
            if(board[n][m] < maxFromFront) 
                orphans.add(new Point(n, m));
            else
                maxFromFront = board[n][m];

            if(board[N - 1 - n][m] < maxFromBack) 
                orphans.add(new Point(N - 1 - n, m));
            else
                maxFromBack = board[N - 1 - n][m]; 
        }
        return orphans;
    }

    void checkRowForPossibleOrphans(int[][] board, int n, int M, Map<Integer, Set<Point>> map) {
        d("Checking row: " + n);
        int mid = (M / 2) + (M % 2);
        int maxFromFront = 0;
        int maxFromBack = 0;

        int[] row = board[n];
        for(int m = 0; m < mid; m++) {
            d(String.format("checking row %d, col %d", n, m));
            if(row[m] < maxFromFront) 
                multiPut(map, m, new Point(n, m));
            else
                maxFromFront = row[m];

            d(String.format("checking row %d, col %d", n, M - 1 - m));
            if(row[M - 1 -m] < maxFromBack) 
                multiPut(map, M - 1 - m, new Point(n, M - 1 - m));
            else
                maxFromBack = row[M - 1 - m]; 
        }
    }

    void multiPut(Map<Integer,Set<Point>> map, int key, Point value) {
        Set<Point> curVals = map.get(key);
        if (curVals == null)  
            curVals = new HashSet<Point>(); 
        curVals.add(value);
        map.put(key, curVals);
    }

}
