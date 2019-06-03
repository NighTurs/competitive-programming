package task;

import java.io.PrintWriter;

public class CMinusNaMinusDayutPlyus {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        outer:
        for (int t = 0; t < n; t++) {
            String s1 = in.next();
            String s2 = in.next();

            int i = 0;
            int h = 0;
            while (i < s1.length() && h < s2.length()) {
                if (s1.charAt(i) != s2.charAt(h)) {
                    if (s2.charAt(h) == '-') {
                        out.println("NO");
                        continue outer;
                    }
                    if (i + 1 < s1.length() && s1.charAt(i + 1) == '-') {
                        i+=2;
                        h++;
                    } else {
                        out.println("NO");
                        continue outer;
                    }
                } else {
                    i++;
                    h++;
                }
            }
            if (i == s1.length() && h == s2.length()) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }

    }
}
