package task;

import java.io.PrintWriter;

public class CPolikarpVosstanavlivaetPerestanovku {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] q = new int[n];

        for (int i = 0; i < n - 1; i++) {
            q[i] = in.nextInt();
        }

        long plus = 0;
        long s = 0;
        int m = 1;
        for (int i = 0; i < n - 1; i++) {
            plus += q[i];
            s += plus;
            m++;
        }

        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }

        long[] a = new long[n];
        if ((sum - s) % m != 0) {
            out.println(-1);
            return;
        }

        int[] c = new int[n];
        a[0] = (sum - s) / m;

        for (int i = 1; i < n; i++) {
            a[i] = q[i - 1] + a[i - 1];
        }

        for (int i = 0; i < n; i++) {
            if (!inBounds(a[i], n) || c[(int)(a[i] - 1)] != 0) {
                out.println(-1);
                return;
            }
            c[(int)(a[i] - 1)] = 1;
        }

        for (int i = 0; i < n; i++) {
            out.print(a[i]);
            out.print(' ');
        }
    }

    boolean inBounds(long z, int n) {
        return z > 0 && z <= n;
    }
}
