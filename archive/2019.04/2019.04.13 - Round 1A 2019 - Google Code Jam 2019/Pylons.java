package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Pylons {

    int n, m;
    int testNumber;
    PrintWriter out;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        this.testNumber = testNumber;
        this.out = out;

        n = in.nextInt();
        m = in.nextInt();
        boolean swap = false;
        if (n > m) {
            int z = n;
            n = m;
            m = z;
            swap = true;
        }

        if (!((n > 1 && m > 4) || (m > 3 && n > 2))) {
            out.println(String.format("Case #%d: IMPOSSIBLE", testNumber));
            return;
        }

        out.println(String.format("Case #%d: POSSIBLE", testNumber));

        List<Pair<Integer, Integer>> moves = new ArrayList<>();

        int[] cur = new int[n];
        int offset = (n % 2 == 1 && n == m) ? 1 : 0;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                cur[i] = m - 1 - offset;
            } else {
                cur[i] = m - 3 - offset;
            }
            moves.add(new Pair<>(i, cur[i]));
            if (moves.size() > 1 && !checkValid(moves.get(moves.size() - 2).fs,
                    moves.get(moves.size() - 2).sc,
                    moves.get(moves.size() - 1).fs,
                    moves.get(moves.size() - 1).sc)) {
                out.println("BULLSHIT");
                return;
            }
        }

        while (moves.size() < n * m) {
            for (int i = 0; i < n; i++) {
                cur[i]++;
                if (cur[i] >= m) {
                    cur[i] = 0;
                }
                moves.add(new Pair<>(i, cur[i]));
                if (!checkValid(moves.get(moves.size() - 2).fs,
                        moves.get(moves.size() - 2).sc,
                        moves.get(moves.size() - 1).fs,
                        moves.get(moves.size() - 1).sc)) {
                    out.println("BULLSHIT");
                    return;
                }
            }
        }

        for (Pair<Integer, Integer> move : moves) {
            if (swap) {
                out.println(String.format("%d %d", move.sc + 1, move.fs + 1));
            } else {
                out.println(String.format("%d %d", move.fs + 1, move.sc + 1));
            }
        }
    }

    public boolean checkValid(int x1, int y1, int x2, int y2) {
        if (x1 == x2 || y1 == y2 || x1 + y1 == x2 + y2 || x1 - y1 == x2 - y2) {
            return false;
        }
        return true;
    }
}