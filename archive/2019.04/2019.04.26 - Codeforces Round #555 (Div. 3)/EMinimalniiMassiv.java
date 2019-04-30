package task;

import java.io.PrintWriter;

public class EMinimalniiMassiv {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int[] ct = new int[n];
        for (int i = 0; i < n; i++) {
            ct[in.nextInt()]++;
        }

        SegmentTree tree = new SegmentTree(n);
        tree.build(ct, 1, 0, n - 1);
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            boolean found = false;
            int val = -1;
            if (n - a[i] < n) {
                val = tree.query(1, 0, n - 1, n - a[i], n - 1).value;
                if (val != Integer.MAX_VALUE) {
                    found = true;
                }
            }
            if (!found) {
                val = tree.query(1, 0, n - 1, 0, n - 1).value;
            }
            ans[i] = (val + a[i]) % n;
            tree.update(1, 0, n - 1, val);
        }
        for (int i = 0; i < n; i++) {
            out.print(ans[i]);
            out.print(' ');
        }
    }

    public static class SegmentTree {

        public Data[] t;

        public SegmentTree(int n) {
            this.t = new Data[n * 4 + 1];
            for (int i = 0; i < n * 4 + 1; i++) {
                this.t[i] = new Data();
            }
        }

        public void build(int a[], int v, int tl, int tr) {
            if (tl == tr) {
                t[v].value = a[tl] > 0 ? tl : Integer.MAX_VALUE;
                t[v].count = a[tl];
            } else {
                int tm = (tl + tr) / 2;
                build(a, v * 2, tl, tm);
                build(a, v * 2 + 1, tm + 1, tr);
                t[v].combine(t[v * 2], t[v * 2 + 1]);
            }
        }

        public void update(int v, int tl, int tr, int pos) {
            if (tl == tr) {
                t[v].update();
            } else {
                int tm = (tl + tr) / 2;
                if (pos <= tm) {
                    update(v * 2, tl, tm, pos);
                } else {
                    update(v * 2 + 1, tm + 1, tr, pos);
                }
                t[v].combine(t[v * 2], t[v * 2 + 1]);
            }
        }

        public Data query(int v, int tl, int tr, int l, int r) {
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
            return new Data().combine(query(v * 2, tl, tm, l, Math.min(tm, r)),
                    query(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r));
        }

        public static class Data {

            int value;
            int count;

            public void update() {
                this.count--;
                if (this.count <= 0) {
                    value = Integer.MAX_VALUE;
                }
            }

            public Data combine(Data a, Data b) {
                this.value = Math.min(a.value, b.value);
                return this;
            }
        }
    }
}
