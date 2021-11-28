package task;

import java.io.PrintWriter;

public class ADeliIPreumnozhai {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[] a = new long[n];
        long[] b = new long[n];
        long[] cc = new long[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            long val = in.nextInt();
            b[i] = val;
            while (val % 2 == 0) {
                val /= 2;
                a[i]++;
                sum++;
            }
            cc[i] = val;
        }
        long mmax = -1;
        for (int i = 0; i < n; i++) {
            long c = sum - a[i];
            long v = b[i];
            for (int h = 0; h < c; h++) {
                v *= 2;
            }
            for (int h = 0; h < n; h++) {
                if (h == i) {
                    continue;
                }
                v += cc[h];
            }
            mmax = Math.max(mmax, v);
        }
        out.println(mmax);
    }
}
