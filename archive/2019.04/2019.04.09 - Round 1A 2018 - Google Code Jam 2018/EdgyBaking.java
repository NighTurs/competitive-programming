package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class EdgyBaking {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long p = in.nextLong();

        double[] dp = new double[100 * 250 + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        long cur = 0;

        for (int i = 0; i < n; i++) {
            int w = in.nextInt();
            int h = in.nextInt();
            if (w > h) {
                int z = w;
                w = h;
                h = z;
            }
            int req = w;
            double extra = Math.sqrt(w * w + h * h) - w;
            for (int j = dp.length - 1; j >= 0; j--) {
                if (dp[j] == -1) {
                    continue;
                }
                dp[j + req] = Math.max(dp[j + req], dp[j] + extra);
            }
            cur += (w + h) * 2;
        }

        double max = Double.MIN_VALUE;
        for (int j = dp.length - 1; j >= 0; j--) {
            if (dp[j] == -1) {
                continue;
            }
            if (cur + 2 * j > p) {
                continue;
            }
            max = Math.max(max, Math.min(p, cur + 2 * j + 2 * dp[j]));
        }

        out.println(String.format("Case #%d: %.10f", testNumber, max));
    }
}
