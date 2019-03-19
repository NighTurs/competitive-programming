package task;

import java.io.PrintWriter;

public class AColorfulSubsequence {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();

        long[] chars = new long['z' - 'a' + 1];
        for (int i = 0; i < n; i++) {
            chars[s.charAt(i) - 'a']++;
        }
        long ans = 1;
        long MOD = (long) 1e9 + 7;
        for (int i = 0; i < 'z' - 'a' + 1; i++) {
            ans = (ans * (chars[i] + 1)) % MOD;
        }

        out.println((ans + MOD - 1) % MOD);
    }
}
