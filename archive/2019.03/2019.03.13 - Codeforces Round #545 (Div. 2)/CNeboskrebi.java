package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CNeboskrebi {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < m; h++) {
                a[i][h] = in.nextInt();
            }
        }
        List<Pair<Integer, Integer>> hs = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            hs.add(new Pair<>(0, 0));
        }
        int[] pos = new int[m];
        int[][] rPos = new int[n][m];
        int[] rUnique = new int[n];

        for (int i = 0; i < n; i++) {
            for (int h = 0; h < m; h++) {
                hs.get(h).fs = a[i][h];
                hs.get(h).sc = h;
            }
            rUnique[i] = uniqueAndPos(hs, pos);
            for (int h = 0; h < m; h++) {
                rPos[i][h] = pos[h];
            }
        }
        hs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            hs.add(new Pair<>(0, 0));
        }
        pos = new int[n];
        int[][] cPos = new int[n][m];
        int[] cUnique = new int[m];
        for (int i = 0; i < m; i++) {
            for (int h = 0; h < n; h++) {
                hs.get(h).fs = a[h][i];
                hs.get(h).sc = h;
            }
            cUnique[i] = uniqueAndPos(hs, pos);
            for (int h = 0; h < n; h++) {
                cPos[h][i] = pos[h];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < m; h++) {
                int max = Math.max(rPos[i][h], cPos[i][h]);
                int rMax = rUnique[i];
                if (max > rPos[i][h]) {
                    rMax += max - rPos[i][h];
                }
                int cMax = cUnique[h];
                if (max > cPos[i][h]) {
                    cMax += max - cPos[i][h];
                }
                out.print(Math.max(rMax, cMax));
                out.print(' ');
            }
            out.println();
        }
    }

    private int uniqueAndPos(List<Pair<Integer, Integer>> hs, int[] pos) {
        hs.sort(Comparator.comparingInt(a -> a.fs));
        int unique = 1;
        pos[hs.get(0).sc] = 1;
        for (int i = 1; i < hs.size(); i++) {
            if (!hs.get(i).fs.equals(hs.get(i - 1).fs)) {
                unique++;
            }
            pos[hs.get(i).sc] = unique;
        }
        return unique;
    }
}
