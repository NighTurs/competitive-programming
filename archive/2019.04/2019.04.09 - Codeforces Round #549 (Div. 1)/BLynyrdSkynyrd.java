package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class BLynyrdSkynyrd {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();
        int[] p = new int[n];
        int[] a = new int[m];

        for (int i = 0; i < n; i++) {
            p[i] = in.nextInt() - 1;
        }
        int[] mp = new int[n];
        for (int i = 0; i < n - 1; i++) {
            mp[p[i]] = p[i + 1];
        }

        mp[p[n - 1]] = p[0];

        for (int i = 0; i < m; i++) {
            a[i] = in.nextInt() - 1;
        }

        int[] last = new int[n];
        Arrays.fill(last, -1);
        BinaryLifting.Vertex[] vs = BinaryLifting.Vertex.emptyGraph(m + 1);

        for (int i = m - 1; i >= 0; i--) {
            int next = mp[a[i]];
            if (last[next] != -1) {
                vs[last[next]].adj.add(vs[i]);
            } else {
                vs[m].adj.add(vs[i]);
            }
            last[a[i]] = i;
        }

        BinaryLifting binLift = new BinaryLifting(vs, vs[m]);

        int[] min = new int[m];
        for (int i = m - 1; i >= 0; i--) {
            BinaryLifting.Vertex parent = binLift.ithParent(vs[i], n - 1);
            if (i == m - 1) {
                min[i] = parent.idx;
            } else {
                min[i] = Math.min(parent.idx, min[i + 1]);
            }
        }

        for (int i = 0; i < q; i++) {
            int l = in.nextInt() - 1;
            int r = in.nextInt() - 1;
            if (min[l] <= r) {
                out.print('1');
            } else {
                out.print('0');
            }
        }
    }
}
