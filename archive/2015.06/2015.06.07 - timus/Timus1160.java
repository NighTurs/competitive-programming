package task;

import task.InputReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Timus1160 {
    final int N = (int) 1.e3;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt();
        Edge a[][] = new Edge[n][n];

        for (int i = 0; i < m; i++) {
            int j1, j2, t;
            j1 = in.nextInt() - 1; j2 = in.nextInt() - 1; t = in.nextInt();
            a[j1][j2] = new Edge(j1, j2, t); a[j2][j1] = new Edge(j2, j1, t);
        }
        boolean c[] = new boolean[n];
        Arrays.fill(c, false);
        c[0] = true;
        PriorityQueue<Edge> qe = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        ArrayDeque<Edge> ans = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (a[0][i] != null) {
                qe.add(a[0][i]);
            }
        }
        int cur = n - 1;
        int max = Integer.MIN_VALUE;
        while (cur > 0) {
            Edge e = null;
            do {
                e = qe.poll();
            } while (c[e.to]);
            max = Math.max(e.weight, max);
            ans.add(e);
            c[e.to] = true;
            for (int i = 0; i < n; i++) {
                if (a[e.to][i] != null && !c[i]) {
                    qe.add(a[e.to][i]);
                }
            }
            cur--;
        }
        out.println(max);
        out.println(ans.size());
        while (ans.size() > 0) {
            Edge e = ans.poll();
            out.println(e.from + 1 + " " + (e.to + 1));
        }
    }
    static class Edge {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
