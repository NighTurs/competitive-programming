package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class DTheBeatles {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();

        Pair<Long, Long> maxMin = new Pair<>((long) -1, Long.MAX_VALUE);

        for (Integer next : Arrays.asList(b, k - b, k + b)) {
            int l = next - a;
            if (closest(a + l, k, 0) != b) {
                continue;
            }
            doit(l, k, n, maxMin);
        }

        for (Integer next : Arrays.asList(a - b, a + b, k - b)) {
            int l = next;
            if (closest(l, k, a) != b) {
                continue;
            }
            doit(l, k, n, maxMin);
        }
        out.println(String.format("%d %d ", maxMin.sc, maxMin.fs));
    }

    public void doit(int l, long k, long n, Pair<Long, Long> maxMin) {
        if (l == 0) {
            return;
        }
        for (long c = k; c <= n * k; c += k) {
            long cand = (n * k) / Euclid.gcd(l + c, k * n);
            maxMin.fs = Math.max(maxMin.fs, cand);
            maxMin.sc = Math.min(maxMin.sc, cand);
        }
    }

    public int closest(int x, int k, int diff) {
        return Math.min((x - diff) % k, k - (x - diff) % k);
    }
}
