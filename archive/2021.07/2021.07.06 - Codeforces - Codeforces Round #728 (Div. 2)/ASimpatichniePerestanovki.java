package task;

import java.io.PrintWriter;

public class ASimpatichniePerestanovki {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }
        for (int i = 0; i < n -1; i += 2) {
            int z = a[i];
            a[i] = a[i + 1];
            a[i + 1] = z;
        }
        if (n % 2 != 0) {
            int z = a[n - 2];
            a[n - 2] = a[n - 1];
            a[n - 1] = z;
        }
        for (int i = 0; i < n; i++) {
            out.print(a[i] + " ");
        }
        out.println();
    }
}
