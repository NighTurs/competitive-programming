package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class EKonditsioneri {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        long[] a = new long[n];
        Arrays.fill(a, ((long)1e9) * 2);
        int[] pos = new int[k];
        for (int i = 0; i < k; i++) {
            pos[i] = in.nextInt() - 1;
        }
        for (int i = 0; i < k; i++) {
            a[pos[i]] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            a[i] += i;
        }
        SegmentTree sg = new SegmentTree(n);
        sg.build(a, 1, 0, n - 1);
        for (int i = 0; i < n; i++) {
            out.print(sg.query(1, 0, n - 1, 0, n - 1).min + " ");
            if (i != n - 1) {
                sg.update(1, 0, n - 1, 0, i, 1);
                sg.update(1, 0, n - 1, i + 1, n - 1, -1);
            }
        }
        out.println();
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
                t[v].init(a[tl]);
            } else {
                int tm = (tl + tr) / 2;
                build(a, v * 2, tl, tm);
                build(a, v * 2 + 1, tm + 1, tr);
                t[v].combine(t[v * 2], t[v * 2 + 1]);
            }
        }

        public void update(int v, int tl, int tr, int l, int r, int offset) {
            if (l == tl && tr == r) {
                t[v].update(offset);
                return;
            }
            int tm = (tl + tr) / 2;
            if (r <= tm) {
                update(v * 2, tl, tm, l, r, offset);
            } else if (l > tm) {
                update(v * 2 + 1, tm + 1, tr, l, r, offset);
            } else {
                update(v * 2, tl, tm, l, Math.min(tm, r), offset);
                update(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r, offset);
            }
            t[v].combine(t[v * 2], t[v * 2 + 1]);
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
            return new SegmentTree.Data(t[v].offset).combine(query(v * 2, tl, tm, l, Math.min(tm, r)),
                    query(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r));
        }

        public static class Data {

            long offset = 0;
            long min;

            public Data() {
            }

            public Data(long offset) {
                this.offset = offset;
            }

            public void init(long val) {
                this.min = val;
            }

            public void update(long val) {
                this.offset += val;
            }

            public SegmentTree.Data combine(SegmentTree.Data a, SegmentTree.Data b) {
                this.min = Math.min(a.min + a.offset, b.min + b.offset);
                return this;
            }
        }
    }

}
