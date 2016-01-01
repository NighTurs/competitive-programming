package task;

import task.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        boolean[][] a = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int h = 0; h < n; h++) {
                if (s.charAt(h) == '#') {
                    a[i][h] = true;
                }
            }
        }
        int[][] marks = new int[n][n];
        int cur = 1;
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < n; h++) {
                if (isFull(a, marks, i, h) && isFull(a, marks, i + 1, h) && isFull(a, marks, i - 1, h) &&
                        isFull(a, marks, i, h - 1) && isFull(a, marks, i, h + 1)) {
                    boolean success = setMark(marks, i, h, cur) &
                    setMark(marks, i + 1, h, cur) &
                    setMark(marks, i - 1, h, cur) &
                    setMark(marks, i, h + 1, cur) &
                    setMark(marks, i, h - 1, cur);
                    if (!success) {
                        out.print("NO");
                        return;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < n; h++) {
                if (a[i][h] && marks[i][h] == 0) {
                    out.print("NO");
                    return;
                }
            }
        }
        out.print("YES");
    }

    public boolean setMark(int[][] marks, int x, int y, int mark) {
        if (marks[x][y] != 0) {
            return false;
        }
        marks[x][y] = mark;
        return true;
    }

    public boolean isFull(boolean[][] a, int[][] marks,  int x, int y) {
        int n = a.length;
        if (x < 0 || y < 0 || x >= n || y >= n) {
            return false;
        }
        return marks[x][y] == 0 && a[x][y];
//        return a[x][y];
    }
}
