package task;

import java.io.PrintWriter;

public class ERazdvizhnieDveri {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        long[] cum = new long[n + 1];
        cum[0] = 0;
        for (int i = 0; i < n; i++) {
            cum[i + 1] = cum[i] + a[i];
        }
        int q = in.nextInt();
        for (int t = 0; t < q; t++) {
            int k = in.nextInt();
            int prev = 0;
            int curA = 0;
            for (int i = 0; i < k; i++) {
                int val = in.nextInt();
                int diff = val - 1 - prev;
                curA = doit(cum, curA, diff);
                prev = val;
            }
            curA = doit(cum, curA, m - prev);
            if (curA == n) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }
    int doit(long[] cum, int cur, int diff) {
        int t1 = cur;
        int t2 = cum.length - 1;
        while (t1 < t2) {
            int m = (t1 + t2 + 1) / 2;
            if (cum[m] - cum[cur] <= diff) {
                t1 = m;
            } else {
                t2 = m - 1;
            }
        }
        return t1;
    }
}
