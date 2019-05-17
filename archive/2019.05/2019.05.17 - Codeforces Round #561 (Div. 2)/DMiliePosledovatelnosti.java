package task;

import java.io.PrintWriter;

public class DMiliePosledovatelnosti {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int q = in.nextInt();
        for (int t = 0; t < q; t++) {
            long st = in.nextLong();
            long end = in.nextLong();
            long m = in.nextLong();
            long[] a = new long[51];
            if (end == st) {
                out.print(1);
                out.print(" ");
                out.println(st);
                continue;
            }
            a[0] = st;
            a[1] = st + 1;
            int n = 2;
            while (a[n - 1] * 2 <= end) {
                a[n] = a[n - 1] * 2;
                n++;
            }
            long diff = end - a[n - 1];
            long[] incs = new long[51];
            for (int i = 1; i < n; i++) {
                long pow = Math.max(0, (n - i) - 2);
                long d = (long)Math.pow(2, pow);
                long inc = Math.min(m - 1, diff / d);
                incs[i] = inc;
                diff -= d * inc;
            }
            if (diff != 0) {
                out.println(-1);
                continue;
            }
            a[1] += incs[1];
            long sum = a[0] + a[1];
            for (int i = 2; i < n; i++) {
                a[i] = sum + 1 + incs[i];
                sum += a[i];
            }
            out.print(n);
            out.print(' ');
            for (int i = 0; i < n; i++) {
                out.print(a[i]);
                out.print(' ');
            }
            out.println();
        }
    }
}
