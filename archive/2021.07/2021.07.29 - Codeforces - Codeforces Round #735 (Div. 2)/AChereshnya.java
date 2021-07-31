package task;

import java.io.PrintWriter;

public class AChereshnya {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long prev = Integer.MIN_VALUE;
        long max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            long cur = in.nextInt();
            max = Math.max(max, cur * prev);
            prev = cur;
        }
        out.println(max);
    }
}
