package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class DLostTree {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Vertex[] vs = Vertex.emptyGraph(n);
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while (!q.isEmpty()) {
            int i = q.poll();
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            out.println("? " + (i + 1));
            out.flush();
            Map<Integer, List<Integer>> d = new HashMap<>();

            for (int h = 0; h < n; h++) {
                int val = in.nextInt();
                if (!d.containsKey(val)) {
                    d.put(val, new ArrayList<>());
                }
                d.get(val).add(h);
            }
            Vertex cur = vs[i];
            for (int h = 1; h < n; h++) {
                List<Integer> tos = d.getOrDefault(h, Collections.emptyList());
                for (Integer to : tos) {
                    if (!visited[to]) {
                        cur.adj.add(to);
                    } else {
                        vs[to].adj.add(cur.idx);
                    }
                    visited[to] = true;
                }
                if (tos.size() > 1) {
                    if (d.containsKey(h + 1)) {
                        q.addAll(d.get(h + 1));
                    }
                    break;
                }
                cur = vs[tos.get(0)];
            }
        }
        out.println("!");
        out.flush();
        for (int i = 0; i < n; i++) {
            for (Integer to : vs[i].adj) {
                if (!vs[to].adj.contains(i) || i < to) {
                    out.println((i + 1) + " " + (to + 1));
                }
            }
        }
        out.flush();
    }
    public static class Vertex {
        int idx;
        Set<Integer> adj = new HashSet<>();

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
