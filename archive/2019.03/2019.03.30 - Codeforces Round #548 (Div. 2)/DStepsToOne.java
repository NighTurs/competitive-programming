package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DStepsToOne {

    private static long MOD = (int) 1e9 + 7;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int m = in.nextInt();
        List<List<Pair<Integer, Integer>>> factors = new ArrayList<>();
        for (int i = 0; i <= m; i++) {
            factors.add(new ArrayList<>());
        }

        boolean[] isPrime = new boolean[m + 1];
        Arrays.fill(isPrime, true);

        for (int i = 2; i <= m; i++) {
            if (!isPrime[i]) {
                continue;
            }
            factors.get(i).add(new Pair<>(i, 1));
            for (int h = i + i; h <= m; h += i) {
                isPrime[h] = false;
                int ct = 0;
                int z = h;
                while (z % i == 0) {
                    z /= i;
                    ct++;
                }
                factors.get(h).add(new Pair<>(i, ct));
            }
        }

        long invm = BinPow.binPow(m, MOD - 2, MOD);
        long[] dp = new long[m + 1];
        dp[1] = 0;
        List<Integer> gcds = new ArrayList<>();
        for (int i = 2; i <= m; i++) {
            long sum = 1;
            gcds.clear();
            possibleGcd(factors.get(i), 0, 1, gcds);
            for (int h = 0; h < gcds.size(); h++) {
                int gcd = gcds.get(h);
                if (gcd == i) {
                    continue;
                }
                int x = i / gcd;
                long ctNums = countNumbers(factors.get(x), 0, 0, 1, m / gcd);
                sum = (sum + ((ctNums * dp[gcd]) % MOD) * invm) % MOD;
            }
            dp[i] = ((sum * m) % MOD * BinPow.binPow(m - m / i, MOD - 2, MOD)) % MOD;
        }

        long ans = 1;
        for (int i = 1; i <= m; i++) {
            ans = (ans + (dp[i] * invm)) % MOD;
        }

        out.println(ans);
    }

    public long countNumbers(List<Pair<Integer, Integer>> a, int pos, int taken, int num, int m) {
        if (pos == a.size()) {
            long ct = m / num;
            return taken % 2 == 0 ? ct : -ct;
        }
        return (countNumbers(a, pos + 1, taken, num, m) + countNumbers(a, pos + 1, taken + 1, num * a.get(pos).fs, m))
                % MOD;
    }

    public void possibleGcd(List<Pair<Integer, Integer>> a, int pos, int cur, List<Integer> outs) {
        if (pos == a.size()) {
            outs.add(cur);
            return;
        }
        possibleGcd(a, pos + 1, cur, outs);
        int z = cur;
        for (int i = 0; i < a.get(pos).sc; i++) {
            z *= a.get(pos).fs;
            possibleGcd(a, pos + 1, z, outs);
        }
    }
}
