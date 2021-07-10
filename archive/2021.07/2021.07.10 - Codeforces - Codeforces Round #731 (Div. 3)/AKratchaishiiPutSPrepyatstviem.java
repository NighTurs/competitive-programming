package task;

import java.io.PrintWriter;

public class AKratchaishiiPutSPrepyatstviem {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int ax = in.nextInt();
        int ay = in.nextInt();
        int bx = in.nextInt();
        int by = in.nextInt();
        int fx = in.nextInt();
        int fy = in.nextInt();
        int def = Math.abs(ax - bx) + Math.abs(ay - by);
        if (fx == ax && ax == bx && Math.abs(fy - ay) + Math.abs(fy - by) == Math.abs(ay - by)) {
            def += 2;
        } else if (fy == ay && ay == by && Math.abs(fx - ax) + Math.abs(fx - bx) == Math.abs(ax - bx)) {
            def += 2;
        }
        out.println(def);
    }
}
