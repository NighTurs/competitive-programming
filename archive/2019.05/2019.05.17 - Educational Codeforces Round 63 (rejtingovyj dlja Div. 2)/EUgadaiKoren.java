package task;

import java.io.PrintWriter;

public class EUgadaiKoren {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long[][] a = new long[50][12];
        long mod = (int) 1e6 + 3;
        for (int i = 1; i <= 50; i++) {
            out.println(String.format("? %d", i));
            out.flush();
            a[i - 1][11] = in.nextInt();
            for (int h = 0; h <= 10; h++) {
                a[i - 1][h] = BinPow.binPow(i, h, mod);
            }
        }
        long[] ans = new long[11];
        Gauss.gauss(a, ans, mod);
        for (int i = 0; i < mod; i++) {
            long sum = 0;
            for (int h = 0; h <= 10; h++) {
                sum = (sum + ans[h] * BinPow.binPow(i, h, mod)) % mod;
            }
            if (sum == 0) {
                out.println(String.format("! %d", i));
                return;
            }
        }
        out.println("! -1");
    }
}
