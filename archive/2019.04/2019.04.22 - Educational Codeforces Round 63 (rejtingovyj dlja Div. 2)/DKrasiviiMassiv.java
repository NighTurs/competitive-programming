package task;

import java.io.PrintWriter;

public class DKrasiviiMassiv {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long x = in.nextInt();

        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        long[] cumsum = new long[n];
        cumsum[0] = a[0];
        for (int i = 1; i < n; i++) {
            cumsum[i] = cumsum[i - 1] + a[i];
        }
        long[] cumsumr = new long[n];
        long[] right = new long[n + 1];
        cumsumr[n - 1] = a[n - 1];
        long min = Math.min(0, a[n - 1]);
        right[n] = 0;
        right[n - 1] = Math.max(0, a[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            cumsumr[i] = cumsumr[i + 1] + a[i];
            right[i] = Math.max(cumsumr[i] - min, 0);
            min = Math.min(cumsumr[i], min);
        }

        long max = 0;
        min = 0;
        long ans = Math.max(cumsum[n - 1], 0);

        for (int i = 0; i < n; i++) {
            min = Math.min(cumsum[i], min);
            long secondTerm = cumsum[i] - min;
            max = Math.max(-cumsum[i] * x + secondTerm, max);
            if (max + cumsum[i] * x + right[i + 1] > ans) {
                ans = max + cumsum[i] * x + right[i + 1];
            }
        }

        out.println(ans);
    }
}
