package task;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CMankhettenskiePodmassivi {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n + 2];
        a[0] = -1;
        a[n + 1] = (int)1e9 + 1;
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }


        List<Pair<Integer, Integer>> b = new LinkedList<>();

        SegmentTree sg = new SegmentTree(n + 2);
        sg.build(a, 1, 0, n + 1);

        SegmentTree sg2 = new SegmentTree(n + 2);
        a[n + 1] = -1;
        a[0] = (int)1e9 + 1;
        sg2.build(a, 1, 0, n + 1);

        for (int i = 1; i <= n; i++) {
            SegmentTree.Data left = sg.query(1, 0, n + 1, 0, i - 1, a[i], true, true);
            SegmentTree.Data right = sg.query(1, 0, n + 1, i + 1, n + 1, a[i], false, false);
            if (left.minP != 0 && right.maxP != n + 1) {
                b.add(Pair.of(left.minP, right.maxP));
            }
            right = sg2.query(1, 0, n + 1, i + 1, n + 1, a[i], true, false);
            left = sg2.query(1, 0, n + 1, 0, i - 1, a[i], false, true);
            if (left.maxP != 0 && right.minP != n + 1) {
                b.add(Pair.of(left.maxP, right.minP));
            }
        }
        b.sort((x, y) -> x.sc == y.sc ? Integer.compare(x.fs, y.fs) : Integer.compare(x.sc, y.sc));
        Queue<Pair<Integer, Integer>> q = new LinkedList<>(b);
        q.add(Pair.of(n + 1, n + 1));

        long ans = 0;
        for (int i = 1; i <= n; i++) {
            while (q.peek().fs < i) {
                q.poll();
            }
            Pair<Integer, Integer> p = q.peek();
            ans += p.sc - i;
        }

        out.println(ans);
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

        public boolean isGood(Data dt, int val, boolean min) {
            if (min) {
                if (dt.min <= val) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (dt.max >= val) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        public SegmentTree.Data query(int v, int tl, int tr, int l, int r, int val, boolean min, boolean right) {
            if (l == tl && tr == r) {
                if (tl == tr || !isGood(t[v], val, min)) {
                    return t[v];
                }
            }
            int tm = (tl + tr) / 2;
            if (r <= tm) {
                return query(v * 2, tl, tm, l, r, val, min, right);
            }
            if (l > tm) {
                return query(v * 2 + 1, tm + 1, tr, l, r, val, min, right);
            }
            if (right) {
                Data dt = query(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r, val, min, right);
                if (isGood(dt, val, min)) {
                    return dt;
                } else {
                    return query(v * 2, tl, tm, l, Math.min(tm, r), val, min, right);
                }
            } else {
                Data dt = query(v * 2, tl, tm, l, Math.min(tm, r), val, min, right);
                if (isGood(dt, val, min)) {
                    return dt;
                } else {
                    return query(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r, val, min, right);
                }
            }
        }

        public static class Data {

            int min;
            int minP;
            int max;
            int maxP;

            public void update(int val, int idx) {
                this.min = val;
                this.minP = idx;
                this.max = val;
                this.maxP = idx;
            }

            public SegmentTree.Data combine(SegmentTree.Data a, SegmentTree.Data b) {
                if (a.min < b.min) {
                    this.min = a.min;
                    this.minP = a.minP;
                } else {
                    this.min = b.min;
                    this.minP = b.minP;
                }
                if (a.max < b.max) {
                    this.max = b.max;
                    this.maxP = b.maxP;
                } else {
                    this.max = a.max;
                    this.maxP = a.maxP;
                }
                return this;
            }
        }
    }

}
