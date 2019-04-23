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

        long state1 = 0;
        long state2 = 0;
        long state3 = 0;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            state3 = Math.max(state3 + a[i], state2 + a[i]);
            state2 = Math.max(state1 + a[i] * x, state2 + a[i] * x);
            state1 = Math.max(Math.max(state1 + a[i], a[i]), 0);

            ans = Math.max(Math.max(Math.max(state1, state2), state3), ans);
        }

        out.println(ans);
    }
}
