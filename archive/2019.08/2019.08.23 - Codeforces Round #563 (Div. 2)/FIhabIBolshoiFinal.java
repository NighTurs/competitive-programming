package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class FIhabIBolshoiFinal {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Vertex[] vs = Vertex.emptyGraph(n);
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].adj.add(vs[v]);
            vs[v].adj.add(vs[u]);
        }

        out.println(String.format("d %d", 1));
        out.flush();
        int d0 = in.nextInt();

        boolean[] passable = new boolean[n];
        Arrays.fill(passable, true);
        Vertex prev = vs[0];
        Vertex st = vs[0];

        int[] rank = new int[n];
        while (true) {
            int[] size = new int[n];
            dfs(prev, st, rank[st.idx], passable, rank, size);

            for (Vertex cur : vs) {
                if (size[cur.idx] == 0) {
                    passable[cur.idx] = false;
                }
            }

            int sz = 1;
            for (Vertex to : st.adj) {
                if (to == prev || !passable[to.idx]) {
                    continue;
                }
                sz += size[to.idx];
            }

            Vertex centroid = null;
            for (Vertex cur : vs) {
                if (!passable[cur.idx]) {
                    continue;
                }
                boolean isCentroid = true;
                int all = 0;
                for (Vertex to : cur.adj) {
                    if (rank[to.idx] < rank[cur.idx] || !passable[to.idx]) {
                        continue;
                    }
                    isCentroid &= size[to.idx] <= sz / 2;
                    all += size[to.idx];
                }
                isCentroid &= (sz - all - 1) <= sz / 2;
                if (isCentroid) {
                    centroid = cur;
                    break;
                }
            }

            out.println(String.format("d %d", centroid.idx + 1));
            out.flush();
            int d = in.nextInt();
            if (d == 0) {
                out.println(String.format("! %d", centroid.idx + 1));
                out.flush();
                return;
            }
            if (d0 == d + rank[centroid.idx]) {
                out.println(String.format("s %d", centroid.idx + 1));
                out.flush();
                prev = centroid;
                st = vs[in.nextInt() - 1];
                if (d == 1) {
                    out.println(String.format("! %d", st.idx + 1));
                    out.flush();
                    return;
                }
            } else {
                passable[centroid.idx] = false;
            }
        }
    }

    void dfs(Vertex prev, Vertex cur, int r, boolean[] passable, int[] rank, int size[]) {
        rank[cur.idx] = r;
        size[cur.idx] = 1;
        for (Vertex to : cur.adj) {
            if (to == prev || !passable[to.idx]) {
                continue;
            }
            dfs(cur, to, r + 1, passable, rank, size);
            size[cur.idx] += size[to.idx];
        }
    }
}
