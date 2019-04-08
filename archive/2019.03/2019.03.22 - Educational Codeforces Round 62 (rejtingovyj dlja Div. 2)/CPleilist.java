package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class CPleilist {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        List<Pair<Integer, Integer>> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(new Pair<>(in.nextInt(), in.nextInt()));
        }
        a.sort(Comparator.comparingInt(x -> -x.sc));

        PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x.fs));

        long curSum = 0;
        long max = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (queue.size() < k) {
                queue.add(a.get(i));
                curSum += a.get(i).fs;
            } else {
                if (queue.peek().fs < a.get(i).fs) {
                    curSum = curSum - queue.poll().fs + a.get(i).fs;
                    queue.add(a.get(i));
                }
            }

            max = Math.max(a.get(i).sc * curSum, max);
        }

        out.println(max);
    }
}
