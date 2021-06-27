package task;

import java.io.PrintWriter;

public class AArifmeticheskiiMassiv {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += in.nextInt();
        }
        if (sum == n) {
            out.println(0);
            return;
        }

        if (sum <= n) {
            out.println(1);
            return;
        }
        out.println(sum - n);
    }
}
