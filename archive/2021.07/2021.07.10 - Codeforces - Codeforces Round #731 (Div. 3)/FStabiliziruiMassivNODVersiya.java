package task;

import java.io.PrintWriter;

public class FStabiliziruiMassivNODVersiya {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int gcd = a[0];
        for (int i = 1; i < n; i++) {
            gcd = (int)Euclid.gcd(gcd, a[i]);
        }
        for (int i = 0; i < n; i++) {
            a[i] /= gcd;
        }
        int[] aa = new int[n * 2];
        for (int i = 0; i < 2 * n; i++) {
            aa[i] = a[i % n];
        }

        SegmentTree sg = new SegmentTree(n * 2);
        sg.build(aa, 1, 0, n * 2 - 1);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int ans = sg.query(1, 0, n * 2 - 1, i, n * 2 - 1, aa[i]);
            assert ans == 1;
            int path = sg.mem - i;
            max = Math.max(path, max);
        }
        out.println(max);
        
    }
    public static class SegmentTree {

        public SegmentTree.Data[] t;
        public int mem = -1;

        public SegmentTree(int n) {
            this.t = new SegmentTree.Data[n * 4 + 1];
            for (int i = 0; i < n * 4 + 1; i++) {
                this.t[i] = new SegmentTree.Data();
            }
        }

        public void build(int a[], int v, int tl, int tr) {
            if (tl == tr) {
                t[v].update(a[tl]);
            } else {
                int tm = (tl + tr) / 2;
                build(a, v * 2, tl, tm);
                build(a, v * 2 + 1, tm + 1, tr);
                t[v].combine(t[v * 2], t[v * 2 + 1]);
            }
        }

        public int query(int v, int tl, int tr, int l, int r, int gcd) {
            if (l == tl && tr == r) {
                int val = (int)Euclid.gcd(gcd, t[v].gcd);
                if (tl == tr && val == 1) {
                    mem = tl;
                }
                if (val != 1 || tl == tr) {
                    return val;
                }
            }
            int tm = (tl + tr) / 2;
            if (r <= tm) {
                return query(v * 2, tl, tm, l, r, gcd);
            }
            if (l > tm) {
                return query(v * 2 + 1, tm + 1, tr, l, r, gcd);
            }
            gcd = query(v * 2, tl, tm, l, Math.min(tm, r), gcd);
            if (gcd != 1) {
                return query(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r, gcd);
            }
            return gcd;
        }

        public static class Data {

            int gcd;

            public void update(int val) {
                this.gcd = val;
            }

            public SegmentTree.Data combine(SegmentTree.Data a, SegmentTree.Data b) {
                this.gcd = (int)Euclid.gcd(a.gcd, b.gcd);
                return this;
            }
        }
    }

}
