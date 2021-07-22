package task;

import java.io.PrintWriter;

public class BPerevorotStroki {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        char[] a = in.next().toCharArray();
        char[] b = in.next().toCharArray();
        for (int turn = 0; turn <= b.length; turn++) {
            for (int st = 0; st < a.length; st++) {

                int h = st;
                boolean fine = true;
                for (int i = 0; i < b.length; i++) {
                    if (b[i] != a[h]) {
                        fine = false;
                        break;
                    }
                    if (turn <= i) {
                        h--;
                    } else {
                        h++;
                    }
                    if (i != b.length - 1 && (h < 0 || h >= a.length)) {
                        fine = false;
                        break;
                    }
                }
                if (fine) {
                    out.println("YES");
                    return;
                }
            }
        }
        out.println("NO");

    }
}
