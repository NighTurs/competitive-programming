package task;

import java.io.PrintWriter;
import java.util.TreeSet;

public class Zillionim {
    int N = 100;

    public void solve(int testNumber, InputReader in, PrintWriter out) {

        if (testNumber == 1) {
            in.nextInt();
        }

        long take = (long)1e10;
        long max = (long)1e12;
        TreeSet<Long> ps = new TreeSet<>();
        while (true) {
            long p = in.nextLong();
            if (p == -1) {
                throw new RuntimeException();
            }
            if (p < 0) {
                return;
            }
            ps.add(p);
            long[][] c = new long[N + 1][3];
            for (int i = 0; i <= N; i++) {
                c[i][0] = -1;
            }
            int all = 0;
            long prev = 1;
            for (Long pp : ps) {
                long ct = (pp - prev) / take;
                if (ct > 0) {
                    all++;
                    c[(int)ct][0] = prev;
                    c[(int)ct][1] = pp - prev;
                    c[(int)ct][2]++;
                }
                prev = pp + take;
            }
            long ct = (max - prev + 1) / take;
            if (ct > 0) {
                all++;
                c[(int)ct][0] = prev;
                c[(int)ct][1] = max - prev + 1;
                c[(int)ct][2]++;
            }


            for (int i = N; i >= 0; i--) {
                if (c[i][0] == -1) {
                    continue;
                }
                if (i == 1) {
                    p = c[i][0];
                    break;
                }
                if (i > 2) {
                    p = c[i][0] + take * 2;
                    break;
                }
                if (all % 2 == 0) {
                    p = c[i][0];
                } else {
                    p = c[i][0] + (c[i][1] - take) / 2;
                }
            }

            ps.add(p);
            out.println(p);
            out.flush();
        }
    }
}
