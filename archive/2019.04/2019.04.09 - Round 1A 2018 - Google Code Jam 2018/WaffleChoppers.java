package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class WaffleChoppers {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int nn = in.nextInt();
        int mm = in.nextInt();

        int[][] a = new int[n][m];
        int count = 0;
        for (int i = 0; i < n; i++) {
            String s = in.next();

            for (int h = 0; h < m; h++) {
                if (s.charAt(h) == '.') {
                    a[i][h] = 0;
                } else {
                    a[i][h] = 1;
                    count++;
                }
            }
        }

        if (count == 0) {
            out.println(String.format("Case #%d: POSSIBLE", testNumber));
            return;
        }

        int pieces = (nn + 1) * (mm + 1);
        if (count % pieces != 0) {
            out.println(String.format("Case #%d: IMPOSSIBLE", testNumber));
            return;
        }
        int pieceCt = count / pieces;
        int curCount = 0;
        List<Integer> cuts = new ArrayList<>();
        cuts.add(-1);
        int inVertical = pieceCt * (mm + 1);
        for (int i = 0; i < n; i++) {
            int prevCount = curCount;
            for (int h = 0; h < m; h++) {
                curCount += a[i][h];
            }
            if (prevCount == inVertical && curCount > inVertical) {
                cuts.add(i - 1);
                curCount = curCount - prevCount;
            } else if (prevCount != inVertical && curCount > inVertical) {
                out.println(String.format("Case #%d: IMPOSSIBLE", testNumber));
                return;
            }
        }
        if (curCount != inVertical) {
            out.println(String.format("Case #%d: IMPOSSIBLE", testNumber));
            return;
        }
        cuts.add(n - 1);

        if (cuts.size() != nn + 2) {
            out.println(String.format("Case #%d: IMPOSSIBLE", testNumber));
            return;
        }

        int[] counts = new int[nn + 1];

        int[] countIdx = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= cuts.size() - 1; j++) {
                if (i >= cuts.get(j - 1) + 1 && i <= cuts.get(j)) {
                    countIdx[i] = j - 1;
                    break;
                }
            }
        }

        int horizontalCuts = 0;
        for (int h = 0; h < m; h++) {
            for (int i = 0; i < n; i++) {
                counts[countIdx[i]] += a[i][h];
            }
            int ctEqual = 0;
            for (int i = 0; i < nn + 1; i++) {
                if (counts[i] > pieceCt) {
                    out.println(String.format("Case #%d: IMPOSSIBLE", testNumber));
                    return;
                }
                if (counts[i] == pieceCt) {
                    ctEqual++;
                }
            }
            if (ctEqual == nn + 1) {
                for (int i = 0; i < nn + 1; i++) {
                    counts[i] = 0;
                }
                horizontalCuts++;
            }
        }

        for (int i = 0; i < nn + 1; i++) {
            if (counts[i] != 0) {
                out.println(String.format("Case #%d: IMPOSSIBLE", testNumber));
                return;
            }
        }

        if (horizontalCuts != mm + 1) {
            out.println(String.format("Case #%d: IMPOSSIBLE", testNumber));
            return;
        }

        out.println(String.format("Case #%d: POSSIBLE", testNumber));
    }
}
