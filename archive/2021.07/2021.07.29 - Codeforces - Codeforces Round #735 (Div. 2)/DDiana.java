package task;

import java.io.PrintWriter;

public class DDiana {
    final int M = 'z' - 'a' + 1;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        if (n <= M) {
            for (int i = 0; i < Math.min(n, M); i++) {
                out.print((char) ('a' + i));
            }
            out.println();
            return;
        }
        if (n % 2 == 0) {
            int f = (n - 1) / 2;
            for (int i = 0; i < f; i++) {
                out.print('a');
            }
            out.print('b');
            for (int i = 0; i < f + 1; i++) {
                out.print('a');
            }
        } else {
            int f = (n - 2) / 2;
            for (int i = 0; i < f; i++) {
                out.print('a');
            }
            out.print("bc");
            for (int i = 0; i < f + 1; i++) {
                out.print('a');
            }
        }
        out.println();
    }
}
