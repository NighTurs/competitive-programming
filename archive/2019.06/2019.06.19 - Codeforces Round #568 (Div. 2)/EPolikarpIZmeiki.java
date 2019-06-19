package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class EPolikarpIZmeiki {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        outer:
        for (int t = 0; t < tt; t++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[][] c = new int['z' - 'a' + 1][4]; // rmin rmax cmin cmax
            for (int i = 0; i <= 'z' - 'a'; i++) {
                for (int h = 0; h < 4; h++) {
                    c[i][h] = h % 2 == 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
            }

            char[][] a = new char[n][m];
            for (int i = 0; i < n; i++) {
                String s = in.next();
                for (int h = 0; h < m; h++) {
                    a[i][h] = s.charAt(h);
                    if (a[i][h] == '.') {
                        continue;
                    }
                    int charIdx = a[i][h] - 'a';
                    c[charIdx][0] = Math.min(c[charIdx][0], i);
                    c[charIdx][1] = Math.max(c[charIdx][1], i);
                    c[charIdx][2] = Math.min(c[charIdx][2], h);
                    c[charIdx][3] = Math.max(c[charIdx][3], h);
                }
            }

            List<Item> ans = new ArrayList<>();
            for (int charIdx = 'z' - 'a'; charIdx >= 0; charIdx--) {
                if (c[charIdx][0] == Integer.MAX_VALUE) {
                    if (!ans.isEmpty()) {
                        ans.add(ans.get(0));
                    }
                    continue;
                }
                if (c[charIdx][0] != c[charIdx][1] && c[charIdx][2] != c[charIdx][3]) {
                    out.println("NO");
                    continue outer;
                }
                for (int i = c[charIdx][0]; i <= c[charIdx][1]; i++) {
                    for (int h = c[charIdx][2]; h <= c[charIdx][3]; h++) {
                        if (a[i][h] == '.' || a[i][h] - 'a' < charIdx) {
                            out.println("NO");
                            continue outer;
                        }
                    }
                }
                ans.add(new Item(c[charIdx][0] + 1, c[charIdx][1] + 1, c[charIdx][2] + 1, c[charIdx][3] + 1));
            }
            out.println("YES");
            out.println(ans.size());
            for (int i = ans.size() - 1; i >= 0; i--) {
                out.println(String.format("%d %d %d %d", ans.get(i).rl, ans.get(i).cl, ans.get(i).rr, ans.get(i).cr));
            }
        }
    }

    static class Item {

        int rl, rr, cl, cr;

        public Item(int rl, int rr, int cl, int cr) {
            this.rl = rl;
            this.rr = rr;
            this.cl = cl;
            this.cr = cr;
        }
    }
}
