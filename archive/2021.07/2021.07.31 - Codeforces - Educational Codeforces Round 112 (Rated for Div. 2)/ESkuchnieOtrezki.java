package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ESkuchnieOtrezki {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        SegmentTree sg = new SegmentTree(n);


        List<Req> reqs = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            if (to != n - 1) {
                to--;
            }
            reqs.add(new Req(from, to, in.nextInt()));
        }
        reqs.sort(Comparator.comparingInt(a -> a.w));
        int cur = 0;
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < m; i++) {
            while (cur < m && !sg.check()) {
                Req r = reqs.get(cur);
                sg.update(1, 0, n  - 1, r.l, r.r, 1);
                cur++;
            }
            if (!sg.check()) {
                out.println(ans);
                return;
            }
            ans = Math.min(ans, reqs.get(cur - 1).w - reqs.get(i).w);
            Req r = reqs.get(i);
            sg.update(1, 0, n - 1, r.l, r.r, -1);
        }
        out.println(ans);
    }

    public static class Req {
        int l, r, w;

        public Req(int l, int r, int w) {
            this.l = l;
            this.r = r;
            this.w = w;
        }
    }

    public static class SegmentTree {

        public SegmentTree.Data[] t;

        public SegmentTree(int n) {
            this.t = new SegmentTree.Data[n * 4 + 1];
            for (int i = 0; i < n * 4 + 1; i++) {
                this.t[i] = new SegmentTree.Data();
            }
        }

        public boolean check() {
            return this.t[1].min + this.t[1].offset > 0;
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

        public static class Data {

            int offset;
            int min;

            public void update(int val) {
                this.offset += val;
            }

            public SegmentTree.Data combine(SegmentTree.Data a, SegmentTree.Data b) {
                this.min = Math.min(a.min + a.offset, b.min + b.offset);
                return this;
            }
        }
    }
}
