package task;

import java.io.PrintWriter;

public class DOcheredSPrioritetom {
    static final long MOD = 998244353;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            if (s.equals("-")) {
                a[i] = 0;
            } else {
                a[i] = in.nextInt();
            }
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                continue;
            }
            int ref = a[i];
            long[] dp1 = new long[n];
            long[] dp2 = new long[n];
            long[] rdp = dp1;
            long[] wdp = dp2;
            rdp[0] = 1;
            for (int h = 0; h < n; h++) {
                if (i == h) {
                    continue;
                }
                System.arraycopy(rdp, 0, wdp, 0, n);
                if (a[h] == 0) {
                    if (h < i) {
                        wdp[0] = (wdp[0] +  rdp[0]) % MOD;
                    }
                    for (int j = 1; j < n; j++) {
                        wdp[j - 1] = (wdp[j - 1] + rdp[j]) % MOD;
                    }
                } else {
                    if (a[h] > ref || (a[h] == ref && h > i)) {
                        for (int j = 0; j < n; j++) {
                            wdp[j] = (wdp[j] + rdp[j]) % MOD;
                        }
                    } else {
                        for (int j = 0; j < n - 1; j++) {
                            wdp[j + 1] = (wdp[j + 1] + rdp[j]) % MOD;
                        }
                    }
                }
                long[] z = rdp;
                rdp = wdp;
                wdp = z;
            }
            for (int j = 0; j < n; j++) {
                ans = (ans + (rdp[j] * ref) % MOD) % MOD;
            }
        }
        out.println(ans);

    }
}
