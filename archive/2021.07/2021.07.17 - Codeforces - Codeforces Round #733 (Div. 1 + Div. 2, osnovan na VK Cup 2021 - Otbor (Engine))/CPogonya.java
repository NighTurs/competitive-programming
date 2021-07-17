package task;

import java.io.PrintWriter;

public class CPogonya {

    long[] cum(int[] v) {
        long[] c = new long[v.length];
        c[0] = v[0];
        for (int i = 1; i < v.length; i++) {
            c[i] = c[i - 1] + v[i];
        }
        return c;
    }


    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        long suma = 0;
        long sumb = 0;

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            suma += a[i];
        }
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
            sumb += b[i];
        }
        ArrayUtils.sort(a);
        ArrayUtils.sort(b);

        long[] cuma = cum(a);
        long[] cumb = cum(b);

        for (int i = 0; i <= n * 3; i++) {
            int r = n + i;
            int skip = r / 4;
            long scorea = suma - (skip == 0 ? 0 : cuma[skip - 1]) + 100L * i;

            int skipb = skip - Math.min(skip, i);
            long scoreb = sumb - (skipb == 0 ? 0 : cumb[skipb - 1]);
            if (scorea >= scoreb) {
                out.println(i);
                return;
            }
        }

    }
}
