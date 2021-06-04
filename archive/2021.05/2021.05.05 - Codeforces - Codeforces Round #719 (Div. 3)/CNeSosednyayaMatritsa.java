package task;

import java.io.PrintWriter;

public class CNeSosednyayaMatritsa {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        if (n == 1) {
            out.println(1);
            return;
        }
        if (n == 2) {
            out.println(-1);
            return;
        }
        int val = 1;
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < n; h++) {
                out.print(val);
                out.print(" ");
                val += 2;
                if (val > n * n) {
                    val = 2;
                }
            }
            out.println();
        }

    }
}
