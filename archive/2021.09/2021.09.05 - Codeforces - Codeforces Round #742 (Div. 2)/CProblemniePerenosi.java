package task;

import java.io.PrintWriter;

public class CProblemniePerenosi {
    int doit(String s, int pos) {
        int d = 0;
        for (int i = pos; i < s.length(); i +=2) {
            d = (d * 10) + (s.charAt(i) - '0');
        }
        return d + 1;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.next();
        out.println(doit(s, 0) * doit(s, 1) - 2);
    }
}
