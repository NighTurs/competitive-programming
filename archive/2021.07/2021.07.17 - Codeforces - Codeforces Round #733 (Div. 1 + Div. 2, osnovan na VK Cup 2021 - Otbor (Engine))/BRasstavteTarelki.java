package task;

import java.io.PrintWriter;

public class BRasstavteTarelki {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n][m];
        a[0][0] = 1;
        a[0][m - 1] = 1;
        a[n - 1][0] = 1;
        a[n - 1][m - 1] = 1;
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < m; h++) {
                if (i == 0 || i == n - 1 || h == 0 || h == m - 1) {
                    boolean found = false;
                    for (int j1 = -1; j1 <= 1; j1++) {
                        for (int j2 = -1; j2 <= 1; j2++) {
                            int ii = i + j1;
                            int hh = h + j2;
                            if (ii < 0 || hh < 0 || ii >= n || hh >= m) {
                                continue;
                            }
                            if (a[ii][hh] == 1) {
                                found = true;
                            }
                        }
                    }
                    if (!found) {
                        a[i][h] = 1;
                    }
                }
                out.print(a[i][h]);
            }
            out.println();
        }
    }
}
