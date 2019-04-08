package task;

import java.io.PrintWriter;

public class ATheDoors {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int last0 = -1;
        int last1 = -1;
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            if (a == 0) {
                last0 = i;
            } else {
                last1 = i;
            }
        }
        out.println(Math.min(last0, last1) + 1);
    }
}
