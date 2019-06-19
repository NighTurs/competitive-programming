package task;

import java.io.PrintWriter;

public class C2EkzamenVBerGUUslozhnennayaVersiya {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int[] t = new int[101];

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            if (sum > m) {
                long ans = 0;
                long dif = sum - m;
                for (int h = 100; h >= 1; h--) {
                    long toTake = Math.min((dif + h - 1) / h, t[h]);
                    ans += toTake;
                    dif -= toTake * h;
                    if (dif <= 0) {
                        out.print(ans);
                        out.print(' ');
                        break;
                    }
                }
            } else {
                out.print("0 ");
            }
            t[a[i]]++;
        }
    }
}
