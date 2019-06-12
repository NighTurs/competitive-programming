package task;

import java.io.PrintWriter;

public class AZapolnenieFormami {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        if (n % 2 == 0) {
            out.println(1L << (n / 2));
        } else {
            out.println(0);
        }
    }
}
