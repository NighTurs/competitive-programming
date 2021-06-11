package task;

import java.io.PrintWriter;

public class AChestniiPleiOff {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int[] a = new int[4];
        int[] b = new int[4];
        for (int i = 0; i < 4; i++) {
            a[i] = in.nextInt();
            b[i] = a[i];
        }
        ArrayUtils.sort(a);
        if ((a[2] > b[0] && a[2] > b[1] && a[3] > b[0] && a[3] > b[1]) ||
                (a[2] > b[2] && a[2] > b[3] && a[3] > b[2] && a[3] > b[3]))  {
            out.println("NO");
        } else {
            out.println("YES");
        }

    }
}
