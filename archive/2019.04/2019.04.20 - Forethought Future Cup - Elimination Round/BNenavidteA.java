package task;

import java.io.PrintWriter;

public class BNenavidteA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.next();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                count++;
            }
        }
        if ((s.length() - count) % 2 != 0) {
            out.println(":(");
            return;
        }
        int last = s.length() - ((s.length() - count) / 2);
        int cur = last;
        for (int i = 0; i < last; i++) {
            if (s.charAt(i) == 'a') {
                continue;
            }
            if (cur >= s.length() || s.charAt(i) != s.charAt(cur)) {
                out.println(":(");
                return;
            }
            cur++;
        }
        for (int i = 0; i < last; i++) {
            out.print(s.charAt(i));
        }
    }
}
