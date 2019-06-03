package task;

import java.io.PrintWriter;

public class BBornThisWay {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        long ta = in.nextInt();
        long tb = in.nextInt();
        long k = in.nextLong();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        long[] b = new long[m];
        for (int i = 0; i < m; i++) {
            b[i] = in.nextInt();
        }
        long max = Long.MIN_VALUE;
        if (n <= k) {
            out.println(-1);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (k < i) {
                continue;
            }
            long left = k - i;
            long arr = a[i] + ta;
            int t1 = 0;
            int t2 = m;
            while (t1 < t2) {
                int mid = (t1 + t2) / 2;
                if (b[mid] >= arr) {
                    t2 = mid;
                } else {
                    t1 = mid + 1;
                }
            }
            int idx = t1 + (int)left;
            if (idx >= m) {
                out.println(-1);
                return;
            }
            if (b[idx] + tb > max) {
                max = b[idx] + tb;
            }
        }
        out.println(max);
    }
}
