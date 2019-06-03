package task;

import java.io.PrintWriter;
import java.util.LinkedList;

public class FIhabIBolshoiFinal {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Vertex[] vs = Vertex.emptyGraph(n);
        for (int i = 0; i < n - 1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            vs[from].adj.add(vs[to]);
            vs[to].adj.add(vs[from]);
        }

        int prev = 0;
        int cur = 0;
        while (true) {
            out.println(String.format("d %d", cur));
            out.flush();
            int d = in.nextInt();
            if (d == 0) {
                out.println(String.format("! %d", cur + 1));
                out.flush();
                return;
            }
            boolean[] visited = new boolean[n];
            int[] steps = new int[n];
            int[] from = new int[n];
            boolean[] possible = new boolean[n];
            visited[prev] = true;
            visited[cur] = true;
            steps[cur] = 0;
            from[cur] = cur;
            LinkedList<Integer> queue = new LinkedList<>();
            queue.add(cur);
            while (!queue.isEmpty()) {
                Vertex v = vs[queue.poll()];
                for (Vertex to : v.adj) {
                    if (visited[to.idx]) {
                        continue;
                    }
                    steps[to.idx] = steps[v.idx] + 1;
                    from[to.idx] = v.idx;
                    if (steps[to.idx] == d) {
                        int j = to.idx;
                        while (j != cur) {
                            possible[j] = true;
                            j = from[j];
                        }
                    }
                    queue.add(to.idx);
                }
            }


        }
    }
}
