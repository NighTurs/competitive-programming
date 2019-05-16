package task;

import java.io.PrintWriter;
import java.util.LinkedList;

public class CRasprostranenieNovostei {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        int[] b = new int[n];
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            int k = in.nextInt();

            int min = Integer.MAX_VALUE;
            for (int h = 0; h < k; h++) {
                int val = in.nextInt() - 1;
                while (a[val] != val) {
                    queue.add(val);
                    val = a[val];
                }
                while (!queue.isEmpty()) {
                    a[queue.poll()] = val;
                }
                b[h] = val;
                min = Math.min(min, b[h]);
            }
            for (int h = 0; h < k; h++) {
                a[b[h]] = min;
            }
        }
        int[] ct = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = a[a[i]];
            ct[a[i]]++;
        }
        for (int i = 0; i < n; i++) {
            out.print(ct[a[i]]);
            out.print(' ');
        }
    }
}
