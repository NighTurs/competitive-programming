package task;

import java.io.PrintWriter;

public class BINeNol {
    int doit(int v, int pow) {
        v += 1;
        int d = v / pow;
        int ans = ((d + 1) / 2) * pow;
        if (d % 2 == 0) {
            ans += v % pow;
        }
        return ans;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int l = in.nextInt();
        int r = in.nextInt();
        int min = Integer.MAX_VALUE;
        for (int p = 1; p <= l || p <= r; p *= 2) {
            int f = doit(r, p);
            int s = doit(l - 1, p);
            int ans = f - s;
            min = Math.min(min, ans);
        }
        out.println(min);
    }
}
