package task;

import java.io.PrintWriter;

public class BUChiselYestDruzya {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }
        long[] b = new long[n];
        for (int i = 0; i < n - 1; i++) {
            b[i] = Math.abs(a[i] - a[i + 1]);
        }
        b[n - 1] = 1;
        SegmentTree sg = new SegmentTree(n);
        sg.build(b, 1, 0, n - 1);
        long max = 1;
        for (int i = 0; i < n; i++) {
            if (b[i] == 1) {
                continue;
            }
            long pos = -sg.query(1, 0, n - 1, i, n - 1, b[i]);
            long cand = pos - i + 1;
            max = Math.max(max, cand);
        }
        out.println(max);
    }

    public static class SegmentTree {

        public SegmentTree.Data[] t;

        public SegmentTree(int n) {
            this.t = new SegmentTree.Data[n * 4 + 1];
            for (int i = 0; i < n * 4 + 1; i++) {
                this.t[i] = new SegmentTree.Data();
            }
        }

        public void build(long a[], int v, int tl, int tr) {
            if (tl == tr) {
                t[v].update(a[tl]);
            } else {
                int tm = (tl + tr) / 2;
                build(a, v * 2, tl, tm);
                build(a, v * 2 + 1, tm + 1, tr);
                t[v].combine(t[v * 2], t[v * 2 + 1]);
            }
        }

        public long query(int v, int tl, int tr, int l, int r, long gcd) {
            long newGcd = Euclid.gcd(gcd, t[v].gcd);
            if (tl == tr) {
                if (newGcd == 1) {
                    return -tl;
                } else {
                    return newGcd;
                }
            }
            if (l == tl && tr == r) {
                if (newGcd != 1) {
                    return newGcd;
                }
            }

            int tm = (tl + tr) / 2;
            if (r <= tm) {
                return query(v * 2, tl, tm, l, r, gcd);
            }
            if (l > tm) {
                return query(v * 2 + 1, tm + 1, tr, l, r, gcd);
            }
            newGcd = query(v * 2, tl, tm, l, Math.min(tm, r), gcd);
            if (newGcd < 0) {
                return newGcd;
            }
            return query(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r, newGcd);
        }

        public static class Data {

            long gcd;

            public void update(long val) {
                this.gcd = val;
            }

            public SegmentTree.Data combine(SegmentTree.Data a, SegmentTree.Data b) {
                this.gcd = Euclid.gcd(a.gcd, b.gcd);
                return this;
            }
        }
    }

}
