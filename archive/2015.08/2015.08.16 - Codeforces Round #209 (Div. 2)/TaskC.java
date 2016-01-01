package task;

import java.io.PrintWriter;
import java.util.PriorityQueue;

public class TaskC {
    final long MOD = (long) 1e9 + 7;
    final int N = (int) 1e5;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt();
        int a[] = new int[N];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            sum += a[i];
        }
        PriorityQueue<Long> pe = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pe.add(sum - a[i]);
        }

        int size = 1;
        long cur = pe.poll();
        while((size % m == 0) || (pe.size() > 0 && pe.peek() == cur)) {
            while (pe.size() > 0 && pe.peek() == cur) {
                size++;
                pe.poll();
            }
            if (size % m == 0) {
                size /= m;
                cur++;
            }
        }

        out.print(fast_pow(m, Math.min(sum, cur)));
    }

    public long fast_pow(long x, long pow) {
        if (pow == 0) {
            return 1;
        }
        return (fast_pow((x * x) % MOD, pow / 2) * (pow % 2 == 0 ? 1 : x)) % MOD;
    }
}
