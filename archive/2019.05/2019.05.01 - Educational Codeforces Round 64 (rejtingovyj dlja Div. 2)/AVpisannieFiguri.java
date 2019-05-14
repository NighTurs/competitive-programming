package task;

import java.io.PrintWriter;

public class AVpisannieFiguri {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int prev2 = -1;
        int prev = in.nextInt();
        long ans = 0;
        for (int i = 0; i < n - 1; i++) {
            int cur = in.nextInt();
            if (prev == cur || (prev == 2 && cur == 3) || (prev == 3 && cur == 2)) {
                out.println("Infinite");
                return;
            }
            if (prev == 1) {
                if (cur == 2 && prev2 == 3) {
                    ans += 2;
                } else {
                    ans += cur == 2 ? 3 : 4;
                }
            } else if (prev == 2) {
                ans += 3;
            } else if (prev == 3) {
                ans += 4;
            }
            prev2 = prev;
            prev = cur;
        }
        out.println("Finite");
        out.println(ans);
    }
}
