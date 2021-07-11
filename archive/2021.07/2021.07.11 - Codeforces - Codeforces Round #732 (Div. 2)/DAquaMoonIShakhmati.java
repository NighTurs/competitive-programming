package task;

import java.io.PrintWriter;

public class DAquaMoonIShakhmati {
    final long MOD = 998244353;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();
        int pair = 0;
        int sing = 0;
        int cur = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                cur++;
            } else {
                pair += cur / 2;
                sing += cur % 2;
                cur = 0;
            }
        }
        pair += cur / 2;
        sing += cur % 2;

        long[] fact = new long[n + 10];
        fact[0] = 1;
        for (int i = 1; i < n + 10; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }
        int free = n - pair * 2 - sing;
        out.println(CombinUtils.binom(free + pair, free, fact, MOD));

    }
}
