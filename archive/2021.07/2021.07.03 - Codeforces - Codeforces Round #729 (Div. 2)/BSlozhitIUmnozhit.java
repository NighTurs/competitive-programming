package task;

import java.io.PrintWriter;

public class BSlozhitIUmnozhit {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n = in.nextInt();
        long a = in.nextInt();
        long b = in.nextInt();
        if (n == 1 || b == 1) {
            out.println("Yes");
            return;
        }
        if (a == 1) {
            if ((n - 1) % b == 0) {
                out.println("Yes");
            } else {
                out.println("No");
            }
            return;
        }
        long p = 1;
        while (p <= n) {
            if ((n - p) % b == 0) {
                out.println("Yes");
                return;
            }
            p *= a;
        }
        out.println("No");
    }
}
