package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class D2RassadkaVKinoteatreSlozhnayaVersiya {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Map<Integer, List<Integer>> byS = new HashMap<>();
        Set<Integer> st = new HashSet<>();
        for (int i = 0; i < n * m; i++) {
            int val = in.nextInt();
            st.add(val);
            byS.putIfAbsent(val, new ArrayList<>());
            byS.get(val).add(i);
        }

        List<Integer> sight = new ArrayList<>(st);
        sight.sort(Integer::compareTo);
        Map<Integer, Pair<Integer, Integer>> mp = new HashMap<>();


        int curI = 0;
        int curH = 0;
        for (int s : sight) {
            List<Integer> p = byS.get(s);

            int cur = 0;

            while (cur < p.size()) {
                int leftCol = m - curH;
                int leftP = p.size() - cur;
                int assign = Math.min(leftCol, leftP);
                for (int i = cur + assign - 1, h = 0; i >= cur; i--, h++) {
                    mp.put(p.get(i), Pair.of(curI, curH + h));
                }
                curH += assign;
                if (curH == m) {
                    curI+=1;
                    curH = 0;
                }
                cur += assign;
            }
        }

        boolean[][] c = new boolean[n][m];

        long ans = 0;

        for (int i = 0; i < n * m; i++) {
            Pair<Integer, Integer> p = mp.get(i);
            for (int h = 0; h < p.sc; h++) {
                if (c[p.fs][h]) {
                    ans++;
                }
            }
            c[p.fs][p.sc] = true;
        }
        out.println(ans);
    }
}
