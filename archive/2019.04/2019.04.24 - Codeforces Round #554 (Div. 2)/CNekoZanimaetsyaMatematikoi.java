package task;

import java.io.PrintWriter;

public class CNekoZanimaetsyaMatematikoi {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long a = in.nextInt();
        long b = in.nextInt();
        if (a > b) {
            long z = a;
            a = b;
            b = z;
        }

        long diff = b - a;
        long min = Euclid.lcm(a, b);
        long kk = 0;
        for (int i = 1; i <= Math.sqrt(diff); i++) {
            if (diff % i == 0) {
                for (int j = 0; j < 2; j++) {
                    long g = j == 0 ? diff / i : i;
                    if (a % g != b % g) {
                        throw new RuntimeException("Impossible");
                    }
                    long k = (g - a % g) % g;
                    if ((a + k) * (b + k) / g < min) {
                        min = (a + k) * (b + k) / g;
                        kk = k;
                    }
                }
            }
        } out.println(kk);
    }
}
