package task;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class NewElementsPart1 {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) {
            a[i][0] = in.nextInt();
            a[i][1] = in.nextInt();
        }
        Set<Pair<Long, Long>> s = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < n; h++) {
                if (i == h) {
                    continue;
                }
                if (a[h][0] <= a[i][0]) {
                    continue;
                }
                if (a[i][1] <= a[h][1]) {
                    continue;
                }
                long first = a[h][0] - a[i][0];
                long second = a[i][1] - a[h][1];
                long gcd = Euclid.gcd(first, second);
                first /= gcd;
                second /= gcd;
                s.add(new Pair<>(first, second));
            }
        }
        out.println(String.format("Case #%d: %d", testNumber, s.size() + 1));
    }
}
