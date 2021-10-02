package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class C {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[] a = new long[n];
        long alla = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            alla += a[i];
        }
        long[] b = new long[n];

        long min = Long.MAX_VALUE;
        long allb = 0;
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
            allb += b[i];
            long ct = 0;
            if (b[i] == 0) {
                ct = alla;
            } else {
                ct = a[i] / b[i];
            }
            min = Math.min(ct, min);
        }
        while (min >= 1) {
            long left = alla - allb * min;
            if (left % min == 0) {
                break;
            }
            min -= 1;
        }

        List<Integer> def = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a[i] -= min * b[i];
            for (int h = 0; h < b[i]; h++) {
                def.add(i + 1);
            }
        }

        int cur = 0;
        long delta = (alla - allb * min) / min;
        out.println(min + " " + (allb + delta));
        for (int i = 0; i < min; i++) {
            for (Integer val : def) {
                out.print(val + " ");
            }
            for (int h = 0; h < delta; h++) {
                while (a[cur] == 0) {
                    cur++;
                }
                out.print((cur + 1) + " ");
                a[cur]--;
            }
            out.println();
        }
    }
}
