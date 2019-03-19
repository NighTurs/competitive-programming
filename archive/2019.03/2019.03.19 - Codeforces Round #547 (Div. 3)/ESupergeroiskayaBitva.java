package task;

import java.io.PrintWriter;

public class ESupergeroiskayaBitva {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long H = in.nextLong();
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        long t1 = 0;
        long t2 = H;

        while (t1 < t2) {
            long mid = (t1 + t2 + 1) / 2;
            if (survive(mid, a)) {
                t2 = mid - 1;
            } else {
                t1 = mid;
            }
        }

        if (survive(t1, a)) {
            out.println(-1);
            return;
        }

        long ans = 0;
        if (H > t1) {
            long diff = 0;
            for (int i = 0; i < n; i++) {
                diff += a[i];
            }

            if (diff >= 0) {
                out.println(-1);
                return;
            }
            diff = -diff;

            long cycles = (H - t1 + diff - 1) / diff;
            ans = cycles * n;

            H -= diff * cycles;
        }
        for (int i = 0; i < n; i++) {
            if (H <= 0) {
                out.println(ans);
                return;
            }
            H += a[i];
            ans += 1;
        }
        if (H <= 0) {
            out.println(ans);
            return;
        }
    }

    private boolean survive(long t1, int[] a) {
        long hp = t1;
        for (int i = 0; i < a.length; i++) {
            if (hp <= 0) {
                return false;
            }
            hp += a[i];
        }
        if (hp <= 0) {
            return false;
        }
        return true;
    }
}
