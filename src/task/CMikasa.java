package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CMikasa {
    final int N = 32;

    long doit(long[] a) {
        long num = 0;
        for (int i = 0; i < a.length; i++) {
            num = num * 2 + a[i];
        }
        return num;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long a = in.nextInt();
        long b = in.nextInt();
        if (a > b) {
            out.println(0);
            return;
        }
        long[] aa = new long[N];
        String s = Long.toBinaryString(a);
        for (int i = s.length() - 1; i >= 0; i--) {
            aa[N - 1 - (s.length() - 1 - i)] = s.charAt(i) - '0';
        }

        List<Integer> ps = new ArrayList<>();
        int ct = 0;
        for (int i = 0; i < N; i++) {
            if (aa[i] == 0) {
                ct++;
                ps.add(i);
            }
        }

        long k = doit(aa);
        assert k == a;

        long t1 = 0;
        long t2 = (long) (Math.pow(2, ct) + 0.1) - 1;
        while (t1 < t2) {
            long m = (t1 + t2) / 2;
            s = Long.toBinaryString(m);
            long[] cand = new long[N];
            for (int i = s.length() - 1; i >= 0; i--) {
                cand[ps.get(ps.size() - 1 - (s.length() - 1 - i))] = s.charAt(i) - '0';
            }
            for (int i = 0; i < N; i++) {
                cand[i] = cand[i] ^ aa[i];
            }
            long d = doit(cand);
            if (d <= b) {
                t1 = m + 1;
            } else {
                t2 = m;
            }
        }
        s = Long.toBinaryString(t1);
        long[] cand = new long[N];
        for (int i = s.length() - 1; i >= 0; i--) {
            cand[ps.get(ps.size() - 1 - (s.length() - 1 - i))] = s.charAt(i) - '0';
        }
        out.println(doit(cand));
    }
}
