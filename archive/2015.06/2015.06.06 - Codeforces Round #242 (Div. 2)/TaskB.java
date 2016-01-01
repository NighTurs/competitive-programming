package task;

import task.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt();
        int[][] a = new int[1100][2];
        for (int i = 0; i < n; i++) {
            int x = in.nextInt(), y = in.nextInt();
            int r = x * x + y * y;
            int p = in.nextInt();
            a[i][0] = r;
            a[i][1] = p;
        }
        Arrays.sort(a, 0, n, (o1, o2) -> o1[0] - o2[0]);
        int i = 0;
        while (m < 1000000 && i < n) {
            m += a[i++][1];
        }
        if (m < 1000000) {
            out.print(-1);
        } else {
            out.printf("%.7f", Math.sqrt(a[--i][0]));
        }
    }
}
