package task;

import java.io.PrintWriter;

public class BMaksimalniiNeprerivniiOtdih {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int count = 0;
        int max = -1;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            if (a[i] == 1) {
                count++;
            } else {
                count = 0;
            }
            max = Math.max(count, max);
        }
        for (int i = 0; i < n; i++) {
            if (a[i] == 1) {
                count++;
            } else {
                count = 0;
            }
            max = Math.max(count, max);
        }
        out.println(max);
    }
}
