package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BPoMestam {
    long doit(int[] a, int st, int n) {

        List<List<Integer>> b = new ArrayList<>();
        b.add(new ArrayList<>());
        b.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            b.get(a[i]).add(i);
        }

        long ans = 0;
        int[] cur = {0, 0};
        for (int i = 0; i < n; i++) {

            if (b.get(st).size() == cur[st]) {
                return Long.MAX_VALUE;
            }
            ans += Math.abs(i - b.get(st).get(cur[st]));
            cur[st]++;
            st = Math.abs(st - 1);
        }
        return ans;
    }


    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            int val = in.nextInt();
            val = (val % 2 == 0 ? 0 : 1);
            a[i] = val;
        }

        long ans = doit(a, 0, n);
        ans = Math.min(ans, doit(a, 1, n));
        if (ans == Long.MAX_VALUE) {
            out.println(-1);
            return;
        }
        out.println(ans / 2);
    }
}
