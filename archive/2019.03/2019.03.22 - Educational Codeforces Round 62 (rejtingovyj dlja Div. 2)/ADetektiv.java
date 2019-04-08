package task;

import java.io.PrintWriter;

public class ADetektiv {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int ans = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int a = in.nextInt() - 1;
            max = Math.max(max, a);
            if (max <= i) {
                ans++;
                max = 0;
            }
        }
        out.println(ans);
    }
}
