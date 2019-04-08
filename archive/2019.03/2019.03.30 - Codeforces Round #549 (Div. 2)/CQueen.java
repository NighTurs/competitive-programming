package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CQueen {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        boolean[] c = new boolean[n];

        Vertex[] vs = Vertex.emptyGraph(n);

        for (int i = 0; i < n; i++) {
            int p = in.nextInt() - 1;
            if (p != -2) {
                vs[p].adj.add(vs[i]);
            }
            int cc = in.nextInt();
            c[i] = cc == 1;
        }

        List<Integer> toDelete = new ArrayList<>();
        boolean[] taken = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!c[i] || taken[i]) {
                continue;
            }
            LinkedList<Integer> queue = new LinkedList<>();
            queue.add(i);
            taken[i] = true;
            while (!queue.isEmpty()) {
                int cur = queue.pop();
                int haters = 0;
                for (Vertex v : vs[cur].adj) {
                    if (c[v.idx]) {
                        haters++;
                        if (!taken[v.idx]) {
                            taken[v.idx] = true;
                            queue.add(v.idx);
                        }
                    }
                }
                if (haters == vs[cur].adj.size()) {
                    toDelete.add(cur);
                }
            }
        }

        if (toDelete.isEmpty()) {
            out.println(-1);
        }

        toDelete.sort(Integer::compare);
        for (int i = 0; i < toDelete.size(); i++) {
            out.print(toDelete.get(i) + 1);
            out.print(' ');
        }
    }
}
