package task;

import java.io.PrintWriter;

public class BDvaStola {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int W = in.nextInt();
        int H = in.nextInt();
        int x1 = in.nextInt();
        int y1 = in.nextInt();
        int x2 = in.nextInt();
        int y2 = in.nextInt();
        int w = in.nextInt();
        int h = in.nextInt();
        if (w + (x2 - x1) > W && h + (y2 - y1) > H) {
            out.println(-1);
            return;
        }
        int wm = Math.max(x1, W - x2);
        int hm = Math.max(y1, H - y2);


        int ans = Integer.MAX_VALUE;
        if (w + (x2 - x1) <= W) {
            ans = Math.min(ans, Math.max(0, w - wm));
        }
        if (h + (y2 - y1) <= H) {
            ans = Math.min(ans, Math.max(0, h - hm));
        }
        out.println(ans);
    }
}
