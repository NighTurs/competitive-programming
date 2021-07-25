package task;

import java.io.PrintWriter;

public class DMassivKakRaznost {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        boolean[] taken = new boolean[n];
        if (doit(0, 0, taken, a)) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    private boolean doit(int pos, int sum, boolean[] taken, int[] a) {
        if (pos == a.length) {
            for (int i = 0; i < a.length; i++) {
                if (a[i] == sum && !taken[i]) {
                    return true;
                }
            }
            return false;
        }
        boolean res = false;
        res |= doit(pos + 1, sum, taken, a);
        taken[pos] = true;
        res |= doit(pos + 1, sum + a[pos], taken, a);
        res |= doit(pos + 1, sum - a[pos], taken, a);
        taken[pos] = false;
        return res;
    }
}
