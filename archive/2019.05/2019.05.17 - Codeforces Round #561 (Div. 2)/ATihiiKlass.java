package task;

import java.io.PrintWriter;

public class ATihiiKlass {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] ct1 = new int['z' - 'a' + 1];
        int[] ct2 = new int['z' - 'a' + 1];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            int c = s.charAt(0) - 'a';
            if (ct1[c] > ct2[c]) {
                ct2[c]++;
            } else {
                ct1[c]++;
            }
        }
        long ans = 0;
        for (int i = 0; i <= 'z' - 'a'; i++) {
            if (ct1[i] > 1) {
                ans += (ct1[i] - 1) * ct1[i] / 2;
            }
            if (ct2[i] > 1) {
                ans += (ct2[i] - 1) * ct2[i] / 2;
            }
        }
        out.println(ans);
    }
}
