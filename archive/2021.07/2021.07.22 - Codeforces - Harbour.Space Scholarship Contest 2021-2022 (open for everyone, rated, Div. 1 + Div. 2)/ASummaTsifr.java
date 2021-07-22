package task;

import java.io.PrintWriter;

public class ASummaTsifr {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        out.println(n / 10 + (n % 10 == 9 ? 1 : 0));
    }
}
