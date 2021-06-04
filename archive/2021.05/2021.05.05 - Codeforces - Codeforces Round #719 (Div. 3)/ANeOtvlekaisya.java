package task;

import java.io.PrintWriter;

public class ANeOtvlekaisya {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();
        boolean[] v = new boolean['Z' - 'A' + 1];
        int prev = -1;
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - 'A';
            if (v[val] && prev != val) {
                out.println("NO");
                return;
            }
            v[val] = true;
            prev = val;
        }
        out.println("YES");
    }
}
