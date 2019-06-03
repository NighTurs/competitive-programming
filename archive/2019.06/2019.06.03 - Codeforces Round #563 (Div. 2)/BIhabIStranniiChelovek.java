package task;

import java.io.PrintWriter;

public class BIhabIStranniiChelovek {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        boolean hasOdd = false;
        boolean hasEven = false;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            if (a[i] % 2 == 0) {
                hasEven = true;
            } else {
                hasOdd = true;
            }
        }
        if (hasEven && hasOdd) {
            ArrayUtils.sort(a);
        }
        for (int i = 0; i < n; i++) {
            out.print(a[i]);
            out.print(' ');
        }
    }
}
