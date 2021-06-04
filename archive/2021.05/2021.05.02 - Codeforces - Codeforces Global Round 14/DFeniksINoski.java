package task;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class DFeniksINoski {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int l = in.nextInt();
        int r = in.nextInt();

        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();

        int ctl = l;
        int ctr = r;
        for (int i = 0; i < l; i++) {
            int z = in.nextInt();
            left.put(z, left.getOrDefault(z, 0) + 1);
        }
        for (int i = 0; i < r; i++) {
            int z = in.nextInt();
            right.put(z, right.getOrDefault(z, 0) + 1);
        }

        for (Integer color : left.keySet()) {
            if (!right.containsKey(color)) {
                continue;
            }
            int ct = Math.min(right.get(color), left.get(color));
            left.put(color, left.get(color) - ct);
            right.put(color, right.get(color) - ct);
            ctl -= ct;
            ctr -= ct;
        }

        Map<Integer, Integer> res;
        int cmin = 0;
        int cmax = 0;
        if (ctl > ctr) {
            res = left;
            cmax = ctl;
            cmin = ctr;
        } else {
            res = right;
            cmax = ctr;
            cmin = ctl;
        }

        int ans = 0;
        for (Integer color : res.keySet()) {
            int ct = res.get(color);
            if (ct % 2 == 1) {
                ct--;
            }
            int take = Math.min(ct, cmax - cmin);
            if (take % 2 == 1) {
                take--;
            }
            ans += take / 2;
            cmax -= take;
        }

        ans += Math.min(cmax, cmin);
        ans += Math.max(cmax, cmin) - Math.min(cmax, cmin);
        out.println(ans);
    }
}
