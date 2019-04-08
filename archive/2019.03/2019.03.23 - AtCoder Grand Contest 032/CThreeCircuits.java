package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CThreeCircuits {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Vertex[] vs = Vertex.emptyGraph(n);

        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            Edge e = new Edge(vs[from], vs[to]);
            vs[from].adj.add(e);
            vs[to].adj.add(e);
        }

        int color = 0;
        for (int i = 0; i < n; i++) {
            for (Edge e : vs[i].adj) {
                if (e.color != -1) {
                    continue;
                }
                e.color = color++;
                boolean success = doit(e, null, vs);
                if (!success) {
                    out.println("No");
                    return;
                }
            }
        }
        if (color != 3) {
            out.println("No");
            return;
        }
        for (int i = 0; i < n; i++) {
            for (Edge e : vs[i].adj) {
                if (e.color == -1) {
                    out.println("No");
                    return;
                }
            }
        }
        out.println("Yes");
    }

    private boolean doit(Edge e, Edge from, Vertex[] vs) {

        int count = 0;
        boolean looped = false;

        for (Edge to : e.to.adj) {
            if (to.color == -1) {
                count++;
            }
            if (to.color == e.color && to != e && to != from) {
                return true;
            }
        }

        if (count == 1) {
            for (Edge to : e.to.adj) {
                if (to.color == -1) {
                    to.color = e.color;
                    looped |= doit(to, e, vs);
                }
            }
        }

        count = 0;
        for (Edge to : e.from.adj) {
            if (to.color == -1) {
                count++;
            }
            if (to.color == e.color && to != e && to != from) {
                return true;
            }
        }

        if (count == 1) {
            for (Edge to : e.from.adj) {
                if (to.color == -1) {
                    to.color = e.color;
                    looped |= doit(to, e, vs);
                }
            }
        }

        return looped;
    }

    public static class Vertex {

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

        public Vertex to(Edge e) {
            //noinspection ObjectEquality
            return e.to == this ? e.from : e.to;
        }
    }

    public static class Edge {
        Vertex from;
        Vertex to;
        int color = -1;

        public Edge(Vertex from, Vertex to) {
            this.from = from;
            this.to = to;
        }
    }
}
