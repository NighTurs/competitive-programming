package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskE {

    private static final int N = 30;
    private static final int M = 50;
    private static final int[][][] a = new int[N + 1][N + 1][M + 1];
    private static final boolean[][] visited = new boolean[N + 1][N + 1];

    public void merge(int nl, int ml, int nr, int mr, int no, int mo, int value) {
        int[] rl = a[nl][ml];
        int[] rr = a[nr][mr];
        int[] ro = a[no][mo];
        for (int i = 1; i <= M; i++) {
            if (rl[i] != Integer.MAX_VALUE && rl[i] + value < ro[i]) {
                ro[i] = rl[i] + value;
            }
            if (rr[i] != Integer.MAX_VALUE && rr[i] + value < ro[i]) {
                ro[i] = rr[i] + value;
            }
            for (int h = 1; h <= M; h++) {
                if (rl[i] != Integer.MAX_VALUE && rr[h] != Integer.MAX_VALUE && i + h <= M &&
                        rl[i] + rr[h] + value < ro[i + h]) {
                    ro[i + h] = rl[i] + rr[h] + value;
                }
            }
        }
    }

    public void doit(int n, int m) {
        if (visited[n][m]) {
            return;
        }
        visited[n][m] = true;
        for (int i = 1; i < n; i++) {
            doit(i, m);
            doit(n - i, m);
            merge(i, m, n - i, m, n, m, m * m);
        }
        for (int i = 1; i < m; i++) {
            doit(n, i);
            doit(n, m - i);
            merge(n, i, n, m - i, n, m, n * n);
        }
    }

    public void prep() {
        for (int i = 0; i <= N; i++) {
            for (int h = 0; h <= N; h++) {
                Arrays.fill(a[i][h], Integer.MAX_VALUE);
                if (i * h <= M) {
                    a[i][h][i * h] = 0;
                }
            }
        }
        visited[1][1] = true;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        prep();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            doit(n, m);
            out.println(a[n][m][k]);
        }
    }
}
