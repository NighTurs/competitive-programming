package task;

import java.io.PrintWriter;

public class CProstiePrefiksnieSummi {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int ctOne = 0;
        int ctTwo = 0;
        for (int i = 0; i < n; i++) {
            if (in.nextInt() == 1) {
                ctOne++;
            } else {
                ctTwo++;
            }
        }
        if (ctOne >= 3) {
            ctOne -= 3;
            for (int i = 0; i < 3; i++) {
                out.print(1);
                out.print(' ');
            }

        } else {
            if (ctTwo > 0) {
                out.print(2);
                out.print(' ');
                ctTwo--;
            }
            if (ctOne > 0) {
                out.print(1);
                out.print(' ');
                ctOne--;
            }
        }
        for (int i = 0; i < ctTwo; i++) {
            out.print(2);
            out.print(' ');
        }
        for (int i = 0; i < ctOne; i++) {
            out.print(1);
            out.print(' ');
        }
    }
}
