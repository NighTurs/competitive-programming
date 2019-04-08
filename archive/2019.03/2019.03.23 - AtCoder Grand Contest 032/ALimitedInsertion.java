package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ALimitedInsertion {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(in.nextInt());
        }
        int[] ans = new int[n];
        for (int t = n - 1; t >= 0; t--) {
            boolean found = false;
            for (int i = a.size() - 1; i >= 0; i--) {
                if (a.get(i) == i + 1) {
                    ans[t] = a.get(i);
                    a.remove(i);
                    found = true;
                    break;
                }
            }
            if (!found) {
                out.println(-1);
                return;
            }
        }
        for (int i = 0; i < n; i++) {
            out.print(ans[i]);
            out.print(' ');
        }
    }
}
