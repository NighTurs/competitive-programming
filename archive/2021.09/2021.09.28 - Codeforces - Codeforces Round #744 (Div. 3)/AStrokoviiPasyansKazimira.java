package task;

import java.io.PrintWriter;

public class AStrokoviiPasyansKazimira {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.next();
        int ac = 0;
        int b = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'B') {
                b++;
            } else {
                ac++;
            }
        }
        if (ac == b) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }
}
