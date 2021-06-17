package task;

import java.io.PrintWriter;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class ELostArray {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        Queue<Integer> q = new LinkedList<>();
        int[][] todo = new int[n + 1][3];
        boolean[] seen = new boolean[n + 1];
        q.add(0);
        seen[0] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i <= Math.min(k, cur); i++) {
                int left = k - i;
                if (cur + left > n) {
                    continue;
                }
                int to = cur - i + left;
                if (!seen[to]) {
                    seen[to] = true;
                    todo[to][0] = cur;
                    todo[to][1] = i;
                    todo[to][2] = left;
                    q.add(to);
                }
            }
        }

        if (!seen[n]) {
            out.println(-1);
            out.flush();
            return;
        }
        Deque<Integer> d = new LinkedList<>();
        int cur = n;
        while (cur != 0) {
            d.add(cur);
            cur = todo[cur][0];
        }
        Set<Integer> present = new HashSet<>();
        long xor = 0;
        while (!d.isEmpty()) {
            out.print("? ");
            int into = d.pollLast();
            int ctTaken = todo[into][1];
            int ctLeft = todo[into][2];
            for (int i = 1; i <= n; i++) {
                if (present.contains(i)) {
                    if (ctTaken > 0) {
                        ctTaken--;
                        present.remove(i);
                        out.print(i + " ");
                    }
                } else {
                    if (ctLeft > 0) {
                        present.add(i);
                        ctLeft--;
                        out.print(i + " ");
                    }
                }
            }
            out.println();
            out.flush();
            int val = in.nextInt();
            xor ^= val;
        }
        out.println("! " + xor);
        out.flush();
    }
}
