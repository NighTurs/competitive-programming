package task;

import java.io.PrintWriter;

public class Transmutation {

    long[] debt;
    long[] count;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        Vertex[] vs = Vertex.emptyGraph(n);
        for (int i = 0; i < n; i++) {
            int r1 = in.nextInt() - 1;
            int r2 = in.nextInt() - 1;
            vs[i].adj.add(vs[r1]);
            vs[i].adj.add(vs[r2]);
        }

        count = new long[n];
        debt = new long[n];
        for (int i = 0; i < n; i++) {
            count[i] = in.nextInt();
        }

        long ans = count[0];
        count[0] = 0;

        long t1 = 0;
        long t2 = 100 * (long)10e9;

        while (t1 < t2) {
            long mid = (t1 + t2 + 1) / 2;
            System.arraycopy(count, 0, debt, 0, n);
            if (possible(vs[0], mid)) {
                t1 = mid;
            } else {
                t2 = mid - 1;
            }

        }
        out.println(String.format("Case #%d: %d", testNumber, ans + t1));
    }

    private boolean possible(Vertex v, long d) {
        if (debt[v.idx] < 0) {
            return false;
        }
        debt[v.idx] -= d;
        if (debt[v.idx] >= 0) {
            return true;
        }

        for (Vertex to : v.adj) {
            if (!possible(to, -debt[v.idx])) {
                return false;
            }
        }
        debt[v.idx] = 0;

        return true;
    }
}
