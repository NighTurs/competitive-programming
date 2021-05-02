package task;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DoubleOrNOTing {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String a = in.next();
        String b = in.next();

        int aa = Integer.valueOf(a, 2);
        int bb = Integer.valueOf(b, 2);


        Map<Long, Integer> mp = new HashMap<>();
        PriorityQueue<Pair<Integer, Long>> queue = new PriorityQueue<>((x, y) -> Long.compare(x.fs, y.fs));
        queue.add(Pair.of(0, (long) aa));
        mp.put((long) aa, 0);

        while (!queue.isEmpty()) {
            Pair<Integer, Long> p = queue.poll();
            int step = p.fs;
            long val = p.sc;

            if (step > 20) {
                break;
            }

            if (mp.getOrDefault(val, Integer.MAX_VALUE) < step) {
                continue;
            }

            long n = val * 2;
            if (mp.getOrDefault(n, Integer.MAX_VALUE) > step + 1) {
                mp.put(n, step + 1);
                queue.add(Pair.of(step + 1, n));
            }

            String st = Long.toBinaryString(val);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < st.length(); i++) {
                sb.append(st.charAt(i) - '0' == 0 ? 1 : 0);
            }
            n = Long.valueOf(sb.toString(), 2);

            if (mp.getOrDefault(n, Integer.MAX_VALUE) > step + 1) {
                mp.put(n, step + 1);
                queue.add(Pair.of(step + 1, n));
            }

            if (mp.containsKey((long)bb)) {
                out.println(String.format("Case #%d: %d", testNumber, mp.get((long)bb)));
                return;
            }
        }
        out.println(String.format("Case #%d: %s", testNumber, "IMPOSSIBLE"));
    }
}
