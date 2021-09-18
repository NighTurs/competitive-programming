package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class AKniga {

    int[] mem = new int[3 * (int) 1e5];

    public int doit(int cur, Vertex[] vs) {
        if (mem[cur] >= 0) {
            return mem[cur];
        }
        if (mem[cur] == -2) {
            return -1;
        }
        mem[cur] = -2;
        int max = 0;
        for (Vertex v : vs[cur].adj) {
            int diff = v.idx > cur ? 1 : 0;
            int ans = doit(v.idx, vs);
            if (ans == -1) {
                return ans;
            }
            max = Math.max(max, ans + diff);
        }
        mem[cur] = max;
        return mem[cur];
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        mem = new int[n];
        Arrays.fill(mem, -1);

        Vertex[] vs = Vertex.emptyGraph(n);
        for (int i = 0; i < n; i++) {
            int k = in.nextInt();
            for (int h = 0; h < k; h++) {
                int to = in.nextInt();
                vs[i].adj.add(vs[to - 1]);
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            int ans = doit(i, vs);
            if (ans == -1) {
                out.println(-1);
                return;
            }
            max = Math.max(max, ans);
        }
        out.println(max + 1);

    }
}
