package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ESdvigPerestanovki {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] a = new int[n];
        int[] aw = new int[n];

        int[] ct = new int[n];
        for (int i = 0; i < n; i++) {

            a[i] = in.nextInt() - 1;
            aw[a[i]] = i;
            int shift = 0;
            if (a[i] <= i) {
                shift = i - a[i];
            } else {
                shift = i + (n - a[i]);
            }
            ct[shift] += 1;
        }

        int[] b = new int[n];
        int[] bw = new int[n];

        List<Integer> ans = new ArrayList<>();

        label:
        for (int shift = 0; shift < n; shift++) {
            int inPlace = ct[shift];
            int displaced = n - inPlace;
            if ((displaced + 1) / 2 > m) {
                continue;
            }
            System.arraycopy(a, 0, b, 0, n);
            System.arraycopy(aw, 0, bw, 0, n);


            int cur = shift == 0 ? 0 : n - shift;
            int swaps = 0;
            for (int i = 0; i < n; i++) {
                if (b[i] != cur) {
                    int is = bw[cur];
                    int z = b[i];
                    b[i] = b[is];
                    b[is] = z;
                    bw[b[i]] = i;
                    bw[b[is]] = is;
                    swaps++;
                    if (swaps > m) {
                        continue label;
                    }
                }
                cur++;
                if (cur >= n) {
                    cur = 0;
                }
            }
            ans.add(shift);
        }

        out.print(ans.size());
        for (Integer shift : ans) {
            out.print(" " + shift);
        }
        out.println();
    }
}
