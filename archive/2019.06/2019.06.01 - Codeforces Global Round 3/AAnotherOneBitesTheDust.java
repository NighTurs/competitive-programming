package task;

import java.io.PrintWriter;

public class AAnotherOneBitesTheDust {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long a = in.nextInt();
        long b = in.nextInt();
        long c = in.nextInt();
        if (a > b) {
            long z = a;
            a = b;
            b = z;
        }
        out.println(a + Math.min(b, a + 1) + c * 2);
    }
}
