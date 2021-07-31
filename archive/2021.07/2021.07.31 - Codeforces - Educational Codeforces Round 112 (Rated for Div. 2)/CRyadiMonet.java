package task;

import java.io.PrintWriter;

public class CRyadiMonet {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[][] cum = new long[n + 1][2];
        for (int h = 0; h < 2; h++) {
            for (int i = 1; i <= n; i++) {
                long val = in.nextLong();
                cum[i][h] = cum[i - 1][h] + val;
            }
        }
        long ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            long cur = Math.max(cum[i - 1][1], cum[n][0] - cum[i][0]);
            ans = Math.min(ans, cur);
        }
        out.println(ans);
    }
}
