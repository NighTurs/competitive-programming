package task;

import java.io.PrintWriter;

public class PancakePyramid {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        long[][] mem = new long[n][n];

        for (int st = 0; st < n; st++) {
            int prev = a[st];
            long sum = 0;
            mem[st][st] = sum;
            for (int i = st + 1; i < n; i++) {
                if (prev > a[i]) {
                    sum += prev - a[i];
                } else {
                    prev = a[i];
                }
                mem[st][i] = sum;
            }
        }
        for (int st = n - 1; st >= 0; st--) {
            int prev = a[st];
            long sum = 0;
            for (int i = st - 1; i >= 0; i--) {
                if (prev > a[i]) {
                    sum += prev - a[i];
                } else {
                    prev = a[i];
                }
                mem[st][i] = sum;
            }
        }

        long MOD = (long)1e9 + 7;
        long ans = 0;
        for (int l = 0; l < n; l++) {
            int idxMax = l;
            int max = a[l];
            for (int r = l + 1; r < n; r++) {
                if (a[r] > max) {
                    max = a[r];
                    idxMax = r;
                }

                if (r - l + 1 < 3) {
                    continue;
                }
                ans = (ans + mem[l][idxMax] + mem[r][idxMax]) % MOD;
            }
        }

        out.println(String.format("Case #%d: %d", testNumber, ans));
    }
}
