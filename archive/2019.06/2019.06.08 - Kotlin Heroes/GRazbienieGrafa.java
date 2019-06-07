package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GRazbienieGrafa {

    List<List<Integer>> ans = new ArrayList<>();

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

        for (int i = 0; i < n; i++) {
            doit(vs[i], null, out);
            for (Edge e : vs[i].adj) {
                if (!e.taken) {
                    out.println("NO");
                    return;
                }
            }
        }

        out.println("YES");
        out.println(ans.size());
        for (List<Integer> l : ans) {
            out.print(l.size());
            out.print(' ');
            for (Integer v : l) {
                out.print(v + 1);
                out.print(' ');
            }
            out.println();
        }
    }

    private Vertex doit(Vertex v, Edge used, PrintWriter out) {
        v.visited = true;
        while (!v.adj.isEmpty() &&  v.adj.peekFirst().taken) {
            v.adj.pollFirst();
        }
        for (Edge e : v.adj) {
            if (e.taken || used == e) {
                continue;
            }
            Vertex to = e.to(v);
            if (to.visited) {
                ans.add(new ArrayList<>());
                ans.get(ans.size() - 1).add(to.idx);
                ans.get(ans.size() - 1).add(v.idx);
                e.taken = true;
                if (v != to) {
                    v.visited = false;
                    return to;
                }
            } else {
                Vertex st = doit(to, e, out);
                if (st == null) {
                    return null;
                }
                ans.get(ans.size() - 1).add(v.idx);
                e.taken = true;
                if (v != st) {
                    v.visited = false;
                    return st;
                }
            }
        }
        v.visited = false;
        return null;
    }

    public static class Vertex {

        int idx;
        boolean visited = false;
        LinkedList<Edge> adj = new LinkedList<>();

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

        Vertex a, b;
        boolean taken = false;

        public Edge(Vertex a, Vertex b) {
            this.a = a;
            this.b = b;
        }

        Vertex to(Vertex from) {
            //noinspection ObjectEquality
            return from == a ? b : a;
        }
    }
}
