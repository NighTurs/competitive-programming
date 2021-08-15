package task;

import java.io.PrintWriter;

public class CMochaIProgulka {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        if (a[0] == 1) {
            out.print(n + 1);
            for (int i = 1; i <= n; i++) {
                out.print(" " + i);
            }
        } else if (a[n - 1] == 0) {
            for (int i = 1; i <= n; i++) {
                out.print(i + " ");
            }
            out.print(n + 1);
        } else {
            boolean found = false;
            for (int i = 0; i < n - 1; i++) {
                if (a[i] == 0 && a[i + 1] == 1) {
                    for (int h = 1; h <= i + 1; h++) {
                        out.print(h + " ");
                    }
                    out.print(n + 1);
                    for (int h = i + 2; h <= n; h++) {
                        out.print(" " + h);
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                out.println(-1);
            }
        }
        out.println();
    }
}
