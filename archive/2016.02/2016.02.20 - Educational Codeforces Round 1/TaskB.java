package task;

import task.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        StringBuilder s = new StringBuilder(in.next());
        StringBuilder sp = new StringBuilder(s);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int l = in.nextInt() - 1;
            int r = in.nextInt() - 1;
            int m = r - l + 1;
            int k = in.nextInt();
            sp.insert(0, s);
            for (int j = 0; j < m; j++) {
                s.setCharAt(l + (j + k) % m, sp.charAt(l + j));
            }
        }
        out.println(s);
    }
}
