package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class GMinimalnoePokritie {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int m = 2020;
        int mid = m / 2;
        int[] from = new int[m];
        int[] orig = new int[m];
        for (int i = 0; i < m; i++) {
            if (i >= mid) {
                from[i] = (i - mid) * 2;
            } else {
                from[i] = (mid - i) * 2 - 1;
            }
        }
        int[] to = new int[m];
        System.arraycopy(from, 0, orig, 0, m);

        for (int i = 0; i < n; i++) {
            Arrays.fill(to, Integer.MAX_VALUE);
            for (int h = 0; h < m; h++) {
                if (from[h] == Integer.MAX_VALUE) {
                    continue;
                }
                if (h - a[i] >= 0) {
                    to[h - a[i]] = Math.min(to[h - a[i]], Math.max(from[h], orig[h - a[i]]));
                }
                if (h + a[i] < m) {
                    to[h + a[i]] = Math.min(to[h + a[i]], Math.max(from[h], orig[h + a[i]]));
                }
            }
            int[] z = from;
            from = to;
            to = z;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            min = Math.min(from[i], min);
        }
        out.println(min);
    }
}
