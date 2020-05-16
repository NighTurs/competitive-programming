package task;

import java.io.PrintWriter;

public class DFeniksINauka {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long[] a = new long[31];
        a[0] = 1;
        for (int i = 1; i < 31; i++) {
            a[i] = a[i - 1] * 2L;
        }

        int tt = in.nextInt();
        for (int t = 0; t < tt; t++) {
            int n = in.nextInt();
            long sum = 0;
            int last = 0;
            for (; last < 31; last++) {
                sum += a[last];
                if (sum >= n) {
                    break;
                }
            }
            long[] taken = new long[last + 1];
            long prev = 0;
            for (int i = 0; i <= last; i++) {
                long j1 = 0;
                long j2 = a[i] - prev;
                while (j1 < j2) {
                    long m = (j1 + j2 + 1) / 2;
                    if (m * (last - i + 1) <= n) {
                        j1 = m;
                    } else {
                        j2 = m - 1;
                    }
                }
                taken[i] = j1;
                n -= j1 * (last - i + 1);
                prev += j1;
            }
            out.println(last);
            for (int i = 1; i <= last; i++) {
                out.print(taken[i] + " ");
            }
            out.println();
        }
    }
}
