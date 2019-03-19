package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class F2BlokiRavnoiSummiUslozhnennayaRedakciya {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] sum = new int[n];
        int prev = 0;
        for (int i = 0; i < n; i++) {
            sum[i] = in.nextInt() + prev;
            prev = sum[i];
        }

        HashMap<Integer, List<Pair<Integer, Integer>>> blocks = new HashMap<>();


        int max = 0;
        int maxS = 0;
        for (int i = 0; i < n; i++) {
            for (int h = i; h >= 0; h--) {
                int s = sum[i];
                if (h > 0) {
                    s -= sum[h - 1];
                }
                blocks.putIfAbsent(s, new ArrayList<>());
                List<Pair<Integer, Integer>> l = blocks.get(s);
                if (l.isEmpty() || l.get(l.size() - 1).sc < h) {
                    l.add(new Pair<>(h, i));
                }
                if (l.size() > max) {
                    max = l.size();
                    maxS = s;
                }
            }
        }

        out.println(max);
        for (int i = 0; i < max; i++) {
            out.println(String.format("%d %d", blocks.get(maxS).get(i).fs + 1, blocks.get(maxS).get(i).sc + 1));
        }
    }
}
