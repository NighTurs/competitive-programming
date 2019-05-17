package task;

import java.util.Arrays;

public class Gauss {

    static int gauss(long[][] a, long[] ans, long mod) {
        int n = a.length;
        int m = a[0].length - 1;

        int[] where = new int[m];
        for (int col = 0, row = 0; col < m && row < n; ++col) {
            int sel = row;
            for (int i = row; i < n; ++i) {
                if (Math.abs(a[i][col]) > Math.abs(a[sel][col])) {
                    sel = i;
                }
            }
            if (a[sel][col] == 0) {
                continue;
            }
            for (int i = col; i <= m; ++i) {
                long z = a[sel][i];
                a[sel][i] =  a[row][i];
                a[row][i] = z;
            }
            where[col] = row;

            for (int i = 0; i < n; ++i) {
                if (i != row) {
                    long c = (a[i][col] * BinPow.binPow(a[row][col], mod - 2, mod)) % mod;
                    for (int j = col; j <= m; ++j) {
                        a[i][j] = (a[i][j] + mod - (a[row][j] * c) % mod) % mod;
                    }
                }
            }
            //noinspection AssignmentToForLoopParameter
            ++row;
        }

        Arrays.fill(ans, 0);
        for (int i = 0; i < m; ++i) {
            if (where[i] != -1) {
                ans[i] = (a[where[i]][m] * BinPow.binPow(a[where[i]][i], mod - 2, mod)) % mod;
            }
        }
        for (int i = 0; i < n; ++i) {
            long sum = 0;
            for (int j = 0; j < m; ++j) {
                sum = (sum + ans[j] * a[i][j]) % mod;
            }
            if (sum != a[i][m]) {
                return 0;
            }
        }

        for (int i = 0; i < m; ++i) {
            if (where[i] == -1) {
                return Integer.MAX_VALUE;
            }
        }
        return 1;
    }
}
