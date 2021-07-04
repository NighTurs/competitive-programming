package task;

import java.io.PrintWriter;

public class CStrannayaFunktsiya {
    long MOD = (long) 1e9 + 7;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n = in.nextLong();
        long ans = 0;
        long left = n;
        long div = 1;
        for (int i = 2; i < (int)1e9; i++) {
            long z = div;
            div = Euclid.lcm(div, i);
            long md = div / z;

            ans = (ans + ((left - (left / md)) % MOD * i) % MOD) %MOD;
            left /= md;
            if (left == 0) {
//                System.err.println(i);
                break;
            }
        }
        out.println(ans);
    }
}
