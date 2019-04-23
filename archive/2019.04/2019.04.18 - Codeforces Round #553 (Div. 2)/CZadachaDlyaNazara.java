package task;

import java.io.PrintWriter;

public class CZadachaDlyaNazara {

    static final long MOD = (int) 1e9 + 7;
    long ans = 0;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long l = in.nextLong();
        long r = in.nextLong();
        doit(1, 1, l, r, 1, 1, true, 3, 2);
        out.println(ans);
    }

    private void doit(long l, long r, long ll, long rr, long i, long h, boolean isOdd, long nextOdd, long nextEven) {
        long bl = Math.max(l, ll);
        long br = Math.min(r, rr);
        if (bl <= br) {
            long addSt = i + (bl - l) * 2;
            long ct = (br - bl + 1);
            ans = (ans + ((ct % MOD) * (addSt % MOD)) % MOD + ((((ct - 1) % MOD) * (ct % MOD)) % MOD)) % MOD;
        }

        if (r >= rr) {
            return;
        }

        long nextSt = isOdd ? nextEven : nextOdd;
        long nextCt = (r - l + 1) * 2;
        long nextLast = nextSt + (nextCt - 1) * 2;
        if (isOdd) {
            doit(r + 1, r + nextCt, ll, rr, nextSt, nextLast, false, nextOdd, nextLast + 2);
        } else {
            doit(r + 1, r + nextCt, ll, rr, nextSt, nextLast, true, nextLast + 2, nextEven);
        }
    }
}
