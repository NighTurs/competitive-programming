package task;

import java.io.PrintWriter;

public class AIgraVZhizn {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        String s = in.next();
        boolean[] a = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                a[i] = true;
            }
        }
        boolean[] b = new boolean[n];
        System.arraycopy(a, 0, b, 0, n);
        for (int t = 0; t < Math.min(n, m); t++) {
            for (int it = 0; it < n; it++) {
                if (a[it]) {
                    continue;
                }
                int nb = 0;
                if (it != 0 && a[it - 1]) {
                    nb++;
                }
                if (it != n - 1 && a[it + 1]) {
                    nb++;
                }
                if (nb == 1) {
                    b[it] = true;
                }
            }
            System.arraycopy(b, 0, a, 0, n);
        }
        for (int i = 0; i < n; i++) {
            if (a[i]) {
                out.print(1);
            } else {
                out.print(0);
            }
        }
        out.println();
    }
}
