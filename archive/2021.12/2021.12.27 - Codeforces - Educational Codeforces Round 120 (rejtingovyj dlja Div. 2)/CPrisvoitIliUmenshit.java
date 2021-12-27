package task;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class CPrisvoitIliUmenshit {
    long MAX = (long)1e10 + 10;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long k = in.nextLong();
        long[] a = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }
        ArrayUtils.sort(a);

        long[] cum = new long[n];
        cum[0] = a[0];
        for (int i = 1; i < n; i++) {
            cum[i] = cum[i - 1] + a[i];
        }

        long all = cum[n - 1];
        long min = a[0];

        long mmin = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            long t1 = 0;
            long t2 = MAX * 2;
            while (t1 < t2) {
                long mid = (t1 + t2) / 2;
                long curmin = min - mid;
                long was = all - cum[i - 1];
                long tobe = curmin * (n - i);
                long overall = all - min + curmin - was + tobe;
                if (overall > k) {
                    t1 = mid + 1;
                } else {
                    t2 = mid;
                }
            }
            mmin = Math.min(mmin, n - i + t1);
        }
        out.println(mmin);
    }

    public static class ArrayUtils {

        static final long seed = System.nanoTime();
        static final Random rand = new Random(seed);

        public static void sort(long[] a) {
            shuffle(a);
            Arrays.sort(a);
        }

        public static void shuffle(long[] a) {
            for (int i = 0; i < a.length; i++) {
                int j = rand.nextInt(i + 1);
                long t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
    }

}
