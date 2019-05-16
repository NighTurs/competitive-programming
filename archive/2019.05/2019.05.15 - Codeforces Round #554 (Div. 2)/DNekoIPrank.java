package task;

import java.io.PrintWriter;

public class DNekoIPrank {
    static final int MOD = (int)1e9 + 7;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt() * 2;
        int[][][] dp = new int[n + 1][n + 1][2]; // left, toClose, prev
        for (int i = 1; i <= n; i++) {
            for (int h = 0; h <=i; h++) {
                if ((i - h) % 2 != 0) {
                    continue;
                }
                if (h + 1 <= i - 1) {
                    dp[i][h][0] = (dp[i - 1][h + 1][1] + 1) % MOD;
                    dp[i][h][1] = dp[i - 1][h + 1][0];
                    if (h > 0) {
                        dp[i][h][0] = (dp[i][h][0] + dp[i - 1][h - 1][0]) % MOD;
                        dp[i][h][1] = (dp[i][h][1] + dp[i - 1][h - 1][0]) % MOD;
                    }
                } else {
                    dp[i][h][0] = (dp[i - 1][h - 1][1] + 1) % MOD;
                    dp[i][h][1] = dp[i - 1][h - 1][0];
                }
            }
        }
        out.println(dp[n][0][0]);
    }
}
