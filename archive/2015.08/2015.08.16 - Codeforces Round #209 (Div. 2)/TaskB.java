package task;

import task.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt(), k = in.nextInt();
        int last = 1;


        for (int i = 0; i < k; i++) {
            out.print((last + 1) + " " + last + " ");
            last += 2;
        }

        for (int i = k; i < n; i++) {
            out.print(last + " " + (last + 1) + " ");
            last += 2;
        }
    }
}
