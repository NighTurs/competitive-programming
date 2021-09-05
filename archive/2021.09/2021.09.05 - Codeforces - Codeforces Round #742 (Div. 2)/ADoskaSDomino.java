package task;

import java.io.PrintWriter;

public class ADoskaSDomino {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        String s = in.next();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'U') {
                out.print('D');
            } else if (s.charAt(i) == 'D') {
                out.print('U');
            } else {
                out.print(s.charAt(i));
            }
        }
        out.println();
    }
}
