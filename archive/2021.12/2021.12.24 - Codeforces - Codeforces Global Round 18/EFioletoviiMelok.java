package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class EFioletoviiMelok {
    public Vertex init(Vertex root, int depth, Vertex from) {
        root.depth = depth;
        root.parent = from;
        int max = Integer.MIN_VALUE;
        if (root.adj.size() == 1 && root.idx != 0) {
            root.max = 0;
            root.vMax = root;
            return root;
        } else {
            Vertex vMax = null;
            for (Vertex to : root.adj) {
                if (to.depth != 0 || to.idx == 0) {
                    continue;
                }
                Vertex cand = init(to, depth + 1, root);
                if (max < cand.depth) {
                    max = cand.depth;
                    vMax = cand;
                }
            }
            root.max = max - root.depth;
            root.vMax = vMax;
            return vMax;
        }
    }

    public int count(Vertex root) {
        int count = 1;
        for (Vertex to : root.adj) {
            if (to == root.parent) {
                continue;
            }
            count += count(to);
        }
        return count;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int kOrig = k;

        Vertex[] vs = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new Vertex();
            vs[i].idx = i;
        }

        for (int i = 0; i < n - 1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            vs[from].adj.add(vs[to]);
            vs[to].adj.add(vs[from]);
        }
        init(vs[0], 0, null);
        PriorityQueue<Vertex> q = new PriorityQueue<>((a, b) -> -Integer.compare(a.max, b.max));
        for (Vertex v : vs[0].adj) {
            q.add(v);
        }


        long red = 0;
        while (k > 0 && q.size() > 0) {
            red++;
            k--;
            Vertex prev = q.poll();
            prev = prev.vMax;
            prev.removed = true;
            Vertex to = prev.parent;
            while (to != null && !to.removed && to.idx != 0) {
                for (Vertex v : to.adj) {
                    if (v == prev || v == to.parent) {
                        continue;
                    }
                    q.add(v);
                }
                to.removed = true;
                prev = to;
                to = prev.parent;
            }
        }
        int sq = n / 2;
        if (red < sq && red < kOrig) {
            red = Math.min(kOrig, sq);
        }

        long blue = 0;
        while (!q.isEmpty()) {
            Vertex v = q.poll();
            blue += count(v);
        }

        out.println((n - blue - red) * (red - blue));
    }

    public class Vertex {
        int idx;
        int depth;
        int max;
        boolean removed;
        Vertex vMax;
        Vertex parent;
        List<Vertex> adj = new ArrayList<>();
    }

}
