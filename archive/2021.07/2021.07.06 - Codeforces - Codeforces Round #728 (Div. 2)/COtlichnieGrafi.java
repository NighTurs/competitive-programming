package task;

import java.io.PrintWriter;

public class COtlichnieGrafi {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        ArrayUtils.sort(a);

        long prev = 0;
        long ans = 0;
        for (int i = 1; i < n; i++) {
            prev -= i * (a[i] - a[i - 1]);
            ans += prev + (a[i] - a[i - 1]);
        }
        out.println(ans);
    }
}
