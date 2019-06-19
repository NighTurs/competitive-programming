package task;

import java.io.PrintWriter;

public class AKanatohodci {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int[] a = new int[3];
        a[0] = in.nextInt();
        a[1] = in.nextInt();
        a[2] = in.nextInt();
        ArrayUtils.sort(a);
        int d = in.nextInt();
        long ans = 0;
        if (a[2] - a[1] < d) {
            ans += d - (a[2] - a[1]);
        }
        if (a[1] - a[0] < d) {
            ans += d - (a[1] - a[0]) ;
        }
        out.println(ans);

    }
}
