package task;

import java.io.PrintWriter;

public class BAquaMoonIUkradennayaStroka {
    final int N = 'z' - 'a' + 1;

    int[][] doit(int n, int m,  InputReader in) {
        int[][] count = new int[m][N];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int h = 0; h < m; h++) {
                count[h][s.charAt(h) - 'a']++;
            }
        }
        return count;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] ct1 = doit(n, m, in);
        int[][] ct2 = doit(n - 1, m, in);

        for (int i = 0; i < m; i++) {
            for (int h = 0; h < N; h++) {
                if (ct1[i][h] != ct2[i][h]) {
                    out.print((char) ('a' + h));
                }
            }
        }
        out.println();
        out.flush();
    }
}
