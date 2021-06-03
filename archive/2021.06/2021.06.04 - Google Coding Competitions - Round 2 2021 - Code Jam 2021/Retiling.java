package task;

import java.io.PrintWriter;

public class Retiling {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int f = in.nextInt();
        int s = in.nextInt();
        boolean[][] a = read(in, n, m);
        boolean[][] b = read(in, n, m);
        int vc = n * m;
        long[][] adj = new long[vc + 1][vc + 1];
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < m; h++) {
                for (int ii = 0; ii < n; ii++) {
                    for (int hh = 0; hh < m; hh++) {
                        int fromIdx = idx(i, h, m);
                        int toIdx = idx(ii, hh, m);
                        if (fromIdx == toIdx) {
                            if (a[i][h] == b[i][h]) {
                                adj[fromIdx][toIdx] = 0;
                            } else {
                                adj[fromIdx][toIdx] = f * 2;
                            }
                        } else if (a[i][h] != b[i][h] && a[ii][hh] != b[ii][hh] && a[i][h] != a[ii][hh]) {
                            adj[fromIdx][toIdx] = s * (Math.abs(i - ii) + Math.abs(h - hh));
                        } else {
                            adj[fromIdx][toIdx] = (int) 1e10;
                        }
                    }
                }
            }
        }

        long[] ans = Hungarian.solve(vc, vc, adj);
        out.println(String.format("Case #%d: %d", testNumber, ans[0] / 2));
    }

    public int idx(int i, int h, int m) {
        return i * m + h + 1;
    }

    private boolean[][] read(InputReader in, int n, int m) {
        boolean[][] a = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String str = in.next();
            for (int h = 0; h < str.length(); h++) {
                if (str.charAt(h) == 'M') {
                    a[i][h] = true;
                }
            }
        }
        return a;
    }
}
