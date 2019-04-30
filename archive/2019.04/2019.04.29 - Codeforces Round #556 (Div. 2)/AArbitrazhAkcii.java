package task;

import java.io.PrintWriter;

public class AArbitrazhAkcii {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int r = in.nextInt();

        int minBuy = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            minBuy = Math.min(in.nextInt(), minBuy);
        }
        int maxSell = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            maxSell = Math.max(in.nextInt(), maxSell);
        }
        out.println(Math.max((r / minBuy) * maxSell + r % minBuy, r));
    }
}
