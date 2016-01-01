package task;

import task.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int a = in.nextInt();
        for (int i = 1; i < n; i++) {
            int b = in.nextInt();
            while (a * b > 0) {
                if (a > b) {
                    a = a % b;
                } else {
                    b = b % a;
                }
            }
            a = a + b;
        }
        out.print(a * n);
    }
}
