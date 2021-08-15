package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class D1MochaIDianaProstayaVersiya {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int ma = in.nextInt();
        int mb = in.nextInt();
        DSU a = new DSU(n);
        DSU b = new DSU(n);
        for (int i = 0; i < ma; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            a.unionSets(from, to);
        }
        for (int i = 0; i < mb; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            b.unionSets(from, to);
        }


        List<Pair<Integer, Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < n; h++) {
                if (i == h) {
                    continue;
                }
                if (a.findSet(i) == a.findSet(h)) {
                    continue;
                }
                if (b.findSet(i) == b.findSet(h)) {
                    continue;
                }
                a.unionSets(i, h);
                b.unionSets(i, h);
                ans.add(Pair.of(i + 1, h + 1));
            }
        }

        out.println(ans.size());
        for (Pair<Integer, Integer> p : ans) {
            out.println(p.fs + " " + p.sc);
        }
    }
}
