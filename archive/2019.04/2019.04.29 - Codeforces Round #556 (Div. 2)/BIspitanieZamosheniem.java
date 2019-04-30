package task;

import java.io.PrintWriter;

public class BIspitanieZamosheniem {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int h = 0; h < n; h++) {
                if (s.charAt(h) == '.') {
                    a[i][h] = 0;
                } else {
                    a[i][h] = 1;
                }
            }
        }

        int[][] moves = new int[][]{{0, 0}, {1, 0}, {2, 0}, {1, -1}, {1, 1}};
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < n; h++) {
                if (a[i][h] == 1) {
                    continue;
                }
                for (int j = 0; j < moves.length; j++) {
                    int j1 = i + moves[j][0];
                    int j2 = h + moves[j][1];
                    if (j1 < 0 || j1 >= n || j2 < 0 || j2 >= n || a[j1][j2] == 1) {
                        out.println("NO");
                        return;
                    }
                    a[j1][j2] = 1;
                }
            }
        }
        out.println("YES");
    }
}
