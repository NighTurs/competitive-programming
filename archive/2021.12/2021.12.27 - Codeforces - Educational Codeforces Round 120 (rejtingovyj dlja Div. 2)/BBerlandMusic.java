package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BBerlandMusic {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        List<Pair<Integer, Integer>> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(Pair.of(i, in.nextInt()));
        }
        String s = in.next();
        List<Pair<Integer, Integer>> b = new ArrayList<>();
        List<Pair<Integer, Integer>> c = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                b.add(a.get(i));
            } else {
                c.add(a.get(i));
            }
        }
        b.sort(Comparator.comparingInt(x -> x.sc));
        c.sort(Comparator.comparingInt(x -> x.sc));
        int[] ans = new int[n];
        for (int i = 0; i < b.size(); i++) {
            ans[b.get(i).fs] = i + 1;
        }
        for (int i = 0; i < c.size(); i++) {
            ans[c.get(i).fs] = b.size() + i + 1;
        }
        for (int i = 0; i < n; i++) {
            out.print(ans[i] + " ");
        }
        out.println();

    }
}
