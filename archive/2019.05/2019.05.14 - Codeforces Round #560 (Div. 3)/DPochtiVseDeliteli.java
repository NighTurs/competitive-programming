package task;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class DPochtiVseDeliteli {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        outer:
        for (int tt = 0; tt < t; tt++) {
            int n = in.nextInt();
            Set<Long> a = new HashSet<>();
            long min = Integer.MAX_VALUE;
            long max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                long val = in.nextInt();
                a.add(val);
                min = Math.min(val, min);
                max = Math.max(val, max);
            }
            long num = min * max;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i != 0) {
                    continue;
                }
                if (!a.contains((long)i) || !a.contains(num / i)) {
                    out.println(-1);
                    continue outer;
                }
                a.remove((long) i);
                a.remove(num / i);
            }
            if (a.isEmpty()) {
                out.println(num);
            } else {
                out.println(-1);
            }
        }
    }
}
