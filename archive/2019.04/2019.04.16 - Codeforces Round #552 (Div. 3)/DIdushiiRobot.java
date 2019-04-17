package task;

import java.io.PrintWriter;

public class DIdushiiRobot {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int bmax = in.nextInt();
        int amax = in.nextInt();

        int b = bmax;
        int a = amax;

        for (int i = 0; i < n; i++) {
            int s = in.nextInt();
            if (b > 0 && a < amax && s == 1) {
                b--;
                a++;
            } else if (a > 0) {
                a--;
            } else if (b > 0) {
                b--;
            } else {
                out.println(i);
                return;
            }
        }
        out.println(n);
    }
}
