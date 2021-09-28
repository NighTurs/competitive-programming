package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class E1MinimizatsiyaPerestanovkiDekom {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        List<Pair<Integer, Integer>> a = new ArrayList<>();
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            int val = in.nextInt();
            b[i] = val;
            a.add(Pair.of(val, i));
        }
        a.sort(Comparator.comparingInt(x -> x.fs));
        int[] where = new int[n];
        Arrays.fill(where, -1);

        for (Pair<Integer, Integer> p : a) {
            int pos = p.sc;
            if (where[pos] == -1) {
                where[pos] = 0;
                for (int i = pos + 1; i < n; i++) {
                    if (where[i] != -1) {
                        break;
                    }
                    where[i] = 1;
                }
            }
        }
        LinkedList<Integer> ans = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (where[i] == 0) {
                ans.addFirst(b[i]);
            } else {
                ans.addLast(b[i]);
            }
        }
        for (Integer v : ans) {
            out.print(v + " ");
        }
        out.println();
    }
}
