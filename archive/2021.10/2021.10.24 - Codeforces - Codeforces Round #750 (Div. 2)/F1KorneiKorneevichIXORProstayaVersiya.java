package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class F1KorneiKorneevichIXORProstayaVersiya {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int M = 1024;
        int n = in.nextInt();
        int[] a = new int[M];
        Arrays.fill(a, -2);
        a[0] = -1;
        for (int i = 0; i < n; i++) {
            int val = in.nextInt();
            for (int h = 0; h < M; h++) {
                if (a[h] == -2 || a[h] >= val) {
                    continue;
                }
                int res = h ^ val;
                if (a[res] == -2) {
                    a[res] = val;
                } else {
                    a[res] = Math.min(val, a[res]);
                }
            }
        }
        int ct = 0;
        for (int i = 0; i < M; i++) {
            if (a[i] != -2) {
                ct++;
            }
        }
        out.println(ct);
        for (int i = 0; i < M; i++) {
            if (a[i] != -2) {
                out.print(i + " ");
            }
        }
        out.println();
    }
}
