package task;

import task.InputReader;
import java.io.PrintWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int k = in.nextInt();
        int n = (int) Math.pow(2, k);
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < n; h++) {
                out.print(ans(i, h, n) ? "+" : "*");
            }
            out.println();
        }
    }

    public boolean ans(int i, int h, int mul) {
        if (i == 0 && h == 0) {
            return true;
        }
        int dmul = mul / 2;
        int ii = i >= dmul ? i - dmul : i;
        int hh = h >= dmul ? h - dmul : h;
        if (i >= dmul && h >= dmul) {
            return !ans(ii, hh, dmul);
        } else {
            return ans(ii, hh, dmul);
        }
    }
}
