package task;

import java.io.PrintWriter;

public class APutKNulyu {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        for (int t = 0; t < tt; t++) {
            long n = in.nextLong();
            long k = in.nextLong();
            long ans = 0;
            while (n != 0) {
                long mod = n % k;
                if (mod != 0) {
                    ans += mod;
                }
                n -= mod;
                if (n > 0) {
                    n /= k;
                    ans++;
                }
            }
            out.println(ans);
        }
    }
}
