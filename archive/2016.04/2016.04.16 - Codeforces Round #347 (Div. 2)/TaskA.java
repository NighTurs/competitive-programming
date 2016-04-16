package task;

import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s1 = in.next();
        String s2 = in.next();
        if (s1.equals(s2)) {
            out.print(s1);
        } else {
            out.print(1);
        }
    }
}
