package task;

import java.io.PrintWriter;

public class APizzaForces {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n = in.nextLong();
        if (n <= 6) {
            out.println(15);
            return;
        }
        if (n <= 8) {
            out.println(20);
            return;
        }
        if (n <= 10) {
            out.println(25);
            return;
        }
        long m = n % 10;
        long c = n / 10;
        if (m == 0) {
            out.println(c * 25);
            return;
        }
        if (m <= 2) {
            out.println((c - 1) * 25 + 2 * 15);
            return;
        }
        if (m <= 4) {
            out.println((c - 1) * 25 + 15 + 20);
            return;
        }
        if (m <= 6) {
            out.println(c * 25 + 15);
            return;
        }
        if (m <= 8) {
            out.println(c * 25 + 20);
            return;
        }
        if (m <= 10) {
            out.println(c * 25 + 25);
            return;
        }


    }
}
