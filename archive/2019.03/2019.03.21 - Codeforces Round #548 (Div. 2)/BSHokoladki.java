package task;

import java.io.PrintWriter;

public class BSHokoladki {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        long sum = 0;
        long min = Long.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            long take = Math.max(Math.min(min - 1, a[i]), 0);
            sum += take;
            min = take;
        }
        out.println(sum);
    }
}
