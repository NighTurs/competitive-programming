package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class DSotsialnayaSet {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        DSU d = new DSU(n);

        int[] a = new int[n];
        int left = 0;
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            if (d.findSet(from) == d.findSet(to)) {
                left++;
            } else {
                d.unionSets(from, to);
            }
            Arrays.fill(a, 0);
            for (int h = 0; h < n; h++) {
                a[d.findSet(h)]++;
            }
            long mmax = 0;
            Arrays.sort(a);
            for (int h = 0; h < left + 1; h++) {
                mmax += a[n - 1 - h];
            }
            out.println(mmax - 1);
        }
    }
}
