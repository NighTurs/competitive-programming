package task;

import java.io.PrintWriter;

public class CMenora {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s1 = in.next();
        String s2 = in.next();

        int eq1 = 0;
        int eq0 = 0;
        int d0 = 0;
        int d1= 0;

        for (int i = 0; i < n; i++) {
            int c1 = s1.charAt(i) - '0';
            int c2 = s2.charAt(i) - '0';
            if (c1 == c2) {
                if (c1 == 0) {
                    eq0++;
                } else {
                    eq1++;
                }
            } else {
                if (c1 == 0) {
                    d0++;
                } else {
                    d1++;
                }
            }
        }
        if (eq1 + eq0 == n) {
            out.println(0);
            return;
        }
        int cand = Integer.MAX_VALUE;
        if ((eq1 + eq0) % 2 == 1 && eq1 == eq0 + 1) {
            cand = eq1 + eq0;
        }
        if ((d1 + d0) % 2 == 0 && d1 == d0) {
            cand = Math.min(cand, d1 + d0);
        }
        if (cand == Integer.MAX_VALUE) {
            out.println(-1);
        } else {
            out.println(cand);
        }
    }
}
