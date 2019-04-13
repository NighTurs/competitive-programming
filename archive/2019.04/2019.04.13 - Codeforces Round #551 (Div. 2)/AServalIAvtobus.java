package task;

import java.io.PrintWriter;

public class AServalIAvtobus {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int t = in.nextInt();

        int minT = Integer.MAX_VALUE;
        int minI = 0;
        for (int i = 0; i < n; i++) {
            int s = in.nextInt();
            int d = in.nextInt();
            if (t <= s) {
                if (minT > s) {
                    minT = s;
                    minI = i;
                }
            } else {
                int g = (t - s + d - 1) / d;
                if (minT > g * d + s) {
                    minT = g * d + s;
                    minI = i;
                }
            }
        }
        out.println(minI + 1);
    }
}
