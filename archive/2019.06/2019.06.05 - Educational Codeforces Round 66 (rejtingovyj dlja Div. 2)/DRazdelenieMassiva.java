package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DRazdelenieMassiva {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        long[] cum = new long[n];
        cum[0] = a[0];
        for (int i = 1; i < n; i++) {
            cum[i] = cum[i - 1] + a[i];
        }

        List<Long> opts = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            opts.add(cum[n - 1] - cum[i]);
        }
        opts.sort(Comparator.comparingLong(x -> -x));
        long ans = cum[n - 1];

        for (int i = 0; i < k - 1; i++) {
            ans += opts.get(i);
        }

        out.println(ans);
    }
}
