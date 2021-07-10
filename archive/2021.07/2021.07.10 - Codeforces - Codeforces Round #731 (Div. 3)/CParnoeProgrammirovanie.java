package task;

import java.io.PrintWriter;

public class CParnoeProgrammirovanie {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int k = in.nextInt();
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[m];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < m; i++) {
            b[i] = in.nextInt();
        }
        int[] ans = new int[n + m];
        int ai = 0;
        int bi = 0;
        int p = 0;
        while (ai < n || bi < m) {
            if (ai < n) {
                if (a[ai] == 0 || a[ai] <= k) {
                    ans[p++] = a[ai];
                    if (a[ai] == 0) {
                        k++;
                    }
                    ai++;
                    continue;
                }
            }
            if (bi < m) {
                if (b[bi] == 0 || b[bi] <= k) {
                    ans[p++] = b[bi];
                    if (b[bi] == 0) {
                        k++;
                    }
                    bi++;
                    continue;
                }
            }
            out.println(-1);
            return;
        }
        for (int i = 0; i < n + m; i++) {
            out.print(ans[i] + " ");
        }
        out.println();
    }
}
