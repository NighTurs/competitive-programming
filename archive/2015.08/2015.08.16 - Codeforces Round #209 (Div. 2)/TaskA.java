package task;

import task.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int ans = 4;
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < m; h++) {
                int cur = in.nextInt();
                if ((i == 0 || h == 0 || i == n - 1 || h == m - 1) && cur == 1) {
                    ans = 2;
                }
            }
        }
        out.println(ans);
    }
}
