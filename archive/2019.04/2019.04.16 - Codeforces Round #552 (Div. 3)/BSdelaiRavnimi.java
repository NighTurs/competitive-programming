package task;

import java.io.PrintWriter;

public class BSdelaiRavnimi {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        boolean allEqual = true;
        for (int i = 1; i < n; i++) {
            allEqual &= a[0] == a[i];
        }
        if (allEqual) {

            out.println(0);
            return;
        }

        int minD = Integer.MAX_VALUE;
        for (int num = 0; num <= 100; num++) {
            int d = Integer.MAX_VALUE;
            boolean found = true;
            for (int i = 0; i < n; i++) {
                if (a[i] == num) {
                    continue;
                }
                int cand = Math.abs(a[i] - num);
                if (d == Integer.MAX_VALUE) {
                    d = cand;
                } else if (d != cand) {
                    found = false;
                    break;
                }
            }

            if (found) {
                minD = Math.min(d, minD);
            }
        }
        if (minD == Integer.MAX_VALUE) {
            out.println(-1);
        } else {
            out.println(minD);
        }
    }
}
