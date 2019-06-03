package task;

import java.io.PrintWriter;

public class CIhabIOsobayaZadachaORaskraske {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n + 1];
        int ct = 0;
        for (int i = 2; i <= n; i++) {
            if (a[i] != 0) {
                continue;
            }
            ct++;
            for (int h = i; h <= n; h += i) {
                if (a[h] == 0) {
                    a[h] = ct;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            out.print(a[i]);
            out.print(' ');
        }

    }
}
