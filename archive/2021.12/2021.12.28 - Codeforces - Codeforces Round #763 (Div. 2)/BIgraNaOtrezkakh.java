package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BIgraNaOtrezkakh {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        List<Pair<Integer, Integer>> a = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            a.add(Pair.of(from, to));
        }

        int[] b = new int[n + 1];
        a.sort(Comparator.comparingInt(x -> (x.sc - x.fs)));
        for (Pair<Integer, Integer> p : a) {
            int cand = -1;
            for (int i = p.fs; i <= p.sc; i++) {
                if (b[i] == 0) {
                    cand = i;
                    break;
                }
            }
            b[p.fs]++;
            b[p.sc]++;
            b[cand]++;
            out.println(p.fs + " " + p.sc + " " + cand);
        }
        out.println();
    }

}
