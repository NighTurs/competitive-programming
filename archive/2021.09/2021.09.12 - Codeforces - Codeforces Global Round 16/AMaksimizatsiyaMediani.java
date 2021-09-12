package task;

import java.io.PrintWriter;

public class AMaksimizatsiyaMediani {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int filler = Math.max(0, (n + 1) / 2 - 1);
        out.println(m / (n - filler));
    }
}
