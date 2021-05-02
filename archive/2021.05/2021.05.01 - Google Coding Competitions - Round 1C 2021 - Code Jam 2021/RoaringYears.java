package task;

import java.io.PrintWriter;
import java.math.BigInteger;

public class RoaringYears {

    static BigInteger m = BigInteger.ONE;

    public static void doit(long cand, BigInteger r) {
        StringBuilder sb = new StringBuilder();
        sb.append(cand);
        long cur = cand;
        BigInteger bi;
        do {
            cur += 1;
            sb.append(cur);
            bi = new BigInteger(sb.toString());
        } while (bi.compareTo(r) <= 0);
        if (m.compareTo(bi) >= 0 || m.equals(BigInteger.ONE)) {
            m = bi;
        }
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long a = in.nextLong();
        m = BigInteger.ONE;

        String sa = String.valueOf(a);

        BigInteger r = BigInteger.valueOf(a);

        for (int i = 1; i <= 20; i++) {
            if (i > sa.length()) {
                continue;
            }
            String sb = sa.substring(0, i);
            long cand = Long.valueOf(sb);
            for (int gap = -100; gap <= 100; gap++) {
                if (cand + gap <= 0) {
                    continue;
                }
                doit(cand + gap, r);
            }
        }

        out.println(String.format("Case #%d: %s", testNumber, m.toString()));

    }
}
