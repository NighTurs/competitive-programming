package task;

import java.io.PrintWriter;

public class TaskA {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        outer:
        for (int tt = 0; tt < t; tt++) {
            int n = in.nextInt();
            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            if (a[0] == 0 || a[n - 1] == 0) {
                out.println(String.format("Case #%d: IMPOSSIBLE", tt + 1));
                continue;
            }
            boolean[] used = new boolean[n];
            int[] dest = new int[n];
            int maxDist = 0;
            for (int i = 0; i < n; i++) {
                int toTake = a[i];
                for (int h = 0; h < n; h++) {
                    if (!used[h] && toTake > 0) {
                        toTake--;
                        used[h] = true;
                        dest[h] = i;
                        if (maxDist < Math.abs(h - i)) {
                            maxDist = Math.abs(h - i);
                        }
                    }
                }
            }
            char[][] map = new char[maxDist + 1][n];
            for (int i = 0; i < maxDist + 1; i++) {
                for (int h = 0; h < n; h++) {
                    map[i][h] = '.';
                }
            }
            for (int i = 0; i < n; i++) {
                int cur = i;
                int row = 0;
                while (cur != dest[i]) {
                    if (cur < dest[i]) {
                        map[row][cur] = '\\';
                        cur++;
                    } else {
                        map[row][cur] = '/';
                        cur--;
                    }
                    row++;
                }
            }
            out.println(String.format("Case #%d: %d", tt + 1, maxDist + 1));
            for (int i = 0; i < maxDist + 1; i++) {
                for (int h = 0; h < n; h++) {
                    out.print(map[i][h]);
                }
                out.println();
            }
        }
    }
}
