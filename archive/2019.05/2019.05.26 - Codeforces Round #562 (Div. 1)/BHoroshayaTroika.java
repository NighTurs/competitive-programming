package task;

import java.io.PrintWriter;

public class BHoroshayaTroika {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.next();
        long ans = (1 + (long) s.length()) * s.length() / 2 - s.length() - (s.length() - 1);
        boolean[] found = new boolean[s.length()];
        for (int k = 2; k <= s.length(); k+=1) {
            boolean any = false;
            for (int i = 0; i < s.length() - k; i++) {
                if (found[i] || found[i + 1]) {
                    found[i] = true;
                    continue;
                }
                if (k % 2 == 0 && s.charAt(i) == s.charAt(i + k) && s.charAt(i) == s.charAt(i + k / 2)) {
                    found[i] = true;
                } else {
                    ans--;
                    any = true;
                }
            }
            if (!any) {
                break;
            }
        }
        out.println(ans);
    }
}
