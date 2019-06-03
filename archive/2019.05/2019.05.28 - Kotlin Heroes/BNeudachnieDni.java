package task;

import java.io.PrintWriter;
import java.util.PriorityQueue;

public class BNeudachnieDni {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int ct = 0;
        for (int i = 0; i < n; i++) {
            int val = in.nextInt();
            if (queue.size() > 1 && queue.peek() > val) {
                ct++;
            }
            queue.add(val);
            while (queue.size() > 2) {
                queue.poll();
            }
        }
        out.println(ct);
    }
}
