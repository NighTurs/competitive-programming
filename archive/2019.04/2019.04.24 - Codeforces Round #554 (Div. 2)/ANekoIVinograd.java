package task;

import java.io.PrintWriter;

public class ANekoIVinograd {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int even1 = 0;
        for (int i = 0 ; i < n; i++) {
            int a = in.nextInt();
            if (a % 2 == 0) {
                even1++;
            }
        }
        int even2 = 0;
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            if (a % 2 == 0) {
                even2++;
            }
        }
        int odd1 = n - even1;
        int odd2 = m - even2;
        out.println(Math.min(even1, odd2) + Math.min(odd1, even2));

    }
}
