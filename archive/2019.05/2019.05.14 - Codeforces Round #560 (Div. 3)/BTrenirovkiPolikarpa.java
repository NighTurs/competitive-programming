package task;

import java.io.PrintWriter;

public class BTrenirovkiPolikarpa {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        ArrayUtils.sort(a);

        int idx = 0;
        for (int k = 1; k <= n + 1; k++) {
            while (idx < n && a[idx] < k) {
                idx++;
            }
            if (idx < n && a[idx] >= k) {
                idx++;
            } else {
                out.println(k - 1);
                return;
            }
        }
    }
}
