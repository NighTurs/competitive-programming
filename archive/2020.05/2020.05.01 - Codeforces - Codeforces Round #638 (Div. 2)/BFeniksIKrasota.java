package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BFeniksIKrasota {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        for (int tt = 0; tt < t; tt++) {
            int n = in.nextInt();
            int k = in.nextInt();
            Set<Integer> s = new HashSet<>();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                s.add(a[i]);
            }
            if (s.size() > k) {
                out.println(-1);
                continue;
            }
            List<Integer> l = new ArrayList<>(s);
            while (l.size() < k) {
                l.add(l.get(0));
            }
            List<Integer> ans = new ArrayList<>();
            int curL = 0;
            int curA = 0;
            while (curA < n) {
                ans.add(l.get(curL));
                if (l.get(curL).equals(a[curA])) {
                    curA++;
                }
                curL++;
                if (curL >= l.size()) {
                    curL = 0;
                }
            }
            out.println(ans.size());
            for (Integer val : ans) {
                out.print(val + " ");
            }
            out.println();
        }
    }
}
