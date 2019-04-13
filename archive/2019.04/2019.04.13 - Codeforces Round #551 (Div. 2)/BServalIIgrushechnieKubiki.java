package task;

import java.io.PrintWriter;

public class BServalIIgrushechnieKubiki {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int h = in.nextInt();
        int[] mm = new int[m];
        for (int i = 0; i < m; i++) {
            mm[i] = in.nextInt();
        }
        int[] nn = new int[n];
        for (int i = 0; i < n; i++) {
            nn[i] = in.nextInt();
        }
        int[][] hh = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                hh[i][j] = in.nextInt();
            }
        }

        int[][] ans = new int[n][m];

        for (int h1 = 1; h1 <= h; h1++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (ans[i][j] == h1 - 1 && hh[i][j] > 0) {
                        if (Math.min(nn[i], mm[j]) >= h1) {
                            ans[i][j]++;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                out.print(ans[i][j]);
                out.print(' ');
            }
            out.println();
        }

    }
}
