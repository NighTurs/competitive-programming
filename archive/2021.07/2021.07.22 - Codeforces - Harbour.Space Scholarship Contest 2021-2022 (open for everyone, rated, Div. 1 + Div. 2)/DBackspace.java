package task;

import java.io.PrintWriter;

public class DBackspace {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        char[] a = in.next().toCharArray();
        char[] b = in.next().toCharArray();
        int pos = a.length - 1;

        for (int i = b.length - 1; i >= 0; i--) {
            while (pos >= 0 && a[pos] != b[i]) {
                pos -= 2;
            }
            if (pos < 0) {
                out.println("NO");
                return;
            }
            pos--;
        }
        out.println("YES");
    }
}
