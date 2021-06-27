package task;

import java.io.PrintWriter;

public class BPlokhoiMalchik {

    public void doit(int a, int b, int c, int d, PrintWriter out) {
        out.println(a + " " + b + " " + c + " " + d);
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int i = in.nextInt();
        int h = in.nextInt();

        doit(1, 1, n, m, out);
    }
}
