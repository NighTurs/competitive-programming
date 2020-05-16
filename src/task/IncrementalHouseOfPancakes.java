package task;

import java.io.PrintWriter;

public class IncrementalHouseOfPancakes {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long l = in.nextLong();
        long r = in.nextLong();
        long ma = l;
        long mi = r;
        if (r > l) {
            ma = r;
            mi = l;
        }

        long j1 = 1;
        long j2 = (long) (2 * 1e9);
        while (j1 < j2) {
            long m = (j1 + j2 + 1) / 2;
            if (ma - sum(m) >= 0 && ma - sum(m) >= mi) {
                j1 = m;
            } else {
                j2 = m - 1;
            }
        }

        long count = j1;
        if (r > l) {
            r -= sum(count);
        } else {
            l -= sum(count);
        }

        long num = 0;
        while (Math.max(l, r) >= count + 1) {

            num ++;
            boolean rfirst = false;
            if (r > l) {
                rfirst = true;
            }

            if (Math.min(l, r) < count + 2) {
                if (rfirst) {
                    r -= count + 1;
                } else {
                    l -= count + 1;
                }
                count++;
                break;
            }

            j1 = 1;
            j2 = ((long)(2 * 1e9) - count) / 2;

            while (j1 < j2) {
                long mm = (j1 + j2 + 1) / 2;
                long m = count + mm * 2;
                long f = sum2(count + 1, m % 2 == (count + 1) % 2 ? m : m - 1);
                long s = sum2(count + 2, m % 2 == (count + 2) % 2 ? m : m - 1);
                boolean good = true;
                if (rfirst) {
                    good = r - f >= 0 && l - s >= 0 && r - f > l - s;
                } else {
                    good = r - s >= 0 && l - f >= 0 && l - f > r - s;
                }
                if (good) {
                    j1 = mm;
                } else {
                    j2 = mm - 1;
                }
            }
            long m = count + j1 * 2;
            long f = sum2(count + 1, m % 2 == (count + 1) % 2 ? m : m - 1);
            long s = sum2(count + 2, m % 2 == (count + 2) % 2 ? m : m - 1);
            if (rfirst) {
                r -= f;
                l -= s;
            } else {
                r -= s;
                l -= f;
            }
            count = count + j1 * 2;
        }

        out.println(String.format("Case #%d: %d %d %d", testNumber, count, l, r));
    }

    static long sum2(long from, long to) {
        long ct = (to - from) / 2 + 1;
        return from * ct + sum(ct - 1) * 2;
    }

    static long sum(long n) {
        return (1 + n) * n / 2;
    }
}
