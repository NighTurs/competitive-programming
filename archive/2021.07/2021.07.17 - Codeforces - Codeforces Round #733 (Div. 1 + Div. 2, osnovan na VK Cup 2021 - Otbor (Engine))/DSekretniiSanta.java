package task;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class DSekretniiSanta {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        boolean[] c = new boolean[n + 1];
        int[] want = new int[n + 1];
        int[] ans = new int[n + 1];
        int[] inv = new int[n + 1];
        Queue<Integer> dup = new LinkedList<>();
        int k = 0;
        for (int i = 1; i <= n; i++) {
            int val = in.nextInt();
            want[i] = val;
            if (c[val]) {
                dup.add(i);
            } else {
                k++;
                ans[i] = val;
                inv[val] = i;
                c[val] = true;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (c[i]) {
                continue;
            }
            int from = dup.poll();
            if (i == from) {
                ans[from] = want[from];
                int dist = inv[want[from]];
                ans[dist] = i;

                inv[want[from]] = from;
                inv[i] = dist;

            } else {
                ans[from] = i;
                inv[i] = from;
            }
        }
        out.println(k);
        for (int i = 1; i <= n; i++) {
            out.print(ans[i] + " ");
        }
        out.println();
    }
}
