package task;

import task.InputReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class TaskD {
    final int N = 1000;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int k = in.nextInt();
        boolean a[][] = new boolean[N][N];
        int kn = 3;

        if (k == 1) {
            set(0, 1, a);
            print(out, a, 2);
            return;
        }

        set(1, 2, a);
        Queue<Integer> prev = new ArrayDeque<>();
        prev.add(2);
        int len = 1;

        String binary = Integer.toBinaryString(k);
        for (int i = 1; i < binary.length(); i++) {
            int t = kn++, b = kn++;
            while (prev.size() > 0) {
                int cur = prev.poll();
                set(cur, t, a);
                set(cur, b, a);
            }
            if (binary.charAt(i) == '1') {
                int pr = b;
                for (int h = 0; h < len; h++) {
                    set(pr, kn++, a);
                    pr = kn - 1;
                }
                set(pr, 1, a);
            }
            len++;
            prev.add(b);
            prev.add(t);
        }

        while (prev.size() > 0) {
            set(0, prev.poll(), a);
        }

        print(out, a, kn);
    }

    public void set(int i, int h, boolean[][] a) {
        a[i][h] = true;
        a[h][i] = true;
    }

    public void print(PrintWriter out, boolean[][] a, int n) {
        out.println(n);
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < n; h++) {
                if (a[i][h]) {
                    out.print('Y');
                } else {
                    out.print('N');
                }
            }
            out.println();
        }
    }
}
