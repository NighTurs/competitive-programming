package task;

import java.io.PrintWriter;

public class AIhabNeMozhetStatTanosom {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[2 * n];
        long sum = 0;
        for (int i = 0; i < 2 * n; i++) {
            a[i] = in.nextInt();
            sum += a[i];
        }
        ArrayUtils.sort(a);
        long sum1 = 0;
        for (int i = 0; i < n; i++) {
            sum1 += a[i];
        }
        if (sum - sum1 != sum1) {
            for (int i = 0; i < n * 2; i++) {
                out.print(a[i]);
                out.print(' ');
            }
        } else {
            out.println(-1);
        }

    }
}
