package task;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DPriganieLyagushki {
    static final int N = (int)2e5;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int m = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int[] earliest = new int[N + 1];
        Arrays.fill(earliest, - 1);
        earliest[0] = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(x -> earliest[x]));
        queue.add(0);
        while (!queue.isEmpty()) {
            int val = queue.poll();
            if (val + a <= N && earliest[val + a] == -1) {
                earliest[val + a] = Math.max(val + a, earliest[val]);
                queue.add(val + a);
            }
            if (val > b && earliest[val - b] == -1) {
                earliest[val - b] = earliest[val];
                queue.add(val - b);
            }
        }
        long ans = 0;
        for (int i = 0; i < a; i++) {
            if (m < earliest[i] || earliest[i] == -1) {
                continue;
            }
            ans += ((earliest[i] * 1L - i) / a + 1) * (m - earliest[i] * 1L + 1);
            long st = i + ((earliest[i] * 1L - i) / a + 1) * a;
            if (st > m) {
                continue;
            }
            long ct = (m * 1L - st) / a + 1;
            ans += (m - st + 1) * ct - (ct - 1) * ct / 2 * a;
        }
        out.println(ans);
    }
}
