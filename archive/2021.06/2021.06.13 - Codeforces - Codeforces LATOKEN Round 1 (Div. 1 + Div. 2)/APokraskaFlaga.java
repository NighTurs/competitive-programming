package task;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class APokraskaFlaga {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n][m];
        int ct = 0;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int h = 0; h < m; h++) {
                switch (s.charAt(h)) {
                    case '.':
                        a[i][h] = 0;
                        break;
                    case 'R':
                        a[i][h] = 1;
                        queue.add(Pair.of(i, h));
                        break;
                    case 'W':
                        a[i][h] = 2;
                        queue.add(Pair.of(i, h));
                        break;
                }
            }
        }
        if (queue.isEmpty()) {
            a[0][0] = 1;
            queue.add(Pair.of(0, 0));
        }
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> cur = queue.poll();
            int i = cur.fs;
            int h = cur.sc;
            int color = a[i][h];
            for (int j1 = -1; j1 <= 1; j1++) {
                for (int j2 = -1; j2 <= 1; j2++) {
                    if (j1 != 0 && j2 != 0) {
                        continue;
                    }
                    if (j1 == 0 && j2 == 0) {
                        continue;
                    }
                    int t1 = i + j1;
                    int t2 = h + j2;
                    if (t1 < 0 || t2 < 0 || t1 >= n || t2 >= m) {
                        continue;
                    }

                    if (a[t1][t2] == 0) {
                        a[t1][t2] = color == 1 ? 2 : 1;
                        queue.add(Pair.of(t1, t2));
                    }
                    if (a[t1][t2] == color) {
                        out.println("NO");
                        return;
                    }
                }
            }
        }
        out.println("YES");
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < m; h++) {
                switch (a[i][h]) {
                    case 1:
                        out.print("R");
                        break;
                    case 2:
                        out.print("W");
                        break;
                    default:
                        throw new RuntimeException();
                }
            }
            out.println();
        }

    }
}
