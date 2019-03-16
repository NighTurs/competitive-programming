package task;

import java.io.PrintWriter;

public class DRaspisanieSmeni {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.next();
        String t = in.next();

        int p = 3;
        int MOD = (int) 1e9 + 7;
        long hash = 0, ppow = 1;
        long[] hashs = new long[t.length()];
        for (int i = t.length() - 1; i >= 0; i--) {
            hash = ((t.charAt(i) - '0' + 1) * ppow + hash) % MOD;
            hashs[i] = hash;
            ppow = (ppow * p) % MOD;
        }

        int loop = -1;
        ppow = p;
//        outer:
        for (int i = 1; i < t.length(); i++) {
            if ((hashs[i] * ppow) % MOD == (hashs[0] + MOD - hashs[t.length() - i]) % MOD) {
//                int j = 0;
//                for (int h = i; h < t.length(); h++) {
//                    if (t.charAt(h) != t.charAt(j)) {
//                        ppow = (ppow * p) % MOD;
//                        continue outer;
//                    }
//                    j++;
//                }
                loop = i;
                break;
            }
            ppow = (ppow * p) % MOD;
        }

        int sum[] = new int[2];
        for (int i = 0; i < s.length(); i++) {
            sum[s.charAt(i) - '0']++;
        }
        int ans[] = new int[s.length()];
        int pos = t.length() - 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            int c = t.charAt(pos) - '0';
            if (sum[c] > 0) {
                ans[i] = c;
                sum[c]--;
            } else {
                ans[i] = Math.abs(1 - c);
                sum[Math.abs(1 - c)]--;
            }
            pos--;
            if (pos == -1) {
                pos = loop - 1;
            }
            if (pos < 0) {
                pos = t.length() - 1;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            out.print((char) (ans[i] + '0'));
        }
    }
}
