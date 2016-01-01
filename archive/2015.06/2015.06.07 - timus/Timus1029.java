package task;

import task.InputReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Stack;

public class Timus1029 {
    final int N = 1000;
    long mem[][];
    int path[][];
    int a[][];
    int n, m;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        mem = new long[n][m];
        path = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(mem[i], -1L);
        }
        a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < m; h++) {
                a[i][h] = in.nextInt();
            }
        }
        long min = Long.MAX_VALUE;
        int ci = n - 1, ch = 0;
        for (int i = 0; i < m; i++) {
            if (min > doit(n - 1, i)) {
                min = doit(n - 1, i);
                ch = i;
            }
        }
        Stack<Integer> de = new Stack<>();
        while (ci != 0) {
            de.add(ch + 1);
            if (path[ci][ch] == ch) {
                ci--;
            } else {
                ch = path[ci][ch];
            }
        }
        de.add(ch + 1);

        while (de.size() > 0) {
            out.print(de.pop() + " ");
        }
    }

    private long doit(int i, int h) {
        if (i < 0 || h < 0 || h >= m) {
            return Long.MAX_VALUE - Integer.MAX_VALUE;
        }
        if (i == 0) {
            return a[i][h];
        }
        if (mem[i][h] != -1) {
            return mem[i][h];
        }
        mem[i][h] = doit(i - 1, h) + a[i][h];
        path[i][h] = h;
        if (mem[i][h] > doit(i, h - 1) + a[i][h]) {
            mem[i][h] = doit(i, h - 1) + a[i][h];
            path[i][h] = h - 1;
        }
        if (mem[i][h] > doit(i, h + 1) + a[i][h]) {
            mem[i][h] = doit(i, h + 1) + a[i][h];
            path[i][h] = h + 1;
        }
        return mem[i][h];
    }
}
