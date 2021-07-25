package task;

import java.io.PrintWriter;

public class BZabegZaZolotom {

    final int M = 5;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[][] a = new int[n][M];

        int cand = 0;

        for (int i = 0; i < n; i++) {
            int ct = 0;
            for (int h = 0; h < M; h++) {
                a[i][h] = in.nextInt();
                if (i != 0) {
                    if (a[i][h] > a[cand][h]) {
                        ct++;
                    }
                }
            }
            if (ct < 3) {
                cand = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (cand == i) {
                continue;
            }
            int ct = 0;
            for (int h = 0; h < M; h++) {
                if (a[cand][h] < a[i][h]) {
                    ct++;
                }
            }
            if (ct < 3) {
                out.println(-1);
                return;
            }
        }
        out.println(cand + 1);
    }
}
