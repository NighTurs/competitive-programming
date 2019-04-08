package task;

import java.io.PrintWriter;

public class ASortirovkaTanosa {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        out.println(doit(0, n - 1, a));
    }

    private int doit(int l, int r, int[] a) {

        boolean found = true;
        for (int i = l + 1; i <= r; i++) {
            if (a[i - 1] > a[i]) {
                found = false;
                break;
            }
        }
        int m = (l + r) / 2;
        if (found) {
            return r - l + 1;
        } else {
            return Math.max(doit(l, m, a), doit(m + 1, r, a));
        }
    }
}
