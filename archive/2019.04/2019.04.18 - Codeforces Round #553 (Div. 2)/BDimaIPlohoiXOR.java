package task;

import java.io.PrintWriter;

public class BDimaIPlohoiXOR {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < m; h++) {
                a[i][h] = in.nextInt();
            }
        }
        int[] ans = new int[n];
        for (int bit = 0; bit < 10; bit++) {
            int[][] idx = new int[n][2];
            for (int i = 0; i < n; i++) {
                idx[i][0] = -1;
                idx[i][1] = -1;
            }

            for (int i = 0; i < n; i++) {
                for (int h = 0; h < m; h++) {
                    if ((a[i][h] & (1 << bit)) > 0) {
                        idx[i][1] = h;
                    } else {
                        idx[i][0] = h;
                    }
                }
            }


            int count = 0;
            for (int i = 0; i < n; i++) {
                if (idx[i][1] == -1 || idx[i][0] == -1) {
                    ans[i] = 0;
                    if (idx[i][0] == -1) {
                        count++;
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                if (idx[i][1] == -1 || idx[i][0] == -1) {
                    continue;
                }
                if (count % 2 == 0) {
                    ans[i] = idx[i][1];
                    count++;
                } else {
                    ans[i] = idx[i][0];
                }
            }
            if (count % 2 == 1) {
                out.println("TAK");
                for (int i = 0; i < n; i++) {
                    out.print(ans[i] + 1);
                    out.print(' ');
                }
                return;
            }
        }
        out.println("NIE");
    }
}
