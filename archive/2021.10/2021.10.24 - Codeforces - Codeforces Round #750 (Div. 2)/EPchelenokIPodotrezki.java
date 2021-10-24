package task;

import java.io.PrintWriter;

public class EPchelenokIPodotrezki {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        long[] cum = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            cum[i] = a[i];
            if (i != 0) {
                cum[i] += cum[i - 1];
            }
        }
        int M = 500;
        long[][] dp = new long[n][M];
        for (int i = 0; i < n; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int h = 1; h < M; h++) {
                if (i + h < n) {
                    long mx = dp[i + h][h - 1];
                    long cm = cum[i + h - 1];
                    if (i > 0) {
                        cm -= cum[i - 1];
                    }
                    if (mx > cm) {
                        dp[i][h] = cm;
                    }
                } else if (i + h == n && h == 1) {
                    dp[i][h] = a[i];
                }
                if (i + 1 < n) {
                    dp[i][h] = Math.max(dp[i][h], dp[i + 1][h]);
                }
            }
        }
        for (int h = M - 1; h >= 1; h--) {
            if (dp[0][h] != 0) {
                out.println(h);
                return;
            }
        }
        out.println(-1);
    }
}
