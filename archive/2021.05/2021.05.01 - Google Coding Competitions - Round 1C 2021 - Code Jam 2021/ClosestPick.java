package task;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ClosestPick {

    public static int k;

    public static int doit(Integer prev, Integer next) {
        if (prev == null) {
            return next - 1;
        }
        if (next == null) {
            return k - prev;
        }
        if (prev.equals(next)) {
            return 0;
        }
        int diff = next - prev - 1;
        int sum = (diff / 4) * 2;
        int m = diff % 4;
        if (m == 0) {
            return sum;
        }
        if (m <= 2) {
            return sum + 1;
        }
        return sum + 2;
    }

    public static int doit2(Integer prev, Integer next) {

        if (prev == null || next == null) {
            return 0;
        }
        if (prev.equals(next)) {
            return 0;
        }
        return next - prev - 1;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        k = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        Arrays.sort(a);
        Integer prev = null;
        int max2 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int res = doit(prev, a[i]);
            q.add(res);
            max2 = Math.max(max2, doit2(prev, a[i]));
            prev = a[i];
        }
        q.add(doit(a[n - 1], null));

        int ct = q.poll() + q.poll();
        ct = Math.max(ct, max2);
        out.println(String.format("Case #%d: %.9f", testNumber, ((double) ct) / k));
    }
}
