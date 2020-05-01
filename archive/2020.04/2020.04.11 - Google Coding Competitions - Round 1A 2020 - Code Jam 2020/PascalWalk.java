package task;

import java.io.PrintWriter;

public class PascalWalk {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        out.println("Case #" + testNumber + ":");
        out.println("1 1");
        if (n == 1) {
            return;
        }
        out.println("2 2");
        if (n == 2) {
            return;
        }
        out.println("3 3");
        if (n == 4) {
            out.println("4 4");
            return;
        }
        long taken = 3;
        int depth = 3;

        long[] cur = new long[]{1, 2, 1};
        long[] next = new long[3];

        while (true) {
            if (taken == n) {
                return;
            }
            calc(cur, next);
            if (next[0] + next[1] + next[2] > n - taken) {
                out.println(depth + " " + 2);
                taken += cur[1];
                if (taken == n) {
                    return;
                }
                while (n - taken >= depth) {
                    depth++;
                    taken += depth;
                    out.println(depth + " " + 2);
                }
                out.println(depth + " " + 1);
                taken++;
                while (taken != n) {
                    depth++;
                    out.println(depth + " " + 1);
                    taken++;
                }
                return;
            }
            depth++;
            out.println(depth + " " + 3);
            taken += next[2];
            cur[0] = next[0];
            cur[1] = next[1];
            cur[2] = next[2];
        }
    }

    public void calc(long[] cur, long[] next) {
        next[0] = 1;
        next[1] = cur[0] + cur[1];
        next[2] = cur[1] + cur[2];
    }
}
