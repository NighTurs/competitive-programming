package task;

import task.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        if (n % 2 == 1) {
            out.print(0);
        } else {
            out.print(n % 4 == 0 ? (n - 1) / 4 : n / 4);
        }
    }
}
