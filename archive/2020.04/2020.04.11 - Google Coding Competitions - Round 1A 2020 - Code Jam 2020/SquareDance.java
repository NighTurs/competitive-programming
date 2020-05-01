package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SquareDance {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Cell[][] cells = new Cell[n][m];
        long rSum = 0;
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < m; h++) {
                cells[i][h] = new Cell();
                cells[i][h].skill = in.nextInt();
                cells[i][h].removed = false;
                rSum += cells[i][h].skill;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < m; h++) {
                if (i != 0) {
                    cells[i][h].top = cells[i - 1][h];
                }
                if (i != n - 1) {
                    cells[i][h].bot = cells[i + 1][h];
                }
                if (h != 0) {
                    cells[i][h].left = cells[i][h - 1];
                }
                if (h != m - 1) {
                    cells[i][h].right = cells[i][h + 1];
                }
            }
        }

        List<Cell> cands = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int h = 0; h < m; h++) {
                if (cells[i][h].isCand()) {
                    cands.add(cells[i][h]);
                }
            }
        }
        long total = 0;
        List<Cell> newCands = new ArrayList<>();
        List<Cell> toCheck = new ArrayList<>();

        while (true) {
            total += rSum;
            if (cands.isEmpty()) {
                break;
            }
            newCands.clear();
            toCheck.clear();
            for (Cell cand : cands) {
                rSum -= cand.skill;
                if (cand.top != null) {
                    cand.top.bot = cand.bot;
                    if (!cand.top.removed) {
                        toCheck.add(cand.top);
                    }
                }
                if (cand.bot != null) {
                    cand.bot.top = cand.top;
                    if (!cand.bot.removed) {
                        toCheck.add(cand.bot);
                    }
                }
                if (cand.left != null) {
                    cand.left.right = cand.right;
                    if (!cand.left.removed) {
                        toCheck.add(cand.left);
                    }
                }
                if (cand.right != null) {
                    cand.right.left = cand.left;
                    if (!cand.right.removed) {
                        toCheck.add(cand.right);
                    }
                }
            }
            for (Cell cand : toCheck) {
                if (!cand.removed && cand.isCand()) {
                    newCands.add(cand);
                }
            }
            cands.clear();
            cands.addAll(newCands);
        }
        out.println(String.format("Case #%d: %d", testNumber, total));
    }

    public static class Cell {
        int skill;
        boolean removed;
        Cell top, bot, left, right;

        boolean isCand() {
            int sum = 0;
            int ct = 0;
            if (top != null) {
                ct++;
                sum += top.skill;
            }
            if (bot != null) {
                ct++;
                sum += bot.skill;
            }
            if (left != null) {
                ct++;
                sum += left.skill;
            }
            if (right != null) {
                ct++;
                sum += right.skill;
            }
            removed = skill * ct < sum;
            return removed;
        }
    }
}
