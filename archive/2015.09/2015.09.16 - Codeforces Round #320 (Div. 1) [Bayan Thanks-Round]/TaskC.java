package task;

import task.InputReader;
import java.io.PrintWriter;

public class TaskC {
    double e = 1.e-7;

    public static double doit(int[] a, double x) {
        double max = 0;
        double curP = 0;
        double curN = 0;
        for (int i = 0; i < a.length; i++) {
            curP += a[i] - x;
            curN += a[i] - x;
            max = Math.max(max, curP);
            max = Math.max(max, -curN);
            if (curP < 0) {
                curP = 0;
            }
            if (curN > 0) {
                curN = 0;
            }
        }
        return max;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        double t1 = Integer.MAX_VALUE, t2 = Integer.MIN_VALUE;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            t1 = Math.min(a[i] * 1.0, t1);
            t2 = Math.max(a[i] * 1.0, t2);
        }
        for (int i = 0; i < 100; i++) {
            double third = (t2 - t1) / 3;
            double m1 = t1 + third;
            double m2 = t1 + 2 * third;
            if (doit(a, m1) > doit(a, m2)) {
                t1 = m1;
            } else {
                t2 = m2;
            }
        }
        out.println(String.format("%.12f", Math.min(doit(a, t1), doit(a, t2))));
    }
}
