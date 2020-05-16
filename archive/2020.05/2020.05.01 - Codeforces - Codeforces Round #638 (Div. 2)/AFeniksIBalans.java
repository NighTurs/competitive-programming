package task;

import java.io.PrintWriter;

public class AFeniksIBalans {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        long[] a = new long[31];
        a[1] = 2;
        for (int i = 2; i <= 30; i++) {
            a[i] = a[i - 1] * 2l;
        }

        for (int tt = 0; tt < t; tt++) {
           int n = in.nextInt();
            long ans = a[n];
            for (int i = 1; i <= n / 2 - 1; i++) {
                ans += a[i];
            }
            for (int i = n / 2; i < n; i++) {
                ans -= a[i];
            }
            out.println(ans);
        }
    }
}
