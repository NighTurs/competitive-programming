package task;

import task.InputReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        boolean[][] a = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int h = 0; h < m; h++) {
                a[i][h] = s.charAt(h) == '.';
            }
        }
        boolean[][] visited = new boolean[n][m];
        Ans[][] ans = new Ans[n][m];
        ArrayDeque<Pair<Integer, Integer>> steps = new ArrayDeque<>();
        for (int ci = 0; ci < n; ci++) {
            for (int ch = 0; ch < m; ch++) {
                if (!visited[ci][ch] && a[ci][ch]) {
                    int pics = 0;
                    Ans cAns = new Ans();
                    steps.addLast(Pair.of(ci, ch));
                    visited[ci][ch] = true;
                    while(!steps.isEmpty()) {
                        int i = steps.peekFirst().fs;
                        int h = steps.peekFirst().sc;
                        ans[i][h] = cAns;
                        steps.pollFirst();
                        for (int j1 = -1; j1 <= 1; j1++) {
                            for (int j2 = -1; j2 <= 1; j2++) {
                                if (j1 == 0 ^ j2 == 0) {
                                    int ti = i + j1;
                                    int th = h + j2;
                                    if (visited[ti][th]) {
                                        continue;
                                    }
                                    if (a[ti][th]) {
                                        visited[ti][th] = true;
                                        steps.addLast(Pair.of(ti, th));
                                    } else {
                                        pics++;
                                    }
                                }
                            }
                        }
                    }
                    cAns.x = pics;
                }
            }
        }

        for (int i = 0; i < k; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            out.println(ans[x][y].x);
        }
    }

    public static class Ans {
        public int x;
    }
}
