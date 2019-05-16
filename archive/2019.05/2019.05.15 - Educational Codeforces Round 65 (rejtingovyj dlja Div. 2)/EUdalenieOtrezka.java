package task;

import java.io.PrintWriter;
import java.util.Arrays;

public class EUdalenieOtrezka {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        int max = Integer.MIN_VALUE;
        int[] dMax = new int[x + 1];
        Arrays.fill(dMax, Integer.MIN_VALUE);
        for (int i = 0; i < n; i++) {
            max = Math.max(max, a[i]);
            dMax[a[i]] = Math.max(dMax[a[i]], max);
        }
        int[] dMin = new int[x + 1];
        Arrays.fill(dMin, Integer.MAX_VALUE);
        int min = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            min = Math.min(min, a[i]);
            dMin[a[i]] = Math.min(dMin[a[i]], min);
        }

        int[] cumMin = new int[x + 2];
        int[] cumMax = new int[x + 2];
        cumMin[x + 1] = Integer.MAX_VALUE;
        cumMax[x + 1] = Integer.MIN_VALUE;
        for (int i = x; i >= 1; i--) {
            cumMin[i] = dMin[i] == i ? Integer.MAX_VALUE : dMin[i];
            cumMax[i] = dMax[i] == i ? Integer.MIN_VALUE : dMax[i];
            cumMin[i] = Math.min(cumMin[i], cumMin[i + 1]);
            cumMax[i] = Math.max(cumMax[i], cumMax[i + 1]);
        }
        int leftMin = Integer.MAX_VALUE;
        int leftMax = Integer.MIN_VALUE;
        long ans = 0;
        for (int i = 1; i <= x; i++) {
            if (leftMin != Integer.MAX_VALUE) {
                break;
            }
            int t1 = leftMax == Integer.MIN_VALUE ? i : leftMax;
            int t2 = x + 1;
            while (t1 < t2) {
                int m = (t1 + t2) / 2;
                if (cumMax[m + 1] == Integer.MIN_VALUE && cumMin[m + 1] >= i) {
                    t2 = m;
                } else {
                    t1 = m + 1;
                }
            }
            if (t1 != x + 1) {
                ans += x - t1 + 1;
            }
            leftMin = Math.min(leftMin, dMin[i] == i ? Integer.MAX_VALUE : dMin[i]);
            leftMax = Math.max(leftMax, dMax[i] == i ? Integer.MIN_VALUE : dMax[i]);
        }
        out.println(ans);
    }
}
