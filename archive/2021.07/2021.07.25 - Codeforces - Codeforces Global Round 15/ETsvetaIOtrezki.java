package task;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ETsvetaIOtrezki {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n * k];

        int m = (n + k - 2) / (k - 1);

        int[] prev = new int[n];
        int[] ct = new int[n * k];

        Arrays.fill(prev, -1);
        Map<Integer, Pair<Integer, Integer>> ans = new HashMap<>();


        for (int i = 0; i < n * k; i++) {
            int val = in.nextInt() - 1;
            if (prev[val] != -1 && !ans.containsKey(val)) {
                int max = Integer.MIN_VALUE;

                for (int h = prev[val]; h <= i; h++) {
                    ct[h]++;
                    max = Math.max(max, ct[h]);
                }
                if (max <= m) {
                    ans.put(val, Pair.of(prev[val], i));
                } else {
                    for (int h = prev[val]; h <= i; h++) {
                        ct[h]--;
                    }
                }
            }
            prev[val] = i;
        }
        for (int i = 0; i < n; i++) {
            Pair<Integer, Integer> p = ans.get(i);
            out.println((p.fs + 1) + " " + (p.sc + 1));
        }
    }
}
