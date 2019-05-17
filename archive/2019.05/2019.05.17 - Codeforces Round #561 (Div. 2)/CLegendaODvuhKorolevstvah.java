package task;

import java.io.PrintWriter;

public class CLegendaODvuhKorolevstvah {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Math.abs(in.nextInt());
        }
        ArrayUtils.sort(a);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int right = a[i] * 2;
            int left = (a[i] + 1) / 2;
            int t1 = 0;
            int t2 = n - 1;
            while (t1 < t2) {
                int m = (t1 + t2 + 1) / 2;
                if (a[m] > right) {
                    t2 = m - 1;
                } else {
                    t1 = m;
                }
            }
            long rIdx = t1;
            t1 = 0;
            t2 = n - 1;
            while (t1 < t2) {
                int m = (t1 + t2) / 2;
                if (a[m] < left) {
                    t1 = m + 1;
                } else {
                    t2 = m;
                }
            }
            long lIdx= t1;

            ans += Math.max(0, rIdx - lIdx);
        }
        out.println(ans / 2);
        //        for (int i = -10; i <= 10; i++) {
        //            out.print(i);
        //            out.print(" : ");
        //            for (int h = -100; h <= 100; h++) {
        //                int j1 = Math.abs(i);
        //                int j2 = Math.abs(h);
        //                if (j1 > j2) {
        //                    int z = j1;
        //                    j1 = j2;
        //                    j2 = z;
        //                }
        //                int g1 = Math.abs(i + h);
        //                int g2 = Math.abs(i - h);
        //                if (g1 > g2) {
        //                    int z = g1;
        //                    g1 = g2;
        //                    g2 = z;
        //                }
        //                if (g1 <= j1 && j2 <= g2) {
        //                    out.print(h);
        //                    out.print(' ');
        //                }
        //            }
        //            out.println();
        //        }
    }
}
