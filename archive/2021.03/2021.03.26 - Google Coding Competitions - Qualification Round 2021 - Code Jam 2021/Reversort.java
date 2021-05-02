package task;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Reversort {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(in.nextInt());
        }
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            int mmin = Integer.MAX_VALUE;
            int pos = 0;
            for (int h = i; h < n; h++) {
                if (mmin > a.get(h)) {
                    mmin = a.get(h);
                    pos = h;
                }
            }
            int t1 = i;
            int t2 = pos;
            while (t1 < t2) {
                int z = a.get(t1);
                a.set(t1, a.get(t2));
                a.set(t2, z);
                t1++;
                t2--;
            }
            ans += pos - i + 1;
        }
        out.println(String.format("Case #%d: %d", testNumber, ans));
    }
}
