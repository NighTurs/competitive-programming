package task;

import java.io.PrintWriter;

public class APostroitePryamougolnik {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int[] a = new int[3];
        for (int i = 0; i < 3; i++) {
            a[i] = in.nextInt();
        }
        ArrayUtils.sort(a);
        if (doit(a, 0, 1, 2)) {
            out.println("YES");
            return;
        }
        if (doit(a, 1, 2, 0)) {
            out.println("YES");
            return;
        }
        if (a[2] - a[1] == a[0]) {
            out.println("YES");
            return;
        }
        out.println("NO");
    }

    private boolean doit(int[] a, int b, int c, int d) {
        return a[b] == a[c] && a[d] % 2 == 0;
    }
}
