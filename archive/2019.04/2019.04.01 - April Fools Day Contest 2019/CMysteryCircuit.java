package task;

import java.io.PrintWriter;

public class CMysteryCircuit {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int ans = n + 1;
        ans += n;

        if (n == 0) {
            out.println(0);
            return;
        }

        for (int i = 1; i <= n; i++) {
            ans += i;
        }
        out.println(ans);

    }
}
