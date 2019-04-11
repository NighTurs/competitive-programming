package task;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class BitParty {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int r = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        int[][] a = new int[c][3];

        long maxS = Integer.MIN_VALUE;
        long maxP = Integer.MIN_VALUE;
        for (int i = 0; i < c; i++) {
            a[i][0] = in.nextInt();
            a[i][1] = in.nextInt();
            a[i][2] = in.nextInt();
            maxS = Math.max(maxS, a[i][1]);
            maxP = Math.max(maxP, a[i][2]);
        }
        long t1 = 0;
        long t2 = b * maxS + b * maxP;
        while (t1 < t2) {
            long mid = (t1 + t2) / 2;
            if (possible(r, b, c, a, mid)) {
                t2 = mid;
            } else {
                t1 = mid + 1;
            }
        }
        out.println(String.format("Case #%d: %d", testNumber, t1));
    }

    public boolean possible(int r, int b, int c, int[][] a, long time) {
        long[] cc = new long[c];
        for (int i = 0; i < c; i++) {
            cc[i] = -Math.max(0, Math.min(a[i][0], (time - a[i][2]) / a[i][1]));
        }
        ArrayUtils.sort(cc);
        long sum = 0;
        for (int i = 0; i < r && i < c; i++) {
            sum -= cc[i];
        }
        return sum >= b;
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
