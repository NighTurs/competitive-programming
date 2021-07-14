package task;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CAquaMoonIStrannayaSortirovka {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        List<Pair<Integer, Integer>> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(Pair.of(in.nextInt(), i));
        }
        a.sort(Comparator.comparingInt(x -> x.fs));

        Map<Integer, Integer> ct = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Pair<Integer, Integer> p = a.get(i);
            ct.putIfAbsent(p.fs, 0);
            if (i % 2 == 0) {
                ct.put(p.fs, ct.get(p.fs) + 1);
            }
            if (p.sc % 2 == 0) {
                ct.put(p.fs, ct.get(p.fs) - 1);
            }
        }

        for (Integer integer : ct.values()) {
            if (integer != 0) {
                out.println("NO");
                return;
            }
        }
        out.println("YES");
    }


}
