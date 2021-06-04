package task;

import java.io.PrintWriter;

public class AFeniksIZoloto {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        label:
        for (int t = 0; t < tt; t++) {
            int n = in.nextInt();
            int x = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            ArrayUtils.sort(a);

            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (sum + a[i] == x) {
                    sum -= a[0];
                    sum += a[i];
                    int z = a[0];
                    a[0] = a[i];
                    a[i] = z;

                    a[i] = a[n - 1];
                    a[n - 1] = z;
                }
                if (sum + a[i] == x) {
                    out.println("NO");
                    continue label;
                }
                sum += a[i];
            }

            out.println("YES");
            for (int i = 0; i < n; i++) {
                out.print(a[i] + " ");
            }
            out.println();
        }
    }
}
