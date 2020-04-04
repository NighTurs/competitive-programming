package task;

import java.io.PrintWriter;

public class BTolikIDyadya {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n * m][2];
        int[][] b = new int[n * m][2];
        int ct = 0;
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < m; h++) {
                a[ct][0] = i + 1;
                a[ct][1] = h + 1;
                ct++;
            }
        }
        ct = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int h = m - 1; h >= 0; h--) {
                b[ct][0] = i + 1;
                b[ct][1] = h + 1;
                ct++;
            }
        }
        ct = 0;
        for (int i = 0; i < n * m; i++) {
            out.print(a[i][0]);
            out.print(' ');
            out.println(a[i][1]);
            ct++;
            if (ct == n * m) {
                break;
            }
            out.print(b[i][0]);
            out.print(' ');
            out.println(b[i][1]);
            ct++;
            if (ct == n * m) {
                break;
            }
        }
    }
}
