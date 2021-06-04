package task;

import java.io.PrintWriter;

public class BPovelitelZnachenii {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        out.println(n / 2 * 6);
        for (int i = 1; i < n; i += 2) {
            out.println("1 " + i + " " + (i + 1));
            out.println("1 " + i + " " + (i + 1));
            out.println("2 " + i + " " + (i + 1));
            out.println("1 " + i + " " + (i + 1));
            out.println("1 " + i + " " + (i + 1));
            out.println("2 " + i + " " + (i + 1));
        }
    }
}
