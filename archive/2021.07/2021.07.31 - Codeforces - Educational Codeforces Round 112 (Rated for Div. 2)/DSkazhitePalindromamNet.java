package task;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class DSkazhitePalindromamNet {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        String s = in.next();

        List<String> perms =  Arrays.asList("abc", "acb", "bac", "bca", "cab", "cba");
        int[][] cum = new int[perms.size()][n + 1];

        int idx = 0;
        for (String perm : perms) {
            int h = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != perm.charAt(h)) {
                    cum[idx][i + 1] = cum[idx][i] + 1;
                } else {
                    cum[idx][i + 1] = cum[idx][i];
                }
                h++;
                if (h == 3) {
                    h = 0;
                }
            }
            idx++;
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            int ans = Integer.MAX_VALUE;

            for (int h = 0; h < perms.size(); h++) {
                ans = Math.min(ans, cum[h][to] - cum[h][from - 1]);
            }
            out.println(ans);
        }
    }
}
