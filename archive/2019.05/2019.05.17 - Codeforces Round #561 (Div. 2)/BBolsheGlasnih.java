package task;

import java.io.PrintWriter;

public class BBolsheGlasnih {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        char[] let = new char[]{'a', 'e', 'i', 'o', 'u'};
        for (int i = 5; i <= n; i++) {
            if (n % i != 0) {
                continue;
            }
            int nn = i;
            int mm = n / i;
            if (mm < 5) {
                continue;
            }

            for (int j1 = 0; j1 < nn; j1++) {
                int st = j1 % let.length;
                for (int j2 = 0; j2 < mm; j2++) {
                    out.print(let[(st + j2) % let.length]);
                }
            }
            return;
        }
        out.println(-1);
    }
}
