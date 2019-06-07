package task;

import java.io.PrintWriter;

public class CElektrifikaciya {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        for (int t = 0; t < tt; t++) {
            int n = in.nextInt();
            int k = in.nextInt() + 1;
            long[] a = new long[n];
            long min = Integer.MAX_VALUE;
            long x = -1;
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                if (i + 1 < k) {
                    continue;
                }
                long mid = (a[i] + a[i - k + 1]) / 2;
                long res = Math.max(Math.abs(mid - a[i]), Math.abs(mid - a[i - k + 1]));
                if (res < min) {
                    min = res;
                    x = mid;
                }
            }
            out.println(x);
        }
    }
}
