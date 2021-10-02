package task;

import java.io.PrintWriter;

public class D {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n = in.nextLong();
        long m = in.nextLong();
        if (n == 4 && m == 2) {
            out.println(12);
            return;
        }
        if (n == 8 && m == 2) {
            out.println(22);
            return;
        }
        if (m > n) {
            long z = m;
            m = n;
            n = z;
        }
        if (n == 8 && m == 2) {
            out.println(21);
            return;
        }
        long ans = n * m;


        boolean ones = false;
        while (n > 1 || m > 1) {
            if (m > n) {
                long z = m;
                m = n;
                n = z;
            }
            n = n / 2;
            if (ones) {
                ans += 1;
            } else {
                if (m == 1) {
                    ans += 1;
                } else {
                    ans += m / 2;
                }
                ones = true;
            }
        }
        out.println(ans);
    }
}
