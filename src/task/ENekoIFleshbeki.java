package task;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ENekoIFleshbeki {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n - 1; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n - 1; i++) {
            b[i] = in.nextInt();
        }
        Map<Integer, Map<Integer, Integer>> mp = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            if (a[i] > b[i]) {
                out.println(-1);
                return;
            }
            mp.putIfAbsent(a[i], new HashMap<>());
            mp.putIfAbsent(b[i], new HashMap<>());
            mp.get(a[i]).putIfAbsent(b[i], 0);
            mp.get(b[i]).putIfAbsent(a[i], 0);
            mp.get(a[i]).put(b[i], mp.get(a[i]).get(b[i]) + 1);
            mp.get(b[i]).put(a[i], mp.get(b[i]).get(a[i]) + 1);
        }
        List<Integer> ans = EulerPath.eulerPath(mp);
        if (ans == null) {
            out.println(-1);
            return;
        }
        for (int i = 0; i < ans.size(); i++) {
            out.print(ans.get(i));
            out.print(' ');
        }
    }
}
