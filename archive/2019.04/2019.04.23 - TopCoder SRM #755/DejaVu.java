package task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DejaVu {

    public int mostDejaVus(int n, int seed, int R) {
        long[] a = new long[n];
        a[0] = seed;

        for (int i = 1; i < n; i++) {
            a[i] = (a[i - 1] * 1664525 + 1013904223) % 4294967296L;
        }

        int[] m = new int[n];
        for (int i = 0; i < n; i++) {
            m[i] = (int) (a[i] % R);
        }

        Map<Integer, List<Integer>> mp = new HashMap<>();

        for (int i = 0; i < n; i++) {
            mp.putIfAbsent(m[i], new ArrayList<>());
            mp.get(m[i]).add(i);
        }

        List<List<Query>> queries = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            queries.add(new ArrayList<>());
        }

        for (Map.Entry<Integer, List<Integer>> entry : mp.entrySet()) {
            List<Integer> idxs = entry.getValue();
            int prevEnd = 0;
            for (int i = 0; i < idxs.size() - 1; i++) {
                int first = idxs.get(i);
                int last = idxs.get(i + 1);
                int until = i + 2 < idxs.size() ? idxs.get(i + 2) - 1 : n - 1;

                queries.get(prevEnd).add(new Query(0, last, until));
                queries.get(first + 1).add(new Query(1, last, until));
                prevEnd = first + 1;
            }
        }

        SegmentTree tree = new SegmentTree(n);
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (Query query : queries.get(i)) {
                if (query.type == 0) {
                    tree.update(1, 0, n - 1, query.l, query.r, 1);
                } else {
                    tree.update(1, 0, n - 1, query.l, query.r, -1);
                }
            }

            ans = Math.max(ans, tree.query(1, 0, n - 1, 0, n - 1));
            if (ans == 11) {
                int z = 1;
            }
        }
        return ans;
    }

    public static class SegmentTree {

        public Data[] t;

        public SegmentTree(int n) {
            this.t = new Data[n * 4 + 1];
            for (int i = 0; i < n * 4 + 1; i++) {
                this.t[i] = new Data();
            }
        }

        public void update(int v, int tl, int tr, int l, int r, int newVal) {
            if (l > r) {
                return;
            }
            if (l == tl && tr == r) {
                t[v].update(newVal);
            } else {
                int tm = (tl + tr) / 2;
                update(v * 2, tl, tm, l, Math.min(tm, r), newVal);
                update(v * 2 + 1, tm + 1, tr, Math.max(tm + 1, l), r, newVal);
                t[v].combine(t[v * 2], t[v * 2 + 1]);
            }
        }

        public int query(int v, int tl, int tr, int l, int r) {
            if (l == tl && tr == r) {
                return t[v].max;
            }
            int tm = (tl + tr) / 2;
            if (r <= tm) {
                return query(v * 2, tl, tm, l, r) + t[v].add;
            }
            if (l > tm) {
                return query(v * 2 + 1, tm + 1, tr, l, r) + t[v].add;
            }
            return Math.max(query(v * 2, tl, tm, l, Math.min(tm, r)),
                    query(v * 2 + 1, tm + 1, tr, Math.max(tm + 1, l), r)) + t[v].add;
        }

        public static class Data {

            int add;
            int max;

            public void update(int val) {
                this.add += val;
                this.max += val;
            }

            public Data combine(Data a, Data b) {
                this.max = Math.max(a.max, b.max) + add;
                return this;
            }
        }
    }

    public static class Query {

        int type;
        int l, r;

        public Query(int type, int l, int r) {
            this.type = type;
            this.l = l;
            this.r = r;
        }
    }
}
