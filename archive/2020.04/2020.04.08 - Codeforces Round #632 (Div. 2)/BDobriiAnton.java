package task;

import java.io.PrintWriter;

public class BDobriiAnton {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        int[] a = new int[(int)(1e5 + 10)];
        for (int tt = 0; tt < t; tt++) {
            int n = in.nextInt();

            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            boolean pos = false;
            boolean neg = false;
            boolean possible = true;
            for (int i = 0; i < n; i++) {
                int cur = in.nextInt();
                if (a[i] > cur && !neg) {
                    possible = false;
                }
                if (a[i] < cur && !pos) {
                    possible = false;
                }
                if (a[i] == 1) {
                    pos = true;
                }
                if (a[i] == -1) {
                    neg = true;
                }
            }
            if (possible) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }
}
