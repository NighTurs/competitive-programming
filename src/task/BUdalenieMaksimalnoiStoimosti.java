package task;

import java.io.PrintWriter;

public class BUdalenieMaksimalnoiStoimosti {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        String s = in.next();
        if (b >= 0) {
            out.println(n * a + n * b);
        } else {

            char target = s.charAt(0);
            int numT = 0;


            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == target) {
                    numT++;
                }
            }

            int ans = numT * a + b;
            int prev = 0;
            for (int i = 0; i < n; i++) {
                if (i != 0 && s.charAt(i) != s.charAt(i - 1)) {
                    if (s.charAt(prev) != target) {
                        ans += (i - prev) * a + b;
                    }
                    prev = i;
                }
            }
            if (s.charAt(prev) != target) {
                ans += (n - prev) * a + b;
            }

            out.println(ans);
        }
    }
}
