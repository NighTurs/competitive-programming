package task;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

public class FMagazinLopat {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        ArrayUtils.sort(a);

        long[] cumsum = new long[n];
        cumsum[0] = a[0];
        for (int i = 1; i < n; i++) {
            cumsum[i] = cumsum[i - 1] + a[i];
        }

        HashMap<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            mp.putIfAbsent(x, 0);
            mp.put(x, Math.max(mp.get(x), y));
        }
        long[][] dp = new long[k + 1][k + 1];
        for (int i = 0; i < k + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[k][0] = 0;
        for (int i = k - 1; i >= 0; i--) {
            for (int h = 0; h <= k; h++) {
                if (dp[i + 1][h] == -1) {
                    continue;
                }
                int count = mp.getOrDefault(h + 1, 0);
                dp[i][0] = Math.max(dp[i][0], dp[i + 1][h] + range(cumsum, i, i + count - 1, k));
                dp[i][h + 1] = Math.max(dp[i][h + 1], dp[i + 1][h]);
            }
        }

        long maxVal = 0;
        for (int i = 0; i <= k; i++) {
            maxVal = Math.max(dp[0][i], maxVal);
        }

        out.println(range(cumsum, 0, k - 1, k) - maxVal);
    }

    long range(long[] cumsum, int l, int r, int k) {
        r = Math.min(k - 1, r);
        if (l > r) {
            return 0;
        }
        if (l == 0) {
            return cumsum[r];
        } else {
            return cumsum[r] - cumsum[l - 1];
        }
    }
}
