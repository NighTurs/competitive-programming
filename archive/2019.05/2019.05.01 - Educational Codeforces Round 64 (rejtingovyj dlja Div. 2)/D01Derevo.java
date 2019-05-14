package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class D01Derevo {
    long ans = 0;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Vertex[] vs = Vertex.emptyGraph(n);
        for (int i = 1; i < n; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int color = in.nextInt();
            vs[a].adj.add(new Edge(vs[b], color));
            vs[b].adj.add(new Edge(vs[a], color));
        }
        ans = 0;
        doit(vs[0], null);
        out.println(ans);
    }

    private void doit(Vertex v, Vertex prev) {
        for (Edge e : v.adj) {
            if (e.to == prev) {
                continue;
            }
            doit(e.to, v);
        }
        long ones = 0;
        long zeros = 0;
        long zeroOnes = 0;
        long oneZeros = 0;
        for (Edge e : v.adj) {
            if (e.to == prev) {
                continue;
            }
            if (e.color == 1) {
                ones += e.to.ones + 1;
                zeroOnes += e.to.zeroOnes + e.to.zeros;
            } else {
                zeros += e.to.zeros + 1;
                oneZeros += e.to.oneZeros + e.to.ones;
            }
        }
        v.ones = ones;
        v.zeros = zeros;
        v.zeroOnes = zeroOnes;
        v.oneZeros = oneZeros;

        ans += 2 * ones + 2 * zeros + zeroOnes + oneZeros;
        for (Edge e : v.adj) {
            if (e.to == prev) {
                continue;
            }
            if (e.color == 1) {
                ans += (e.to.ones + e.to.zeroOnes + e.to.zeros + 1) * (ones - e.to.ones - 1);
            } else {
                ans += (e.to.zeros + 1) * (zeros - e.to.zeros - 1 + oneZeros - e.to.oneZeros - e.to.ones + ones);
            }
        }
    }

    public static class Vertex {
        long ones = -1;
        long zeros = -1;
        long zeroOnes = -1;
        long oneZeros = -1;
        int idx;
        List<Edge> adj = new ArrayList<>();

        public static Vertex[] emptyGraph(int n) {
            Vertex[] vs = new Vertex[n];
            for (int i = 0; i < n; i++) {
                vs[i] = new Vertex();
                vs[i].idx = i;
            }
            return vs;
        }
    }

    public static class Edge {
        Vertex to;
        int color;

        public Edge(Vertex to, int color) {
            this.to = to;
            this.color = color;
        }
    }

}
