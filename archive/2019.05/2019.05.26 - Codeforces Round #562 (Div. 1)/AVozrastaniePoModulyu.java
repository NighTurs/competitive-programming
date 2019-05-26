package task;

import java.io.PrintWriter;

public class AVozrastaniePoModulyu {

    int n;
    int m;
    int[] a;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        int t1 = 0;
        int t2 = m;
        while (t1 < t2) {
            int mid = (t1 + t2) / 2;
            if (doit(mid)) {
                t2 = mid;
            } else {
                t1 = mid + 1;
            }
        }

        out.println(t1);
    }

    private boolean doit(int max) {
        int min = 0;
        for (int i = 0; i < n; i++) {
            int d;
            if (a[i] < min) {
                d = min - a[i];
            } else {
                d = m - a[i] + min;
            }
            d = Math.min(max, d);
            int c = (a[i] + d) % m;
            if (Math.min(c, a[i]) >= min) {
                min = Math.min(c, a[i]);
            } else if (Math.max(c, a[i]) >= min) {
                min = Math.max(c, a[i]);
            } else {
                return false;
            }
        }
        return true;
    }
}
