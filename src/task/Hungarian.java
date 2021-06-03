package task;

import java.util.Arrays;

public class Hungarian {

    /**
     * https://e-maxx.ru/algo/assignment_hungary
     * @param a adjacency matrix, indexes from 1
     * @return long[n + 1] with selected tasks (indexes from 1), cost is at 0 intex
     */
    public static long[] solve(int n, int m, long[][] a) {
        long[] u = new long[n + 1];
        long[] v = new long[m + 1];
        int[] p = new int[m + 1];
        int[] way = new int[m + 1];
        for (int i = 1; i <= n; ++i) {
            p[0] = i;
            int j0 = 0;
            long[] minv = new long[m + 1];
            Arrays.fill(minv, Long.MAX_VALUE);
            boolean[] used = new boolean[m + 1];
            do {
                used[j0] = true;
                int i0 = p[j0];
                long delta = Long.MAX_VALUE;
                int j1 = 0;
                for (int j = 1; j <= m; ++j) {
                    if (!used[j]) {
                        long cur = a[i0][j] - u[i0] - v[j];
                        if (cur < minv[j]) {
                            minv[j] = cur;
                            way[j] = j0;
                        }
                        if (minv[j] < delta) {
                            delta = minv[j];
                            j1 = j;
                        }
                    }
                }
                for (int j = 0; j <= m; ++j) {
                    if (used[j]) {
                        u[p[j]] += delta;
                        v[j] -= delta;
                    } else {
                        minv[j] -= delta;
                    }
                }
                j0 = j1;
            } while (p[j0] != 0);
            do {
                int j1 = way[j0];
                p[j0] = p[j1];
                j0 = j1;
            } while (j0 != 0);
        }
        long[] ans = new long[n + 1];
        ans[0] = -v[0];
        for (int j = 1; j <= m; ++j) {
            ans[p[j]] = j;
        }
        return ans;
    }
}
