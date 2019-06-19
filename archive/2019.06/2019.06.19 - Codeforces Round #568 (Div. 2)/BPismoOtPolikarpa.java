package task;

import java.io.PrintWriter;

public class BPismoOtPolikarpa {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        outer:
        for (int i = 0; i < n; i++) {
            String s1 = in.next();
            String s2 = in.next();
            int pos = 0;
            for (int h = 0; h < s1.length(); h++) {
                while (pos < s2.length() && (pos != 0 && s2.charAt(pos - 1) == s2.charAt(pos))
                        && s1.charAt(h) != s2.charAt(pos)) {
                    pos++;
                }
                if (pos < s2.length() && s1.charAt(h) == s2.charAt(pos)) {
                    pos++;
                } else {
                    out.println("NO");
                    continue outer;
                }
            }
            while (pos < s2.length() && (pos != 0 && s2.charAt(pos - 1) == s2.charAt(pos))) {
                pos++;
            }
            if (pos == s2.length()) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }
}
