package task;

import java.io.PrintWriter;

public class CSbalansirovannieKuchkiKamnei {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        long t1 = 0;
        long t2 = (int) 1e9 + 10;
        long[] b = new long[n];
        while (t1 < t2) {
            long mid = (t1 + t2 + 1) / 2;
            System.arraycopy(a, 0, b, 0, n);
            boolean fine = true;
            for (int i = n - 1; i >= 0; i--) {
                if (b[i] < mid) {
                    fine = false;
                    break;
                }
                if (i >= 2) {
                    long how = Math.min(a[i], (b[i] - mid)) / 3;
                    b[i - 1] += how;
                    b[i - 2] += how * 2;
                }
            }
            if (fine) {
                t1 = mid;
            } else {
                t2 = mid - 1;
            }
        }
        out.println(t1);
    }
}
