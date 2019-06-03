package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DDekodirovanieChislovihPosledovatelnostei {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int m = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            if (a[i] == -1) {
                m++;
            }
        }
        out.println(m);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            ans.add(new ArrayList<>());
        }
        int[] next = new int[m];
        for (int i = 0; i < m; i++) {
            next[i] = i + 1;
        }
        next[m - 1] = 0;

        int h = 0;
        int prev = m - 1;
        for (int i = 0; i < n; i++) {
            ans.get(h).add(a[i]);
            if (a[i] == -1) {
                next[prev] = next[h];
            } else {
                prev = h;
            }
            h = next[h];
        }

        for (int i = 0; i < m; i++) {
            out.print(ans.get(i).size() - 1);
            for (Integer val : ans.get(i)) {
                if (val == -1) {
                    continue;
                }
                out.print(' ');
                out.print(val);
            }
            out.println();
        }
    }
}
