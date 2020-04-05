package task;

import java.io.PrintWriter;

public class NestingDepth {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.next();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - '0';
            while (count > val) {
                count--;
                sb.append(')');
            }
            while (count < val) {
                count++;
                sb.append('(');
            }
            sb.append(s.charAt(i));
        }
        for (int i = 0; i < count; i++) {
            sb.append(')');
        }
        out.println(String.format("Case #%d: %s", testNumber, sb));
    }
}
