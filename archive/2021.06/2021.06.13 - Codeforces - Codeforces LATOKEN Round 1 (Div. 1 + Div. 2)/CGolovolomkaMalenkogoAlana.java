package task;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CGolovolomkaMalenkogoAlana {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            m.put(a[i], i);
        }
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }
        boolean[] c = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (c[i]) {
                continue;
            }
            c[i] = true;
            int first = a[i];
            int cur = i;
            while (b[cur] != first) {
                cur = m.get(b[cur]);
                c[cur] = true;
            }
            count++;
        }
        long ans = 1;
        for (int i = 0; i < count; i++) {
            ans = (ans * 2) % (long) (1e9 + 7);
        }
        out.println(ans);
    }
}
