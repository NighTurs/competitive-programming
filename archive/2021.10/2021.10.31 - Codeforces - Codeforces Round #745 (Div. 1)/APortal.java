package task;

import java.io.PrintWriter;

public class APortal {

    int n;
    int m;

    int capped(int i, int h, int[][] cum) {
        if (i < 0) {
            return 0;
        }
        if (h < 0) {
            return 0;
        }
        return cum[i][h];
    }

    int sum(int x, int y, int x1, int y1, int[][] cum) {
        return cum[x1][y1] - capped(x - 1, y1, cum) - capped(x1, y - 1, cum) + capped(x - 1, y - 1, cum);
    }

    int sumInv(int x, int y, int x1, int y1, int[][] cum) {
        return (x1 - x + 1) * (y1 - y + 1) - sum(x, y, x1, y1, cum);
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int h = 0; h < m; h++) {
                if (s.charAt(h) == '1') {
                    a[i][h] = 1;
                }
            }
        }
        int[][] cum = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < m; h++) {
                cum[i][h] = a[i][h] + capped(i - 1, h, cum) + capped(i, h - 1, cum) - capped(i - 1, h - 1, cum);
            }
        }
        int mmin = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int h = 0; h < m; h++) {
                for (int j2 = h + 3; j2 < m; j2++) {
                    int j3 = i + 4;
                    if (j3 >= n) {
                        break;
                    }
                    int s = sumInv(i, h + 1, i, j2 - 1, cum) + sumInv(i + 1, h, j3 - 1, h, cum) +
                            sum(i + 1, h + 1, j3 - 1, j2 - 1, cum);
                    if (s > 20) {
                        break;
                    }
                    for (int j1 = i + 4; j1 < n; j1++) {
                        int first = sumInv(i, h + 1, i, j2 - 1, cum) + sumInv(i + 1, h, j1 - 1, h, cum) +
                                sum(i + 1, h + 1, j1 - 1, j2 - 1, cum);
                        if (first > 20) {
                            break;
                        }
                        int second = first + sumInv(j1, h + 1, j1, j2 - 1, cum) + sumInv(i + 1, j2, j1 - 1, j2, cum);
                        mmin = Math.min(mmin, second);
                    }
                }

            }
        }

        out.println(mmin);


    }
}
