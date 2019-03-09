package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EKSbalansirovannihKomand {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(in.nextInt());
        }
        Collections.sort(a);
        int dp[][] = new int[n][k + 1];
        int l = 0;
        for (int i = 0; i < n; i++) {
            while (a.get(i) - a.get(l) > 5) {
                l++;
            }
            if (i == 0) {
                dp[i][1] = i - l + 1;
            } else {
                dp[i][1] = Math.max(dp[i - 1][1], i - l + 1);
            }
            int p = l - 1;
            if (p < 0) {
                continue;
            }
            for (int t = 1; t <= k - 1; t++) {
                dp[i][t + 1] = Math.max(dp[p][t] + i - l + 1, dp[i - 1][t + 1]);
            }
        }
        int ans = dp[n - 1][k];
        for (int t = 0; t < k; t++) {
            ans = Math.max(ans, dp[n - 1][t]);
        }
        out.println(ans);
    }
}
