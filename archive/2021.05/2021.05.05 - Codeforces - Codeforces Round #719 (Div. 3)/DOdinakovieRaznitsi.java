package task;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class DOdinakovieRaznitsi {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            int val = in.nextInt() - i;
            m.put(val, m.getOrDefault(val, 0) + 1);
        }
        long ans = 0;
        for (Integer key : m.keySet()) {
            long ct = m.get(key);
            ans += (1 + ct - 1) * (ct - 1) / 2;
        }
        out.println(ans);
    }
}
