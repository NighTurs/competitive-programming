package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DServalIPodveshennoeDerevo {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        Vertex[] vs = Vertex.emptyGraph(n);
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            if (a == 0) {
                vs[i].isMin = true;
            } else {
                vs[i].isMin = false;
            }
        }

        for (int i = 1; i < n; i++) {
            int parent = in.nextInt() - 1;
            vs[parent].adj.add(vs[i]);
        }

        vs[0].leafs = countLeafs(vs[0]);

        out.println(vs[0].leafs - doit(vs[0]));
    }

    private int doit(Vertex v) {
        if (v.leafs == 1) {
            return 0;
        }
        if (!v.isMin) {
            int min = Integer.MAX_VALUE;
            for (Vertex to : v.adj) {
                min = Math.min(doit(to), min);
            }
            return min;
        } else {
            int sum = v.adj.size() - 1;
            for (Vertex to : v.adj) {
                sum += doit(to);
            }
            return sum;
        }
    }

    private int countLeafs(Vertex v) {
        int sum = 0;
        for (Vertex to : v.adj) {
            sum += countLeafs(to);
        }
        if (sum == 0) {
            v.leafs = 1;
        } else {
            v.leafs = sum;
        }
        return v.leafs;
    }

    public static class Vertex {
        int idx;
        boolean isMin;
        int leafs;
        List<Vertex> adj = new ArrayList<>();

        public static Vertex[] emptyGraph(int n) {
            Vertex[] vs = new Vertex[n];
            for (int i = 0; i < n; i++) {
                vs[i] = new Vertex();
                vs[i].idx = i;
            }
            return vs;
        }
    }

}
