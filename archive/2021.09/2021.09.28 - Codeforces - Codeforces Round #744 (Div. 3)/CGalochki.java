package task;

import java.io.PrintWriter;

public class CGalochki {
    boolean check(int i, int h, int n, int m) {
        if (i < 0 || h < 0) {
            return false;
        }
        if (i >= n || h >= m) {
            return false;
        }
        return true;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        boolean[][] a = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int h = 0; h < m; h++) {
                if (s.charAt(h) == '*') {
                    a[i][h] = true;
                }
            }
        }
        boolean[][] exp = new boolean[n][m];
        for (int i = n - 1; i >= 0; i--) {
            for (int h = 0; h < m; h++) {
                if (!a[i][h]) {
                    continue;
                }
                int last = 0;
                for (int j = 1; j <= Math.max(n, m); j++) {
                    int l1 = i - j;
                    int l2 = h - j;
                    boolean left = check(l1, l2, n, m) && a[l1][l2];
                    int r1 = i - j;
                    int r2 = h + j;
                    boolean right = check(r1, r2, n, m) && a[r1][r2];
                    if (left && right) {
                        last = j;
                    } else {
                        break;
                    }
                }
                if (last >= k) {
                    for (int j = 1; j <= Math.max(n, m); j++) {
                        int l1 = i - j;
                        int l2 = h - j;
                        boolean left = check(l1, l2, n, m) && a[l1][l2];
                        int r1 = i - j;
                        int r2 = h + j;
                        boolean right = check(r1, r2, n, m) && a[r1][r2];
                        if (left && right) {
                            exp[l1][l2] = true;
                            exp[r1][r2] = true;
                        } else {
                            break;
                        }
                    }
                }
                if (last < k && !exp[i][h]) {
                    out.println("NO");
                    return;
                }
                exp[i][h] = true;
            }
        }
        out.println("YES");
    }
}
