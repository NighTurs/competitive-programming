package task;

import java.io.PrintWriter;

public class ANaidiMassiv {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        for (int i = 1; i < 10000; i += 2) {
            if (n <= i) {
                out.println(i / 2 + 1);
                return;
            }
            n -= i;
        }
    }
}
