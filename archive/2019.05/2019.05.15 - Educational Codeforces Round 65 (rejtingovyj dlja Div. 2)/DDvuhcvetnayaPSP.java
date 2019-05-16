package task;

import java.io.PrintWriter;

public class DDvuhcvetnayaPSP {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();
        int r = 0;
        int b = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                if (r > b) {
                    b++;
                    out.print('0');
                } else {
                    r++;
                    out.print('1');
                }
            } else {
                if (r > b) {
                    r--;
                    out.print('1');
                } else {
                    b--;
                    out.print('0');
                }
            }
        }
    }
}
