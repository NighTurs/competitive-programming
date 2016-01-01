package task;

import task.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[2000];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int left = 0, right = n - 1;
        int[] res = new int[2];
        int cur = 0;
        while (left <= right) {
            if (a[left] > a[right]) {
                res[cur] += a[left];
                left++;
            } else {
                res[cur] += a[right];
                right--;
            }

            cur = (cur + 1) % 2;
        }
        out.print(res[0] + " " + res[1]);
    }
}
