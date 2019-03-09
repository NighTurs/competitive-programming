package task;

import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int cnt1 = in.nextInt();
        int cnt2 = in.nextInt();
        int cnt3 = in.nextInt();
        int cnt4 = in.nextInt();
        if (cnt3 != 0) {
            if (cnt1 == 0 || cnt4 == 0) {
                out.println(0);
                return;
            }
            cnt1--;
            cnt4--;
        }
        if (cnt1 != cnt4) {
            out.println(0);
            return;
        }
        out.println(1);
    }
}
