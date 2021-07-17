package task;

import java.io.PrintWriter;

public class ADvoichnoDesyatichnie {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.next();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            max = Math.max(s.charAt(i) - '0', max);
        }
        out.println(max);
    }
}
