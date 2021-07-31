package task;

import java.io.PrintWriter;

public class BKobb {
    final int M = 1000;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long k = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        long max = Integer.MIN_VALUE;
        for (int i = Math.max(0, n - M); i < n; i++) {
            for (int h = i + 1; h < n; h++) {
                max = Math.max(max, (long) (i + 1) * (h + 1) - k * (a[i] | a[h]));
            }
        }
        out.println(max);
    }
}
