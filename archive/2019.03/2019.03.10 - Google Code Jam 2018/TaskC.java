package task;

import static task.Kuhn.kuhn;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskC {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        for (int tt = 0; tt < t; tt++) {
            int n = in.nextInt();
            int[] mt = new int[n];
            boolean[] used = new boolean[n];

            HashMap<Integer, List<Pair<Integer, Integer>>> costumes = new HashMap<>();
            for (int i = 0; i < n; i++) {
                for (int h = 0; h < n; h++) {

                    int costume = in.nextInt();
                    costumes.putIfAbsent(costume, new ArrayList<>());
                    costumes.get(costume).add(new Pair<>(i, h));
                }
            }
            int changes = 0;
            for (List<Pair<Integer, Integer>> vs : costumes.values()) {
                if (vs.size() == 1) {
                    continue;
                }
                List<List<Integer>> edges = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    edges.add(new ArrayList<>());
                }
                for (Pair<Integer, Integer> p : vs) {
                    edges.get(p.fs).add(p.sc);
                }

                kuhn(edges, mt, used);

                int left = 0;
                for (int i = 0; i < n; i++) {
                    if (mt[i] != -1) {
                        left++;
                    }
                }
                changes += vs.size() - left;
            }
            out.println(String.format("Case #%d: %d", tt + 1, changes));
        }
    }
}
