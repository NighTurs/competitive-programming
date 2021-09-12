package task;

import java.io.PrintWriter;

public class BMINMEXRazrez {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.next();
        int zr = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                zr++;
            }
        }
        if (zr == s.length()) {
            out.println(1);
            return;
        }
        if (zr == 0) {
            out.println(0);
            return;
        }

        boolean yes = false;
        boolean first = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (!yes) {
                    if (!first) {
                        yes = true;
                        first = true;
                    } else {
                        out.println(2);
                        return;
                    }
                } else {
                    first = true;
                }
            } else {
                yes = false;
            }
        }
        out.println(1);
    }
}
