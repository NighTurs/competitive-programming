package task;

import java.io.PrintWriter;

public class CKoltsa {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                int e = i + 1;
                if (n - e >= e - 1) {
                    out.println(e + " " + n + " " + (e + 1) + " " + n);
                } else {
                    out.println(1 + " " + e + " " + 1 + " " + (e - 1));
                }
                return;
            }
        }
        if (n % 2 == 1) {
            n -= 1;
        }
        out.println(1 + " " + n + " " + 1 + " " + (n / 2));
    }
}
