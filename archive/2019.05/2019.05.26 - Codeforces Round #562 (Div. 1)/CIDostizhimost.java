package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class CIDostizhimost {

    static final int N = 20;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        int[][] vs = new int[n][N];
        int[] last = new int[N];
        Arrays.fill(last, Integer.MAX_VALUE);

        for (int i = n - 1; i >= 0; i--) {
            for (int h = 0; h < N; h++) {
                vs[i][h] = Integer.MAX_VALUE;
            }
            for (int h = 0; h < N; h++) {
                if ((a[i] & (1 << h)) == 0) {
                    continue;
                }
                if (last[h] != Integer.MAX_VALUE) {
                    vs[i][h] = last[h];
                    for (int j = 0; j < N; j++) {
                        vs[i][j] = Math.min(vs[i][j], vs[last[h]][j]);
                    }
                }
                last[h] = i;
            }
        }

        outer:
        for (int i = 0; i < q; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            if (from == to) {
                out.println("Shi");
                continue outer;
            }
            for (int h = 0; h < N; h++) {
                if ((a[to] & (1 << h)) > 0 && vs[from][h] <= to) {
                    out.println("Shi");
                    continue outer;
                }
            }
            out.println("Fou");
        }
    }
}
