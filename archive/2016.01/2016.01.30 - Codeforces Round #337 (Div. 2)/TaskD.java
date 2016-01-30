package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        List<Step> steps = new LinkedList<>();
        TreeSet<Integer> ys = new TreeSet<>();


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
            steps.add(new Step(true, xl, yl, yr));
            steps.add(new Step(false, xr + 1, yl, yr));
            ys.add(yl);
            ys.add(yr);
        }

        steps.sort((a, b) -> Integer.compare(a.x, b.x));
        SegmentTree st = new SegmentTree(ys.stream().collect(Collectors.toList()));
        long result = 0;
        int prevX = steps.get(0).x;
        while(!steps.isEmpty()) {
            Step step = steps.remove(0);
            result += (long) (step.x - prevX) * st.lookUp(1, ys.first(), ys.last());
            st.update(1, step.ly, step.ry, step.isIn ? 1 : -1);
            while (!steps.isEmpty() && steps.get(0).x == step.x) {
                step = steps.remove(0);
                st.update(1, step.ly, step.ry, step.isIn ? 1 : -1);
            }
            prevX = step.x;
        }

        out.print(result);
    }

    public static class SegmentTree {
        ArrayList<Pair<Integer, Integer>> ys;
        Node[] nodes;

        public SegmentTree(List<Integer> ys) {
            this.ys = new ArrayList<>();
            for (int i = 0; i < ys.size() - 1; i++) {
                this.ys.add(Pair.of(ys.get(i), ys.get(i)));
                if (ys.get(i) + 1 < ys.get(i + 1)) {
                    this.ys.add(Pair.of(ys.get(i) + 1, ys.get(i + 1) - 1));
                }
            }
            this.ys.add(Pair.of(ys.get(ys.size() - 1), ys.get(ys.size() - 1)));
            nodes = new Node[this.ys.size() * 4];
            buildTree(1, 0, this.ys.size() - 1);
        }

        private void buildTree(int v, int il, int ir) {
            nodes[v] = new Node(ys.get(il).fs, ys.get(ir).sc, 0, 0);
            if (il == ir) {
                return;
            } else {
                int  m = (il + ir) / 2;
                buildTree(v * 2, il, m);
                buildTree(v * 2 + 1, m + 1, ir);
            }
        }

        private long lookUp(int v, int yl, int yr) {
            if (yl > yr) {
                return 0;
            }
            if (nodes[v].yl == yl && nodes[v].yr == yr) {
                return nodes[v].inRes();
            }
            return lookUp(v * 2, yl, Math.min(nodes[v * 2].yr, yr)) +
                    lookUp(v * 2 + 1, Math.max(yl, nodes[v * 2 + 1].yl), yr);
        }

        private long update(int v, int yl, int yr, int upd) {
            if (yl > yr) {
                return nodes[v].inRes();
            }
            if (nodes[v].yl == yl && nodes[v].yr == yr) {
                nodes[v].count += upd;
                return nodes[v].inRes();
            }
            nodes[v].outRes = update(v * 2, yl, Math.min(nodes[v * 2].yr, yr), upd) +
                    update(v * 2 + 1, Math.max(yl, nodes[v * 2 + 1].yl), yr, upd);
            return nodes[v].inRes();
        }

        private static class Node {
            int yl;
            int yr;
            int count;
            long outRes;

            public Node(int yl, int yr, int count, int outRes) {
                this.yl = yl;
                this.yr = yr;
                this.count = count;
                this.outRes = outRes;
            }

            public long inRes() {
                if (count > 0) {
                    return yr - yl + 1;
                } else {
                    return outRes;
                }
            }
        }
    }

    public static class Step {
        boolean isIn;
        int x;
        int ly, ry;

        public Step(boolean isIn, int x, int ly, int ry) {
            this.isIn = isIn;
            this.x = x;
            this.ly = ly;
            this.ry = ry;
        }
    }
}
