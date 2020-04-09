package task;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CYevgeniiIMassiv {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Map<Long, Integer> sums = new HashMap<>();
        long prev = 0;
        long ans = (long) (1 + n) * n / 2;
        long maxLast = -2;
        sums.put(0L, -1);
        for (int i = 0; i < n; i++) {
            long val = in.nextLong();
            long cur = prev + val;
            long last = sums.getOrDefault(cur, -2);
            maxLast = Math.max(maxLast, last);
            ans -= maxLast + 2;
            sums.put(cur, i);
            prev = cur;
        }
        out.println(ans);
    }
}
