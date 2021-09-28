package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class DProduktivnayaVstrecha {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((x, y) -> -Integer.compare(x.fs, y.fs));
        for (int i = 0; i < n; i++) {
            pq.add(Pair.of(in.nextInt(), i + 1));
        }
        List<Pair<Integer, Integer>> ans = new ArrayList<>();
        while (true) {
            Pair<Integer, Integer> first = pq.poll();
            Pair<Integer, Integer> second = pq.poll();
            if (first.fs > 0 && second.fs > 0) {
                ans.add(Pair.of(first.sc, second.sc));
            } else {
                break;
            }
            first.fs--;
            second.fs--;
            pq.add(first);
            pq.add(second);
        }
        out.println(ans.size());
        for (Pair<Integer, Integer> p : ans) {
            out.println(p.fs + " " + p.sc);
        }
    }
}
