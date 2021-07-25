package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class CMaksimiziruitePeresecheniya {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[2 * n];
        Arrays.fill(a, -1);
        for (int i = 0; i < k; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            a[from] = to;
            a[to] = from;
        }
        int left = n - k;
        for (int i = 0; i < 2 * n; i++) {
            if (a[i] != -1) {
                continue;
            }
            int ct = 0;
            for (int h = i + 1; h < 2 * n; h++) {
                if (a[h] == -1) {
                    ct++;
                }
                if (ct == left) {
                    a[i] = h;
                    a[h] = i;
                    left--;
                    break;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 2 * n; i++) {
            if (i > a[i]) {
                continue;
            }
            for (int h = i + 1; h < 2 * n; h++) {
                if (h > a[h]) {
                    continue;
                }
                if (h < a[i] && a[h] > a[i]) {
                    ans++;
                }
            }
        }
        out.println(ans);
    }
}
