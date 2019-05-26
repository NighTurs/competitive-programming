package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewElementsPart2 {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[][] a = new long[n][2];
        for (int i = 0; i < n; i++) {
            a[i][0] = in.nextInt();
            a[i][1] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            for (int h = i + 1; h < n; h++) {
                if (a[i][0] >= a[h][0] && a[i][1] >= a[h][1]) {
                    out.println(String.format("Case #%d: IMPOSSIBLE", testNumber));
                    return;
                }
            }
        }

        Pair<Integer, Integer> min = new Pair<>(Integer.MAX_VALUE, 1);
        Pair<Integer, Integer> max = new Pair<>(1, Integer.MAX_VALUE);
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

                if (i < h) {
                    // first * A > second * B
                    // B < first / second A
                    if (min.fs * 1.0 / min.sc > first * 1.0 / second) {
                        min = new Pair<>((int)first, (int)second);
                    }
                } else {
                    // first * A < second * B
                    // B > first / second A
                    if (max.fs * 1.0 / max.sc < first * 1.0 / second) {
                        max = new Pair<>((int)first, (int)second);
                    }
                }
            }
        }
        if (min.fs == Integer.MAX_VALUE) {
            min = new Pair<>(Integer.MAX_VALUE, max.sc);
        }
        if (max.sc == Integer.MAX_VALUE) {
            max = new Pair<>(1, Integer.MAX_VALUE);
        }
        if (min.sc == Integer.MAX_VALUE) {
            out.println(String.format("Case #%d: %d %d", testNumber, 1, 1));
            return;
        }

        long gcd = Euclid.gcd(min.sc, max.sc);
        long div = (min.sc * 1L * max.sc) / gcd;
        long from = max.fs * (div / max.sc);
        long to = min.fs * (div / min.sc);
        if (from >= to) {
            out.println(String.format("Case #%d: IMPOSSIBLE", testNumber));
            return;
        }
        Map<Integer, Integer> f = new HashMap<>();
        factor(min.sc, f);
        factor((int)(max.sc / gcd), f);

        int[][] ff = new int[f.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> e : f.entrySet()) {
            ff[i][0] = e.getKey();
            ff[i][1] = e.getValue();
            i++;
        }
        List<Integer> divs = new ArrayList<>();
        findDivs(ff, 0, 1, divs);
        long min1 = Integer.MAX_VALUE;
        long min2 = Integer.MAX_VALUE;
        for (i = 0; i < divs.size(); i++) {
            int d = divs.get(i);
            long v1 = div / d;
            long v2 = (from / d + 1) * d;
            if (v2 < to && v1 < min1 || (v1 == min1 && v2 < min2)) {
                v1 = min1;
                v2 = min2;
            }
        }
        out.println(String.format("Case #%d: %d %d", testNumber, min1, min2));
    }

    void findDivs(int[][] f, int pos, int cur, List<Integer> divs) {
        if (pos == f.length) {
            divs.add(cur);
            return;
        }
        int mul = 1;
        for (int i = 0; i < f[pos][1]; i++) {
            findDivs(f, pos + 1, cur * mul, divs);
            mul *= f[pos][0];
        }
    }

    void factor(int val, Map<Integer, Integer> f) {
        int cur = val;
        for (int i = 2; i <= Math.sqrt(val); i++) {
            while (cur % i == 0) {
                cur /= i;
                f.putIfAbsent(i, 0);
                f.put(i, f.get(i) + 1);
            }
        }
        if (cur != 1) {
            f.putIfAbsent(cur, 0);
            f.put(cur, f.get(cur) + 1);
        }
    }
}
