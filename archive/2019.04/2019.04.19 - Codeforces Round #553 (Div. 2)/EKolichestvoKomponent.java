package task;

import java.io.PrintWriter;
import java.util.TreeSet;

public class EKolichestvoKomponent {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        TreeSet<Long> tree = new TreeSet<>();
        tree.add(a[n - 1]);
        long ans = 0;

        for (int i = n - 2; i >= 0; i--) {
            if (i != 0) {
                Long higher = tree.ceiling(a[i - 1]);
                Long lower = tree.floor(a[i - 1]);

                if (a[i] > a[i - 1]) {
                    if (higher == null || higher >= a[i]) {
                        higher = a[i];
                    }
                    if (lower == null) {
                        lower = 0L;
                    }
                    ans += (a[i] - higher) * a[i - 1] + (higher - a[i - 1]) * lower;
                } else if (a[i] < a[i - 1]) {
                    if (lower == null || lower <= a[i]) {
                        lower = a[i];
                    }
                    if (higher == null) {
                        higher = n + 1L;
                    }
                    ans += (lower - a[i]) * (n - a[i - 1] + 1) + (a[i - 1] - lower) * (n - higher + 1);
                }
            }
            tree.add(a[i]);
        }

        long prev = 0;

        for (Long val : tree) {
            ans += (val - prev) * (n - val + 1);
            prev = val;
        }

        out.println(ans);
    }
}

//x a[i- 1] y a[i]
//
//a[i] x a[i-1] y



