package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DPolnoeZerkalo {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        Vertex[] vs = Vertex.emptyGraph(n);
        for (int i = 0; i < n - 1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            vs[from].adj.add(vs[to]);
            vs[to].adj.add(vs[from]);
        }

        int[] dist = bfs(Collections.singletonList(vs[0]), vs, false);
        Vertex v1 = vs[maxDist(dist, vs)];
        dist = bfs(Collections.singletonList(v1), vs, false);
        Vertex v2 = vs[maxDist(dist, vs)];
        int[] dist2 = bfs(Collections.singletonList(v2), vs, false);

        List<Vertex> cands = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if ((dist[i] == dist[v2.idx] / 2 || dist[i] == (dist[v2.idx] + 1) / 2) && (dist2[i] == dist[v2.idx] / 2
                    || dist2[i] == (dist[v2.idx] + 1) / 2)) {
                cands.add(vs[i]);
            }
        }
        if (cands.size() > 1) {
            if (cands.get(0).adj.size() > cands.get(1).adj.size()) {
                cands.remove(0);
            } else if (cands.get(0).adj.size() < cands.get(1).adj.size()) {
                cands.remove(1);
            }
        }

        dist = bfs(cands, vs, true);
        int leafIdx = uniqueLeaf(dist, vs);

        Vertex candRoot = cands.get(0);
        if (leafIdx != -1) {
            candRoot = vs[leafIdx];
        }
        dist = bfs(Collections.singletonList(candRoot), vs, false);
        int[] rank = new int[n];
        Arrays.fill(rank, -1);
        for (int i = 0; i < n; i++) {
            int d = dist[i];
            if (rank[d] == -1) {
                rank[d] = vs[i].adj.size();
            }
            if (rank[d] != vs[i].adj.size()) {
                out.println(-1);
                return;
            }
        }
        out.println(candRoot.idx + 1);
    }

    private int maxDist(int[] dist, Vertex[] vs) {
        int max = -1;
        int idx = -1;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] > max) {
                max = dist[i];
                idx = i;
            }
        }
        return idx;
    }

    private int uniqueLeaf(int[] dist, Vertex[] vs) {

        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < dist.length; i++) {
            if (vs[i].adj.size() != 1) {
                continue;
            }
            mp.putIfAbsent(dist[i], 0);
            mp.put(dist[i], mp.get(dist[i]) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            if (entry.getValue() == 1) {
                for (int i = 0; i < dist.length; i++) {
                    if (vs[i].adj.size() != 1) {
                        continue;
                    }
                    if (dist[i] == entry.getKey()) {
                        return i;
                    }
                }
            }
        }
        for (int i = 0; i < dist.length; i++) {
            if (vs[i].adj.size() != 1 || dist[i] == -1) {
                continue;
            }
            return i;
        }
        return -1;
    }

    private int[] bfs(List<Vertex> vv, Vertex[] vs, boolean onlyDirect) {
        boolean[] visited = new boolean[vs.length];
        int[] dist = new int[vs.length];
        Arrays.fill(dist, -1);
        LinkedList<Vertex> queue = new LinkedList<>();
        for (Vertex v : vv) {
            queue.add(v);
            dist[v.idx] = 0;
            visited[v.idx] = true;
        }
        while (!queue.isEmpty()) {
            Vertex cur = queue.pop();
            if (onlyDirect && dist[cur.idx] > 0) {
                int opts = 0;
                for (Vertex to : cur.adj) {
                    if (!visited[to.idx]) {
                        opts++;
                    }
                }
                if (opts > 1) {
                    continue;
                }
            }
            for (Vertex to : cur.adj) {
                if (visited[to.idx]) {
                    continue;
                }
                dist[to.idx] = dist[cur.idx] + 1;
                visited[to.idx] = true;
                queue.add(to);
            }
        }
        return dist;
    }
}
