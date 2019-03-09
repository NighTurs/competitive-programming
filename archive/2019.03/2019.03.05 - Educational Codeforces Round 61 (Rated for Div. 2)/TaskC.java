package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskC {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int l = in.nextInt() - 1;
            int r = in.nextInt() - 1;
            pairs.add(new Pair<>(l, r));
            for (int h = l; h <= r; h++) {
                a[h]++;
            }
        }
        int total = n;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                total--;
            }
        }
        int[] sum1 = new int[n];
        int[] sum2 = new int[n];
        int s1 = 0;
        int s2 = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 1) {
                s1 += 1;
            }
            if (a[i] == 2) {
                s2 += 1;
            }
            sum1[i] = s1;
            sum2[i] = s2;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int h = 0; h < m; h++) {
                if (i == h) {
                    continue;
                }
                int l1 = pairs.get(i).fs;
                int r1 = pairs.get(i).sc;
                int l2 = pairs.get(h).fs;
                int r2 = pairs.get(h).sc;
                if (l2 < l1) {
                    int z = l1;
                    l1 = l2;
                    l2 = z;
                    z = r1;
                    r1 = r2;
                    r2 = z;
                }
                int ones1 = count(sum1, l1, r1);
                int ones2 = count(sum1, l2, r2);
                int twos = 0;
                if (l2 <= r1) {
                    twos = count(sum2, l2, Math.min(r1, r2));
                }
                int cand = total - ones1 - ones2 - twos;
                if (max < cand) {
                    max = cand;
                }
            }
        }
        out.println(max);
    }

    private int count(int[] s, int l, int r) {
        if (l == 0) {
            return s[r];
        }
        return s[r] - s[l - 1];
    }
}
