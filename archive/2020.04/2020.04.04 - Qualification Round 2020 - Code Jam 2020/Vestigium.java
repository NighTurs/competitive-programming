package task;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class Vestigium {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int a[][] = new int[n][n];
        int out1 = 0;
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < n; h++) {
                a[i][h] = in.nextInt();
                if (i == h) {
                    out1 += a[i][h];
                }
            }
        }
        int outRow = 0;
        int outCol = 0;
        for (int i = 0; i < n; i++) {
            boolean repRow = false;
            boolean repCol = false;
            Set<Integer> sRow = new HashSet<>();
            Set<Integer> sCol = new HashSet<>();
            for (int h = 0; h < n; h++) {
                if (sRow.contains(a[i][h])) {
                    repRow = true;
                }
                if (sCol.contains(a[h][i])) {
                    repCol = true;
                }
                sRow.add(a[i][h]);
                sCol.add(a[h][i]);
            }
            outRow += repRow ? 1 : 0;
            outCol += repCol ? 1 : 0;
        }
        out.println(String.format("Case #%d: %d %d %d", testNumber, out1, outRow, outCol));
    }
}
