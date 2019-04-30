package task;

import java.io.PrintWriter;

public class FMaksimalniiSbalansirovanniiKrug {
    static final int m = 2 * (int)1e5 + 10;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[m];
        for (int i = 0; i < n; i++) {
            a[in.nextInt()]++;
        }

        int[] cumsum = new int[m];
        cumsum[0] = a[0];
        for (int i = 1; i < m; i++) {
            cumsum[i] = cumsum[i - 1] + a[i];
        }
        int lastTwo = m - 1;
        int max = Integer.MIN_VALUE;
        int l = -1;
        int r = -1;
        for (int i = m - 1; i >= 0; i--) {
            if (a[i] > 0) {
                int next = i + 1;
                if (lastTwo != -1) {
                    next = lastTwo + 1;
                }
                if (a[next] == 0) {
                    next--;
                }
                int sum = cumsum[next] - cumsum[i - 1];
                if (sum > max) {
                    max = sum;
                    l = i;
                    r = next;
                }
            }
            if (a[i] > 1 && lastTwo == -1) {
                lastTwo = i;
            } else if (a[i] < 2) {
                lastTwo = -1;
            }
        }

        out.println(max);
        for (int i = l; i <= r; i++) {
            out.print(i);
            out.print(' ');

        }
        for (int i = r; i >= l; i--) {
            for (int h = 1; h < a[i]; h++) {
                out.print(i);
                out.print(' ');
            }
        }


    }
}
