package task;

import java.io.PrintWriter;

public class AOperatsiiBivayutRaznie {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        if (a == 0 && b == 0) {
            out.println(0);
            return;
        }
        if (a == b) {
            out.println(1);
            return;
        }
        if (Math.abs(a - b) % 2 == 1) {
            out.println(-1);
        } else {
            out.println(2);
        }
    }
}
