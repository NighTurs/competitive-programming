package task;

import java.io.PrintWriter;

public class ALyubiteA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.next();

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                count++;
            }
        }
        int other = s.length() - count;
        int ans = 0;
        while (other >= count) {
            ans++;
            other--;
        }
        out.println(s.length() - ans);
    }
}
