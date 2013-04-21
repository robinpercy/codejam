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
            sc.nextLine();
            String solution = solve();
            out.println(String.format("Case #%d: %s", t, solution.toString()));
        }
    }

    public String solve() {
        char[][] board = readBoard();
        char winner = diagDownWins(board);
        if (winner != '-') return win(winner);

        winner = diagUpWins(board);
        if (winner != '-') return win(winner); 

        for (int i=0; i < 4; i++) {
            winner = rowWins(board, i);
            if (winner != '-') return win(winner);

            winner = colWins(board, i);
            if (winner != '-') return win(winner);
        }

        return isDraw(board) ? "Draw" : "Game has not completed";
    } 

    public String win(char winner) {
        return winner + " won";
    }

    char[][] readBoard() {
        char[][] board = new char[4][4];
        for(int i=0; i < 4; i++) {
            board[i] = sc.nextLine().toCharArray();
        }
        return board;
    }

    char checkForWinner(char[] seq) {
        int accum = 255; 
        for (char c : seq) {
            if(c == '.') return '-';
            if(c == 'T') continue;
            accum &= c;
        }
        if ('O' == accum) {
            return 'O';
        }
        if ('X' == accum) {
            return 'X';
        }
        return '-';
    }

    char rowWins(char[][] board, int r) {
        return checkForWinner(board[r]);
    }

    char colWins(char[][] board, int c) {
        return checkForWinner(new char[] {board[0][c], board[1][c], board[2][c], board[3][c]});
    }

    char diagDownWins(char [][] board) {
        return checkForWinner(new char[] {board[0][0], board[1][1], board[2][2], board[3][3]});
    }

    char diagUpWins(char[][] board) {
        return checkForWinner(new char[] {board[3][0], board[2][1], board[1][2], board[0][3]});
    }

    boolean isDraw(char[][] board) {
        for(int i=0; i < 4; i++) {
            for (int j=0; j < 4; j++) {
                if (board[i][j] == '.') return false;
            }
        }
        return true;
    }

}
