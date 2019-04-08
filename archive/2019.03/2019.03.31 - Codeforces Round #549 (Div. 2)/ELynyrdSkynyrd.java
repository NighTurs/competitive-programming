package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class ELynyrdSkynyrd {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[m];
        int[] mp = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt() - 1;
        }
        for (int i = 0; i < n - 1; i++) {
            mp[a[i]] = a[i + 1];
        }
        mp[a[n - 1]] = a[0];

        for (int i = 0; i < m; i++) {
            b[i] = in.nextInt() - 1;
        }

        int[] lastN = new int[n];
        int[] prevM = new int[m];
        int[] tail = new int[m];
        int[] len = new int[m];
        int[] nearest = new int[m];

        Arrays.fill(prevM, -1);
        Arrays.fill(nearest, Integer.MAX_VALUE);
        Arrays.fill(lastN, -1);


        for (int i = m - 1; i >= 0; i--) {
            int cur = b[i];
            int next = mp[b[i]];

            if (lastN[next] == -1) {
                len[i] = 1;
                tail[i] = i;
            } else {
                int nextPos = lastN[next];
                len[i] = len[nextPos] + 1;
                prevM[nextPos] = i;
                tail[i] = tail[nextPos];
                if (len[i] > n) {
                    tail[i] = prevM[tail[i]];
                }
            }
            if (len[i] >= n) {
                nearest[i] = tail[i];
            }
            lastN[cur] = i;
        }

        SegmentTree tree = new SegmentTree(m);
        tree.build(nearest, 1, 0, m - 1);

        for (int i = 0; i < q; i++) {
            int l = in.nextInt() - 1;
            int r = in.nextInt() - 1;
            out.print((tree.query(1, 0, m - 1, l, r).min <= r) ? 1 : 0);
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
                t[v].update(a[tl]);
            } else {
                int tm = (tl + tr) / 2;
                build(a, v * 2, tl, tm);
                build(a, v * 2 + 1, tm + 1, tr);
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
            return new Data().combine(query(v * 2, tl, tm, l, tm), query(v * 2 + 1, tm + 1, tr, tm + 1, r));
        }

        public static class Data {

            int min;

            public void update(int val) {
                this.min = val;
            }

            public Data combine(Data a, Data b) {
                this.min = Math.min(a.min, b.min);
                return this;
            }
        }
    }
}
