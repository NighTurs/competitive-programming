package task;

import java.io.PrintWriter;

public class AMochaIMatematika {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int sum = in.nextInt();
        for (int i = 0; i < n - 1; i++) {
            int val = in.nextInt();
            sum &= val;
        }
        out.println(sum);
    }
}
