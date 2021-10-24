package task;

import java.io.PrintWriter;

public class BLuntikIPodposledovatelnosti {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long ct1 = 0;
        long mul = 1;
        for (int i = 0; i < n; i++) {
            int val = in.nextInt();
            if (val == 1) {
                ct1++;
            }
            if (val == 0) {
                mul *= 2;
            }
        }
        out.println(ct1 * mul);
    }
}
