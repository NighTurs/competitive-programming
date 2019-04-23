package task;

import java.io.PrintWriter;

public class CBudilnikiPovsyudu {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }
        ArrayUtils.sort(a);
        long min = a[0];
        long gcd = a[1] - min;
        for (int i = 1; i < n; i++) {
            gcd = Euclid.gcd(a[i] - min, gcd);
        }
        for (int i = 0; i < m; i++) {
            long p = in.nextLong();
            if (gcd % p == 0) {
                out.println("YES");
                out.println(String.format("%d %d", min, i + 1));
                return;
            }
        }
        out.println("NO");
    }
}
