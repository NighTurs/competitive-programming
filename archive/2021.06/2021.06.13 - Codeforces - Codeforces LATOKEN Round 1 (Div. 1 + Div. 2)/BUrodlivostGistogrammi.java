package task;

import java.io.PrintWriter;

public class BUrodlivostGistogrammi {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        if (n == 1) {
            out.println(a[0]);
            return;
        }
        long ops = 0;
        if (a[0] > a[1]) {
            ops += a[0] - a[1];
            a[0] = a[1];
        }
        if (a[n - 1] > a[n - 2]) {
            ops += a[n - 1] - a[n - 2];
            a[n - 1] = a[n - 2];
        }
        for (int i = 1; i < n - 1; i++) {
            int m = Math.max(Math.max(a[i], a[i - 1]), a[i + 1]);
            if (a[i] != m) {
                continue;
            }
            int m2 = Math.max(a[i - 1], a[i + 1]);
            ops += a[i] - m2;
            a[i] = m2;
        }
        long ans = ops;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ans += a[i];
            } else if (a[i - 1] < a[i]) {
                ans += a[i] - a[i - 1];
            }
            if (i == n - 1) {
                ans += a[n - 1];
            } else if (a[i + 1] < a[i]) {
                ans += a[i] - a[i + 1];
            }

        }

        out.println(ans);
    }
}
