package task;

import java.io.PrintWriter;

public class CSlozhnieSkali {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        ArrayUtils.sort(a);
        int min = Integer.MAX_VALUE;
        int mi = 0;
        for (int i = 0; i < n - 1; i++) {
            if (Math.abs(a[i + 1] - a[i]) < min) {
                mi = i;
                min = Math.abs(a[i + 1] - a[i]);
            }
        }
        out.print(a[mi] + " ");
        for (int i = mi + 2; i < n; i++) {
            out.print(a[i] + " ");
        }
        for (int i = 0; i < mi; i++) {
            out.print(a[i] + " ");
        }
        out.print(a[mi + 1]);
        out.println();

    }
}
