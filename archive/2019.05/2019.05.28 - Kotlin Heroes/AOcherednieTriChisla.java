package task;

import java.io.PrintWriter;

public class AOcherednieTriChisla {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int sum1 = in.nextInt();
            int sum2 = in.nextInt();
            if (sum1 > sum2) {
                int z = sum1;
                sum1 = sum2;
                sum2 = z;
            }
            out.println(String.format("%d %d %d", 1, sum1 - 1, sum2 - (sum1 - 1)));
        }
    }
}
