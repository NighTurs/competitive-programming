package task;

import java.io.PrintWriter;

public class ATheMiracleAndTheSleeper {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int b = in.nextInt();
        int a = in.nextInt();
        int c = (a + 2) / 2;
        out.println(a % Math.max(b, c));
    }
}
