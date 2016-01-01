package task;

import task.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int x = in.nextInt();
        int[] a = new int[n];
        int[] orLeft = new int[n];
        int[] orRight = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            if (i == 0) {
                orLeft[i] = a[i];
            } else {
                orLeft[i] = orLeft[i - 1] | a[i];
            }
        }
        orRight[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            orRight[i] = orRight[i + 1] | a[i];
        }
        long max = orLeft[n - 1];
        for (int i = 0; i < n; i++) {
            long left = i == 0 ? 0 : orLeft[i - 1];
            long right = i == n - 1 ? 0 : orRight[i + 1];
            long cur = left | right;
            long num = a[i];
            for (int h = 0; h < k; h++) {
                num *= x;
                max = Math.max(max, cur | num);
            }
        }
        out.println(max);
    }
}
