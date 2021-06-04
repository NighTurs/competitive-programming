package task;

import java.io.PrintWriter;

public class BFeniksIPazl {

    public static boolean pow2(int x) {
        int xx = (int) Math.sqrt(x);
        return xx * xx == x;
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        for (int t = 0; t < tt; t++) {
            int n = in.nextInt();
            if (n % 2 != 0) {
                out.println("NO");
                continue;
            }
            if (pow2(n / 2)) {
                out.println("YES");
                continue;
            }
            if (n % 4 != 0) {
                out.println("NO");
                continue;
            }
            if (pow2(n / 4)) {
                out.println("YES");
                continue;
            }
            out.println("NO");
        }
    }
}
