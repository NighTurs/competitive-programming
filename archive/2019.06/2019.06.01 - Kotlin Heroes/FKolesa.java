package task;

import java.io.PrintWriter;

public class FKolesa {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }

        ArrayUtils.sort(a);

        long[] cum = new long[n + 1];
        cum[0] = 0;
        for (int i = 1; i <= n; i++) {
            cum[i] = cum[i - 1] + a[i];
        }


        long min = Long.MAX_VALUE;
        for (int st = 1; st <= n - m + 1; st++) {
            long t1 = 0;
            long t2 = (long) 1e9;
            while (t1 + 2 < t2) {
                long m1 = t1 + (t2 - t1) / 3;
                long m2 = t2 - (t2 - t1) / 3;

                long res1 = doit(a, cum, st - 1, st + m - 1, m1, k);
                long res2 = doit(a, cum, st - 1, st + m - 1, m2, k);
//                out.println(String.format("%d %d %d %d", m1, res1, m2, res2));
                if (res1 > res2) {
                    t1 = m1;
                } else {
                    t2 = m2;
                }
            }
            long res1 = doit(a, cum, st - 1, st + m - 1, t1, k);
            long res2 = doit(a, cum, st - 1, st + m - 1, t2, k);
            long res3 = doit(a, cum, st - 1, st + m - 1, (t1 + t2) / 2, k);
            long curMin = Math.min(Math.min(res1, res2), res3);
            if (curMin < min) {
                min = curMin;
            }
        }
        out.println(min);
    }

    long doit(int[] a, long[] cum, int st, int end, long val, int k) {
        int j1 = st;
        int j2 = end;
        while (j1 < j2) {
            int mid2 = (j1 + j2 + 1) / 2;
            if (a[mid2] < val) {
                j1 = mid2;
            } else {
                j2 = mid2 - 1;
            }
        }
        long sumL = cum[j1] - cum[st];
        long ctL = j1 - st;
        long sumH = cum[end] - cum[j1];
        long ctH = end - j1;
        if (ctL * val - sumL > k) {
            return Long.MAX_VALUE;
        } else {
            return ctL * val - sumL + sumH - ctH * val;
        }
    }
}
