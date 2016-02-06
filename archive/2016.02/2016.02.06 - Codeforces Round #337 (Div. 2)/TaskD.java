package task;

import task.InputReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        TreeSet<Integer> tys = new TreeSet<>();
        List<Step> steps = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int xl = in.nextInt();
            int yl = in.nextInt();
            int xr = in.nextInt();
            int yr = in.nextInt();
            if (xl > xr) {
                int z = xl;
                xl = xr;
                xr = z;
            }
            if (yl > yr) {
                int z = yl;
                yl = yr;
                yr = z;
            }
            tys.add(yl);
            tys.add(yr);
            steps.add(new Step(xl - 1, true, yl, yr));
            steps.add(new Step(xr, false, yl, yr));
        }
        steps.sort((a, b) -> Integer.compare(a.x, b.x));
        List<Integer> ys = tys.stream().collect(Collectors.toList());

        List<Comp> comps = new ArrayList<>();
        Map<Integer, Integer> yLookup = new HashMap<>();
        for (int i = 0; i < ys.size() - 1; i++) {
            comps.add(new Comp(ys.get(i), ys.get(i)));
            yLookup.put(ys.get(i), comps.size() - 1);
            if (ys.get(i + 1) - ys.get(i) > 1) {
                comps.add(new Comp(ys.get(i) + 1, ys.get(i + 1) - 1));
            }
        }
        comps.add(new Comp(ys.get(ys.size() - 1), ys.get(ys.size() - 1)));
        yLookup.put(ys.get(ys.size() - 1), comps.size() - 1);

        SegmentTree tree = new SegmentTree(comps);

        long sum = 0;
        int prev = steps.get(0).x;
        int i = 0;
        while (steps.size() > i) {
            Step cur = steps.get(i);
            sum += (long) (cur.x - prev) * tree.lookup();
            do {
                cur = steps.get(i);
                tree.update(1, 0, comps.size() - 1, yLookup.get(cur.yl), yLookup.get(cur.yr), cur.isAdd ? 1 : -1);
                i++;
            } while (i < steps.size() && steps.get(i).x == cur.x);
            prev = cur.x;
        }

        out.print(sum);
    }

    public static class SegmentTree {

        private List<Node> tree;
        private List<Comp> coords;

        public SegmentTree(List<Comp> coords) {
            this.coords = coords;
            tree = new ArrayList<>(coords.size() * 4 + 1);
            for (int i = 0; i < coords.size() * 4 + 1; i++) {
                tree.add(new Node());
            }
            build(1, 0, coords.size() - 1);
        }

        private void build(int p, int l, int r) {
            tree.get(p).size = coords.get(r).to - coords.get(l).from + 1;
            tree.get(p).valueIn = 0;
            tree.get(p).valueOut = 0;
            if (l == r) {
                return;
            }
            int m = (l + r) / 2;
            build(p * 2, l, m);
            build(p * 2 + 1, m + 1, r);
        }

        public int lookup() {
            return tree.get(1).value();
        }

        public int update(int p, int l, int r, int ul, int ur, int val) {
            if (ul > ur) {
                return tree.get(p).value();
            }
            if (l == ul && r == ur) {
                tree.get(p).valueIn += val;
                return tree.get(p).value();
            }
            int m = (l + r) / 2;
            tree.get(p).valueOut = update(p * 2, l, m, ul, Math.min(m, ur), val) +
                    update(p * 2 + 1, m + 1, r, Math.max(m + 1, ul), ur, val);
            return tree.get(p).value();
        }

        public static class Node {
            int size;
            int valueIn, valueOut;

            public int value() {
                if (valueIn > 0) {
                    return size;
                } else {
                    return valueOut;
                }
            }
        }
    }

    public static class Comp {
        int from, to;

        public Comp(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public static class Step {
        int x;
        boolean isAdd;
        int yl, yr;

        public Step(int x, boolean isAdd, int yl, int yr) {
            this.x = x;
            this.isAdd = isAdd;
            this.yl = yl;
            this.yr = yr;
        }
    }
}
