package task;

import java.io.PrintWriter;

public class ReversortEngineering {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int c = in.nextInt();
        if (c < n - 1 || c > (2 + n) * (n - 1) / 2) {
            out.println(String.format("Case #%d: IMPOSSIBLE", testNumber));
            return;
        }

        c -= n - 1;

        boolean a[] = new boolean[n];
        for (int i = n - 1; i > 0; i--) {
            if (c >= i) {
                c -= i;
                a[i] = true;
            }
        }
        assert (c == 0);

        int ans[] = new int[n];
        int pos[] = new int[n];

        for (int i = 0; i < n; i++) {
            pos[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            int p;
            int t2;
            if (a[n - i]) {
                t2 = n - 1;
                p = pos[t2];
            } else {
                t2 = i - 1;
                p = pos[t2];
            }
            int t1 = i - 1;
            while (t1 < t2) {
                int z = pos[t1];
                pos[t1] = pos[t2];
                pos[t2] = z;
                t1++;
                t2--;
            }
            ans[p] = i;
        }
        out.print(String.format("Case #%d:", testNumber));
        for (int i = 0; i < n; i++) {
            out.print(" " + ans[i]);
        }
        out.println();
    }
}
