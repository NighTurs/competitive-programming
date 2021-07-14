package task;

import java.io.PrintWriter;

public class D1PRSIKriminalniiSpisokProstayaVersiya {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        testNumber = in.nextInt();
        for (int t = 0; t < testNumber; t++) {
            int n = in.nextInt();
            int k = in.nextInt();
            for (int i = 0; i < n; i++) {
                if (i == 0) {
                    out.println(0);
                } else {
                    out.println((i - 1) ^ i);
                }
                out.flush();
                int ans = in.nextInt();
                if (ans == 1) {
                    break;
                }
                if (ans == -1) {
                    return;
                }
            }
        }

    }
}
