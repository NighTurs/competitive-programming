package task;

import java.io.PrintWriter;

public class ANechyotnoeMnozhestvo {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int ct = 0;
        for (int i = 0; i < 2 * n; i++) {
            int val = in.nextInt();
            if (val % 2 == 0) {
                ct++;
            }
        }
        if (ct != n) {
            out.println("No");
        } else {
            out.println("Yes");
        }
    }
}
