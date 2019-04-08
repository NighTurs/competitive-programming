package task;

import java.io.PrintWriter;

public class BNirvana {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.next();

        int max = 1;

        for (int i = 0; i < s.length(); i++) {
            max *= s.charAt(i) - '0';
        }

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - '0';
            if (c != 0) {
                int mul = 1;
                for (int h = 0; h < i; h++) {
                    mul *= s.charAt(h) - '0';
                }
                mul *= s.charAt(i) - '0' - 1;
                if (i == 0 && s.charAt(i) == '1') {
                    mul = 1;
                }
                for (int h = i + 1; h < s.length(); h++) {
                    mul *= 9;
                }
                max = Math.max(max, mul);
            }
        }
        out.println(max);
    }
}
