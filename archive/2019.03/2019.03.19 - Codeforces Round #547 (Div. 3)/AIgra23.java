package task;

import java.io.PrintWriter;

public class AIgra23 {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        if (b % a != 0) {
            out.println(-1);
            return;
        }
        int c = b / a;
        int ans = 0;
        while (c % 2 == 0) {
            c /= 2;
            ans++;
        }
        while (c % 3 == 0) {
            c /= 3;
            ans++;
        }
        if (c != 1) {
            out.println(-1);
        } else {
            out.println(ans);
        }
    }
}
