package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FGrafBezDlinnihOrientirovannihPutei {

    boolean[] visited;
    int[] to;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        Vertex[] vs = Vertex.emptyGraph(n);
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            vs[a].adj.add(new Edge(i, a, vs[b]));
            vs[b].adj.add(new Edge(i, a, vs[a]));
        }

        visited = new boolean[n];
        to = new int[m];
        Arrays.fill(to, -1);

        Edge fe = vs[0].adj.get(0);
        to[fe.idx] = fe.to.idx;

        boolean result = true;
        result &= traverse(vs[0], true);
        result &= traverse(fe.to, false);

        if (result) {
            printResult(out, n, m, vs);
            return;
        }

        visited = new boolean[n];
        to = new int[m];
        Arrays.fill(to, -1);

        fe = vs[0].adj.get(0);
        to[fe.idx] = 0;

        result = true;
        result &= traverse(vs[0], false);
        result &= traverse(fe.to, true);

        if (result) {
            printResult(out, n, m, vs);
            return;
        }
        out.println("NO");
    }

    private boolean traverse(Vertex v, boolean outer) {
        if (visited[v.idx]) {
            return true;
        }
        visited[v.idx] = true;
        for (Edge e : v.adj) {
            int shouldTo = outer ? e.to.idx : v.idx;
            if (to[e.idx] != -1 && to[e.idx] != shouldTo) {
                return false;
            }
            to[e.idx] = shouldTo;
            if (!traverse(e.to, !outer)) {
                return false;
            }
        }
        return true;
    }

    public void printResult(PrintWriter out, int n, int m, Vertex[] vs) {
        out.println("YES");
        boolean[] bin = new boolean[m];
        for (int i = 0; i < n; i++) {
            for (Edge edge : vs[i].adj) {
                bin[edge.idx] = edge.first != to[edge.idx];
            }
        }
        for (int i = 0; i < m; i++) {
            out.print(bin[i] ? '0' : '1');
        }
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
    }

    public static class Edge {

        int idx;
        int first;
        Vertex to;

        public Edge(int idx, int first, Vertex to) {
            this.idx = idx;
            this.first = first;
            this.to = to;
        }
    }
}
