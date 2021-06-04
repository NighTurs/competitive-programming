package task;

import java.io.PrintWriter;

public class BZauryadnieChisla {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long ans = 0;
        for (int i = 1; i <= 9; i++) {
            StringBuilder sb = new StringBuilder();
            for (int h = 1; h <= 10; h++) {
                sb.append(i);
                long val = Long.parseLong(sb.toString());
                if (val <= n) {
                    ans++;
                }
            }
        }
        out.println(ans);
    }
}
