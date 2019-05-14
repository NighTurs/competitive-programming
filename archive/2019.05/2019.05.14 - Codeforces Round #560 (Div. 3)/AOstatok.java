package task;

import java.io.PrintWriter;

public class AOstatok {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        int ans = 0;
        String s = in.next();
        for (int i = n - x; i < n; i++) {

            if (s.charAt(i) == '0') {
                if (i == n - y - 1) {
                    ans++;
                }
            } else {
                if (i != n - y - 1) {
                    ans++;
                }
            }
        }
        out.println(ans);
    }
}
