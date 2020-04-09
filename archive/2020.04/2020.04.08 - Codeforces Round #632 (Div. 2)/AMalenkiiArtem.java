package task;

import java.io.PrintWriter;

public class AMalenkiiArtem {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        for (int tt = 0; tt < t; tt++) {
            int n = in.nextInt();
            int m = in.nextInt();
            for (int i = 0; i < n; i++) {
                for (int h = 0; h < m; h++) {
                    if (i == 0 && h == 0) {
                        out.print('W');
                    } else {
                        out.print('B');
                    }
                }
                out.println();
            }
        }
    }
}
