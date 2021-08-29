package task;

import java.io.PrintWriter;

public class CSzhatayaSkobochnayaPosledovatelnost {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        long ans = 0;
        for (int st = 0; st < n; st+=2) {
            long open1 = a[st];
            long open2 = 0;
            long add = 0;
            for (int cur = st + 1; cur < n; cur++) {

                if (cur % 2 == 0) {
                    open2 += a[cur];
                } else {
                    long close = a[cur];
                    long d = Math.min(open2, close);
                    open2 -= d;
                    close -= d;

                    if (open2 == 0) {
                        add += Math.min(open1, close) + (add > 0 ? 1 : 0);
                    }

                    d = Math.min(open1, close);
                    open1 -= d;
                    close -= d;

                    if (close > 0) {
                        break;
                    }
                }
            }
            ans += add;
        }
        out.println(ans);
    }
}
