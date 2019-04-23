package task;

import java.io.PrintWriter;

public class BEEEeE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();
        int k = in.nextInt();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(k- 1)) {
                out.print('*');
            } else {
                out.print(s.charAt(i));
            }
        }
    }
}
