package task;

import java.io.PrintWriter;

public class ENOKiDolzhniBitBolshimi {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        boolean[][] a = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            int k = in.nextInt();
            for (int h = 0; h < k; h++) {
                int val = in.nextInt() - 1;
                a[i][val] = true;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < n; h++) {
                if (i == h) {
                    continue;
                }
                boolean isFine = false;
                for (int j = 0; j < m; j++) {
                    if (a[i][j] && a[h][j]) {
                        isFine = true;
                        break;
                    }
                }
                if (!isFine) {
                    out.println("impossible");
                    return;
                }
            }
        }
        out.println("possible");
    }
}
