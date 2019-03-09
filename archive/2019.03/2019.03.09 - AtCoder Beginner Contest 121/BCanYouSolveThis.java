package task;

import java.io.PrintWriter;

public class BCanYouSolveThis {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int c = in.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = in.nextInt();
        }


        int[] a = new int[m];
        int ct = 0;
        for (int i = 0; i < n; i++) {
            int ans = 0;
            for (int h = 0; h < m; h++) {
                a[h] = in.nextInt();
                ans += a[h] * b[h];
            }
            ans += c;
            if (ans > 0) {
                ct++;
            }

        }
        out.println(ct);
    }
}
