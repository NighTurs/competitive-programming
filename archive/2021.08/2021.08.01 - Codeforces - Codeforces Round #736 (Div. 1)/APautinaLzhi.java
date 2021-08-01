package task;

import java.io.PrintWriter;

public class APautinaLzhi {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n + 1];

        int ans = n;
        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            if (from < to) {
                if (a[from] == 0) {
                    ans--;
                }
                a[from]++;
            } else {
                if (a[to] == 0) {
                    ans--;
                }
                a[to]++;
            }
        }
        int qq = in.nextInt();
        for (int i = 0; i < qq; i++) {
            int type = in.nextInt();
            if (type == 3) {
                out.println(ans);
                continue;
            }
            int from = in.nextInt();
            int to = in.nextInt();
            if (from > to) {
                int z = from;
                from = to;
                to = z;
            }
            if (type == 1) {
                if (a[from] == 0) {
                    ans--;
                }
                a[from]++;
            } else {
                if (a[from] == 1) {
                    ans++;
                }
                a[from]--;
            }
        }
    }
}
