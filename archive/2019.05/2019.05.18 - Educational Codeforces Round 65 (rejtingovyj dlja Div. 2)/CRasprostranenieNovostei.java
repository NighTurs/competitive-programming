package task;

import java.io.PrintWriter;

public class CRasprostranenieNovostei {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        DSU dsu = new DSU(n);
        for (int i = 0; i < m; i++) {
            int k = in.nextInt();
            int prev = -1;
            for (int h = 0; h < k; h++) {
                int val = in.nextInt() - 1;
                if (prev != -1) {
                    dsu.unionSets(prev, val);
                } else {
                    prev = val;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            out.print(dsu.size[dsu.findSet(i)]);
            out.print(' ');
        }
    }
}
