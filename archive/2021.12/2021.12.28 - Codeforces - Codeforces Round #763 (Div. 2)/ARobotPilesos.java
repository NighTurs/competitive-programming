package task;

import java.io.PrintWriter;

public class ARobotPilesos {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int cx = in.nextInt();
        int cy = in.nextInt();
        int tx = in.nextInt();
        int ty = in.nextInt();
        int step = 0;
        int dx = 1;
        int dy = 1;
        while (true) {
            if (cx == tx || cy == ty) {
                out.println(step);
                return;
            }
            step++;
            if (cx == 1 && dx == -1) {
                dx = 1;
            }
            if (cy == 1 && dy == -1) {
                dy = 1;
            }
            if (cx == n && dx == 1) {
                dx = -1;
            }
            if (cy == m && dy == 1) {
                dy = -1;
            }
            cx += dx;
            cy += dy;
        }
    }
}
