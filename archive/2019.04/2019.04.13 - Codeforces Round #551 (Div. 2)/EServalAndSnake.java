package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class EServalAndSnake {
    PrintWriter out;
    InputReader in;
    List<Pair<Integer, Integer>> ans;
    int n;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        this.out = out;
        this.in = in;
        n = in.nextInt();

        ans = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            Pair<Integer, Integer> res = process(i, false, true);
            if (res != null) {
                ans.add(res);
            }
        }

        for (int i = 1; i <= n - 1; i++) {
            Pair<Integer, Integer> res = process(i, true, true);
            if (res != null) {
                ans.add(res);
            }
        }
        if (ans.size() != 2) {
            ans.add(process(n, true, false));
        }
        StringBuilder sb = new StringBuilder("! ");
        for (Pair<Integer, Integer> p : ans) {
            sb.append(String.format("%d %d ", p.fs, p.sc));
        }
        out.println(sb);
        out.flush();
    }

    Pair<Integer, Integer> process(int row, boolean rev, boolean initQuery) {
        if (initQuery) {
            if (query(row, 1, row, n, rev) % 2 == 0) {
                return null;
            }
        }

        int t1 = 1;
        int t2 = n;
        while (t1 < t2) {
            int mid = (t1 + t2) / 2;
            if (query(row, t1, row, mid, rev) % 2 == 0) {
                t1 = mid + 1;
            } else {
                t2 = mid;
            }
        }
        return rev ? new Pair<>(t1, row) : new Pair<>(row, t1);
    }

    int query(int x, int y, int x1, int y1, boolean rev) {
        StringBuilder sb = new StringBuilder("? ");
        int app = 0;
        if (!rev) {
            sb.append(x);
            sb.append(' ');
            sb.append(y);
            sb.append(' ');
            sb.append(x1);
            sb.append(' ');
            sb.append(y1);
            app = append(x, y, x1, y1);
        } else {
            sb.append(y);
            sb.append(' ');
            sb.append(x);
            sb.append(' ');
            sb.append(y1);
            sb.append(' ');
            sb.append(x1);
            app = append(y, x, y1, x1);
        }
        out.println(sb);
        out.flush();
        return in.nextInt() + app;
    }

    int append(int x, int y, int x1, int y1) {
        int append = 0;
        for (Pair<Integer, Integer> p : ans) {
            if (p.fs >= x && p.fs <= x1 && p.sc >= y && p.sc <= y1) {
                append++;
            }
        }
        return append;
    }
}
