package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class E2OptimizatsiyaMassivaDekom {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        List<Pair<Integer, Integer>> a = new ArrayList<>();
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            int val = in.nextInt();
            b[i] = val;
            a.add(Pair.of(val, i));
        }
        SegmentTree st = new SegmentTree(n);
        st.build(new int[n], 1, 0, n - 1);
        a.sort((x,y) -> x.fs == y.fs ? Integer.compare(x.sc, y.sc) : Integer.compare(x.fs, y.fs));
        Map<Integer, Integer> ct = new HashMap<>();
        long ans = 0;
        for (Pair<Integer, Integer> p : a) {
            int val = p.fs;
            int pos = p.sc;
            long same = ct.getOrDefault(val, 0);
            long taken = st.query(1, 0, n - 1, 0, pos).sum - same;
            long all = pos - same;
            long alt = all - taken;
            ans += Math.min(taken, alt);
            st.update(1, 0, n - 1, pos, 1);
            ct.putIfAbsent(val, 0);
            ct.put(val, ct.get(val) + 1);
        }
        out.println(ans);
    }
}
