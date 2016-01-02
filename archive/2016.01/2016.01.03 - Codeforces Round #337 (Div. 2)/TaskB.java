package task;

import task.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (min > a[i]) {
                min = a[i];
            }
        }
        int firstFree = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] != min) {
                firstFree++;
            } else {
                break;
            }
        }
        int curFree = 0;
        int maxFree = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (a[i] != min) {
                curFree++;
            } else {
                if (maxFree < curFree) {
                    maxFree = curFree;
                }
                curFree = 0;
            }
        }
        if (maxFree < curFree + firstFree) {
            maxFree = curFree + firstFree;
        }
        out.print((long) min * n + maxFree);
    }
}
