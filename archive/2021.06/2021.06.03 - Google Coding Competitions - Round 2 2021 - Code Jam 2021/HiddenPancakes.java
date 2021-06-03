package task;

import java.io.PrintWriter;

public class HiddenPancakes {

    int[] a;
    int n;
    SegmentTree sg;
    long[] fact;
    static final long MOD = (int) 1e9 + 7;

    {
        int N = (int) 1e5 + 5;
        fact = new long[N];
        fact[0] = 1;
        for (int i = 1; i < N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
    }

    public long doit(int from, int to, int inc) {
        if (from > to) {
            return 1;
        }
        SegmentTree.Data dt = sg.query(1, 0, n - 1, from, to);
        if (dt.min != inc) {
            return 0;
        }
        long left = doit(from, dt.pos - 1, inc);
        long right = doit(dt.pos + 1, to, inc + 1);
        long bin = CombinUtils.binom(to - from, dt.pos - from, fact, MOD);
        return (left * right) % MOD * bin % MOD;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        sg = new SegmentTree(n);
        sg.build(a, 1, 0, n - 1);

        out.println(String.format("Case #%d: %d", testNumber, doit(0, n - 1, 1)));
    }

    public static class SegmentTree {

        public SegmentTree.Data[] t;

        public SegmentTree(int n) {
            this.t = new SegmentTree.Data[n * 4 + 1];
            for (int i = 0; i < n * 4 + 1; i++) {
                this.t[i] = new SegmentTree.Data();
            }
        }

        public void build(int a[], int v, int tl, int tr) {
            if (tl == tr) {
                t[v].update(a[tl], tl);
            } else {
                int tm = (tl + tr) / 2;
                build(a, v * 2, tl, tm);
                build(a, v * 2 + 1, tm + 1, tr);
                t[v].combine(t[v * 2], t[v * 2 + 1]);
            }
        }

        public SegmentTree.Data query(int v, int tl, int tr, int l, int r) {
            if (l == tl && tr == r) {
                return t[v];
            }
            int tm = (tl + tr) / 2;
            if (r <= tm) {
                return query(v * 2, tl, tm, l, r);
            }
            if (l > tm) {
                return query(v * 2 + 1, tm + 1, tr, l, r);
            }
            return new SegmentTree.Data().combine(query(v * 2, tl, tm, l, Math.min(tm, r)),
                    query(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r));
        }

        public static class Data {

            int min;
            int pos;

            public void update(int val, int pos) {
                this.min = val;
                this.pos = pos;
            }

            public SegmentTree.Data combine(SegmentTree.Data a, SegmentTree.Data b) {
                if (a.min < b.min) {
                    this.min = a.min;
                    this.pos = a.pos;
                } else {
                    this.min = b.min;
                    this.pos = b.pos;
                }
                return this;
            }
        }
    }
}
