package task;

import java.io.PrintWriter;

public class E2UdalyaiIUdlinyaiSlozhnayaVersiya {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        String s = in.next();
        StringHash hash = new StringHash(s, 2);
        for (int i = 1; i < n; i++) {
            int t1 = 0;
            int t2 = n - i;
            if (s.charAt(0) == s.charAt(i)) {
                while (t1 < t2) {
                    int t = (t1 + t2 + 1) / 2;
                    if (hash.equals(0, i, t)) {
                        t1 = t;
                    } else {
                        t2 = t - 1;
                    }
                }
            }
            if (i + t1 >= n || s.charAt(i + t1) > s.charAt(t1)) {
                s = s.substring(0, i);
                break;
            }
        }
        int h = 0;
        for (int i = 0; i < m; i++) {
            out.print(s.charAt(h));
            h++;
            if (h >= s.length()) {
                h = 0;
            }
        }
    }
}
