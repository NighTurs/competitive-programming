package task;



import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CAquaMoonIStrannayaSortirovka {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        List<Pair<Integer, Integer>> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(Pair.of(in.nextInt(), i));
        }
        a.sort(Comparator.comparingInt(x -> x.fs));
        SegmentTree sg = new SegmentTree(n);
        sg.build(1, 0, n - 1);

        Map<Integer, Integer> m = new HashMap<>();
        int to = 0;
        for (Pair<Integer, Integer> p : a) {
            int offset = sg.query(1, 0, n - 1, p.sc, 0);
            int swaps = offset + (p.sc + offset - to);
            if (swaps % 2 == 1) {
                m.putIfAbsent(p.fs, 0);
                m.put(p.fs, m.get(p.fs) + 1);
            }
            sg.update(1, 0, n - 1, 0, p.sc, 1);
            to++;
        }
        for (Integer v : m.keySet()) {
            if (m.get(v) % 2 == 1) {
                out.println("NO");
                return;
            }
        }
        out.println("YES");
    }
    public static class SegmentTree {

        public SegmentTree.Data[] t;

        public SegmentTree(int n) {
            this.t = new SegmentTree.Data[n * 4 + 1];
            for (int i = 0; i < n * 4 + 1; i++) {
                this.t[i] = new SegmentTree.Data();
            }
        }

        public void build(int v, int tl, int tr) {
            if (tl == tr) {
                t[v].offset = 0;
            } else {
                int tm = (tl + tr) / 2;
                build(v * 2, tl, tm);
                build(v * 2 + 1, tm + 1, tr);
            }
        }

        public void update(int v, int tl, int tr, int l, int r, int offset) {
            if (l == tl && tr == r) {
                t[v].offset += offset;
                return;
            }
            int tm = (tl + tr) / 2;
            if (r <= tm) {
                update(v * 2, tl, tm, l, r, offset);
                return;
            }
            if (l > tm) {
                update(v * 2 + 1, tm + 1, tr, l, r, offset);
                return;
            }
            update(v * 2, tl, tm, l, Math.min(tm, r), offset);
            update(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r, offset);
        }

        public int query(int v, int tl, int tr, int pos, int offset) {
            if (tl == tr) {
                return t[v].offset + offset;
            } else {
                int tm = (tl + tr) / 2;
                if (pos <= tm) {
                    return query(v * 2, tl, tm, pos, offset + t[v].offset);
                } else {
                    return query(v * 2 + 1, tm + 1, tr, pos, offset + t[v].offset);
                }
            }
        }


        public static class Data {
            int offset;
        }
    }

}
