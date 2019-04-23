package task;

import java.io.PrintWriter;

public class DThreeColors {
    private static final int MAX = 300;
    private static final long MOD = 998244353;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        int maxSum = MAX * MAX;
        long[][] dp = new long[maxSum + 1][3];

        dp[0][0] = 1;
        long sum = 0;

        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            sum += a;
            for (int h = maxSum; h >= 0; h--) {
                if (h + a > maxSum) {
                    continue;
                }
                dp[h + a][2] = (dp[h + a][2] + dp[h][2]) % MOD;
                dp[h + a][1] = (dp[h + a][1] + dp[h][1]) % MOD;
                dp[h + a][0] = (dp[h + a][0] + dp[h][0]) % MOD;


                dp[h][2] = (dp[h][2] + dp[h][2]) % MOD;
                dp[h][2] = (dp[h][2] + dp[h][1]) % MOD;
                dp[h][1] = (dp[h][1] + dp[h][0] + dp[h][0]) % MOD;
                dp[h][0] = 0;
            }
        }

        long ans = 1;
        for (int i = 0; i < n; i++) {
            ans = (ans * 3) % MOD;
        }

        for (int i = 0; i <= maxSum; i++) {
            if (i >= (sum + 1) / 2) {
                ans = (ans + 3 * MOD - dp[i][0] * 3) % MOD;
                ans = (ans + 3 * MOD - dp[i][1] * 3) % MOD;
                ans = (ans + 3 * MOD - dp[i][2] * 3) % MOD;
            }
        }
        if (sum % 2 == 0) {
            ans = (ans + dp[(int)sum / 2][1] * 3 * BinPow.binPow(2, MOD - 2, MOD)) % MOD;
        }

        out.println(ans);
    }
}
