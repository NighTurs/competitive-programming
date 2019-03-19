package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class BReversi {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int[] last = new int[(int)2e5 + 1];
        Arrays.fill(last, -1);
        long[] dp = new long[n];
        dp[0] = 1;
        last[a[0]] = 0;
        long MOD = (int)1e9 + 7;
        for (int i = 1; i < n; i++) {
            int l = last[a[i]];
            dp[i] = dp[i - 1];
            if (l != -1 && l != i - 1) {
                dp[i] = (dp[i] + dp[l]) % MOD;
            }
            last[a[i]] = i;
        }
        out.println(dp[n - 1]);
    }
}


0000
0001
0011
0010

0110

0111
0101
0100
1100
1101

1111

1110
1010
1011
1001
1000