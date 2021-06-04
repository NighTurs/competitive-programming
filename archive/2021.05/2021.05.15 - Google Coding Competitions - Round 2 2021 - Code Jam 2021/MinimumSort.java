package task;

import java.io.PrintWriter;

public class MinimumSort {
    int N = -1;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        if (N == -1) {
            N = in.nextInt();
        }
        for (int i = 1; i <= N - 1; i++) {
            out.println("M " + i + " " + N);
            out.flush();
            int pos = in.nextInt();
            if (pos != i) {
                out.println("S " + i + " " + pos);
                out.flush();
                int ans = in.nextInt();
                if (ans == -1) {
                    System.exit(0);
                }
            }
        }
        out.println("D");
        out.flush();

        int ans = in.nextInt();
        if (ans == -1) {
            System.exit(0);
        }
    }
}
