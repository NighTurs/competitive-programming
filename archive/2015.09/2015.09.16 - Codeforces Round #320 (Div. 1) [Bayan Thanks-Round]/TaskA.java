package task;

import task.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();

        if (b > a) {
            out.print(-1);
        } else {
            double min = Double.MAX_VALUE;
            int r = (int) ((a + b) / b);
            if (r % 2 != 0) {
                r--;
            }
            if (r != 0) {
                min = Math.min((a + b) * 1.0 / r, min);
            }
            int l = (int) ((a - b) / b);
            if (l % 2 != 0) {
                l--;
            }
            if (l != 0) {
                min = Math.min((a - b) * 1.0 / l, min);
            }
            out.print(String.format("%.10f", min));
        }
    }
}
