package task;

import java.io.PrintWriter;

public class D1DvestiDvadtsatOdinProstayaVersiya {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        String s = in.next();
        int[] cum = new int[n + 1];
        for (int i = 0; i < s.length(); i++) {
            cum[i] = (i == 0 ? 0 : cum[i - 1]);
            if ((s.charAt(i) == '+' && i % 2 == 0) ||
                    (s.charAt(i) == '-' && i % 2 == 1)) {
                cum[i] += 1;
            } else {
                cum[i] -= 1;
            }
        }
        for (int i = 0; i < q; i++) {
            int l = in.nextInt() - 1;
            int r = in.nextInt() - 1;
            int val = cum[r] - (l == 0 ? 0 : cum[l - 1]);
            if (val == 0) {
                out.println(0);
            } else if (val % 2 == 0) {
                out.println(2);
            } else {
                out.println(1);
            }
        }
    }
}
