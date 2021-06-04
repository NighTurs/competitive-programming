package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CFeniksIBashni {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        for (int t = 0; t < tt; t++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int x = in.nextInt();
            List<Pair<Integer, Integer>> a = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                a.add(Pair.of(i, in.nextInt()));
            }
            a.sort(Comparator.comparingInt(aa -> aa.sc));

            int[] ma = new int[m];
            for (int i = 0; i < n; i++) {
                ma[i % m] += a.get(i).sc;
            }
            int mmin = Integer.MAX_VALUE;
            int mmax = Integer.MIN_VALUE;
            for (int i = 0; i < m; i++) {
                mmin = Math.min(ma[i], mmin);
                mmax = Math.max(ma[i], mmax);
            }
            if (mmax - mmin > x) {
                out.println("NO");
                continue;
            }
            out.println("YES");
            int[] pos = new int[n];
            for (int i = 0; i < n; i++) {
                pos[a.get(i).fs] = i % m + 1;

            }
            for (int i = 0; i < n; i++) {
                out.print(pos[i] + " ");
            }
            out.println();
        }
    }
}
