package task;

import java.io.PrintWriter;

public class BHoroshayaStroka {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        for (int ii = 0; ii < t; ii++) {
            int n = in.nextInt();
            String s = in.next();
            int l = s.length() - 1;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '>') {
                    l = i;
                    break;
                }
            }
            int r = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == '<') {
                    r = i;
                    break;
                }
            }

            int ans = Math.min(l, s.length() - 1 - r);
            out.println(ans);
        }
    }

}
