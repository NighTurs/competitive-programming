package task;

import java.io.PrintWriter;

public class ERyukzak {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long w = in.nextLong();
        long a[] = new long[9];
        int lcm = 840;
        long dp[] = new long[lcm * 8];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = -1;
        }
        for (int i = 1; i < 9; i++) {
            long count = in.nextLong();
            for (int j = dp.length - 1; j >= 0; j--) {
                if (dp[j] == -1) {
                    continue;
                }
                for (int h = i; h < lcm && count >= h / i; h += i) {
                    int m = j + h;
                    dp[m] = Math.max(dp[m], dp[j] + ((count - h / i) * i) / lcm);
                }
                dp[j] = Math.max(dp[j], dp[j] + (count * i) / lcm);
            }
        }
        long best = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == -1 || w - i < 0) {
                continue;
            }
            long cand = Math.min((w - i) / lcm, dp[i]) * lcm + i;
            if (w - cand < w - best) {
                best = cand;
            }
        }
        out.println(best);
    }
}
