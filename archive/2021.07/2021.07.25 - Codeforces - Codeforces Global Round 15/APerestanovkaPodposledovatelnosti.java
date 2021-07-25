package task;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class APerestanovkaPodposledovatelnosti {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        char[] a = in.next().toCharArray();
        char[] b = a.clone();
        ArrayUtils.sort(b);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) {
                ans++;
            }
        }
        out.println(ans);
    }

    public static class ArrayUtils {

        static final long seed = System.nanoTime();
        static final Random rand = new Random(seed);

        public static void sort(char[] a) {
            shuffle(a);
            Arrays.sort(a);
        }

        public static void shuffle(char[] a) {
            for (int i = 0; i < a.length; i++) {
                int j = rand.nextInt(i + 1);
                char t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
    }

}
