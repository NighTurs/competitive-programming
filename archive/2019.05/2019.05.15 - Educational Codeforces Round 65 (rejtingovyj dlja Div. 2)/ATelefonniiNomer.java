package task;

import java.io.PrintWriter;

public class ATelefonniiNomer {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        for (int tt = 0; tt < t; tt++) {
            int n = in.nextInt();
            String s = in.next();
            int firstPos = -1;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '8') {
                    firstPos = i;
                    break;
                }
            }
            if (firstPos != -1 && n - firstPos >= 11) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }
}
