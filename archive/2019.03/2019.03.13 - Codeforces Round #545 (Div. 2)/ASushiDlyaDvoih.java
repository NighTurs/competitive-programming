package task;

import java.io.PrintWriter;

public class ASushiDlyaDvoih {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int curType = -1;
        int curSize = 0;
        int prevSize = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            if (curType == a) {
                curSize++;
            } else {
                prevSize = curSize;
                curType = a;
                curSize = 1;
            }
            if (ans < Math.min(prevSize, curSize) * 2) {
                ans = Math.min(prevSize, curSize) * 2;
            }
        }
        out.println(ans);
    }
}
