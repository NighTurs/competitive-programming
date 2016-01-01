package task;

import task.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[101];
        for (int i = 0; i < n; i++) {
            a[in.nextInt()]++;
        }
        boolean allZero = false;
        int ans = 0;
        while (!allZero) {
            int boxes = 0;
            ans++;
            allZero = true;
            for (int i = 0; i <= 100; i++) {
                while (a[i] > 0 && boxes <= i) {
                    a[i]--;
                    boxes++;
                }
                if (a[i] > 0) {
                    allZero = false;
                }
            }
        }
        out.print(ans);
    }
}
