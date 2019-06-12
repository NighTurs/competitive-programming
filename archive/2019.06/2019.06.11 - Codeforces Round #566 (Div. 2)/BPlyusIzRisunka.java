package task;

import java.io.PrintWriter;

public class BPlyusIzRisunka {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            for (int h = 0; h < s.length(); h++) {
                a[i][h] = s.charAt(h) == '*' ? 1 : 0;
            }
        }

        boolean found = false;
        outer:
        for (int i = 1; i < n - 1; i++) {
            for (int h = 1; h < m - 1; h++) {
                if (a[i][h] == 1 && a[i - 1][h] == 1 && a[i + 1][h] == 1 && a[i][h + 1] == 1 && a[i][h - 1] == 1) {
                    a[i][h] = 0;
                    int j = i - 1;
                    while (j >= 0 && a[j][h] == 1) {
                        a[j][h] = 0;
                        j--;
                    }
                    j = i + 1;
                    while (j < n && a[j][h] == 1) {
                        a[j][h] = 0;
                        j++;
                    }
                    j = h - 1;
                    while (j >= 0 && a[i][j] == 1) {
                        a[i][j] = 0;
                        j--;
                    }
                    j = h + 1;
                    while (j < m && a[i][j] == 1) {
                        a[i][j] = 0;
                        j++;
                    }
                    found = true;
                    break outer;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < m; h++) {
                if (a[i][h] == 1) {
                    found = false;
                }
            }
        }
        out.println(found ? "YES" : "NO");
    }
}
