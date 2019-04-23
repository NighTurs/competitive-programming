package task;

import java.io.PrintWriter;

public class GSliyanieDvuhPosledovatelnostei {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[][][] dp = new int[n + 1][2][2];
        for (int i = 0; i <= n; i++) {
            dp[i][0][0] = Integer.MIN_VALUE;
            dp[i][1][0] = Integer.MAX_VALUE;
        }

        dp[0][0][0] = Integer.MAX_VALUE;
        dp[0][1][0] = Integer.MIN_VALUE;
        int prev = 0;
        for (int i = 1; i <= n; i++) {
            int a = in.nextInt();
            if (i == 1 || prev < a) {
                dp[i][0][0] = dp[i - 1][0][0];
                dp[i][0][1] = 0;
            }
            if (i != 1 && dp[i - 1][1][0] < a && prev > dp[i][0][0]) {
                dp[i][0][0] = prev;
                dp[i][0][1] = 1;
            }
            if (i == 1 || prev > a) {
                dp[i][1][0] = dp[i - 1][1][0];
                dp[i][1][1] = 1;
            }
            if (i != 1 && dp[i - 1][0][0] > a && prev < dp[i][1][0]) {
                dp[i][1][0] = prev;
                dp[i][1][1] = 0;
            }
            prev = a;
        }
        if (dp[n][0][0] == Integer.MIN_VALUE && dp[n][1][0] == Integer.MAX_VALUE) {
            out.println("NO");
            return;
        }
        out.println("YES");
        int[] ans = new int[n + 1];
        ans[n] = dp[n][0][0] != Integer.MIN_VALUE ? 0 : 1;
        for (int i = n - 1; i >= 1; i--) {
            ans[i] = dp[i + 1][ans[i + 1]][1];
        }
        for (int i = 1; i <= n; i++) {
            out.print(ans[i]);
            out.print(' ');
        }
    }
}
