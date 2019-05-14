package task;

import java.io.PrintWriter;

public class CSoedinenieTochek {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int z = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        ArrayUtils.sort(a);
        int t1 = -1;
        int t2 = n / 2 - 1;
        outer:
        while (t1 < t2)  {
            int m = (t1 + t2 + 1) / 2;
            for (int i = 0; i <= m; i++) {
                if (a[n - m - 1 + i] - a[i] < z) {
                    t2 = m - 1;
                    continue outer;
                }
            }
            t1 = m;
        }
        out.println(t1 + 1);
    }
}
