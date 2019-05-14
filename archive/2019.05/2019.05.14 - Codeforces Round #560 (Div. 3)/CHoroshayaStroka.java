package task;

import java.io.PrintWriter;

public class CHoroshayaStroka {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();
        boolean[] remove = new boolean[n];
        int ans = 0;
        char prev = s.charAt(0);
        int idx = 0;
        for (int i = 1; i < n; i++) {
            if (idx % 2 == 0 && s.charAt(i) == prev) {
                remove[i] = true;
                ans++;
            } else {
                prev = s.charAt(i);
                idx++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!remove[i]) {
                sb.append(s.charAt(i));
            }
        }
        if ((n - ans) % 2 == 1) {
            out.println(ans + 1);
            sb.deleteCharAt(sb.length() - 1);
        } else {
            out.println(ans);
        }
        out.println(sb);
    }
}
