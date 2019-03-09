package task;

import java.io.PrintWriter;

public class AWhiteCells {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int nn = in.nextInt();
        int mm = in.nextInt();
        int ans = n * m;
        ans -= nn * m;
        ans -= mm * (n - nn);
        out.println(ans);
    }
}
