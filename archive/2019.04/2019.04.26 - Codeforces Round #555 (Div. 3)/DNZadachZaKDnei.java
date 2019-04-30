package task;

import java.io.PrintWriter;

public class DNZadachZaKDnei {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n = in.nextInt();
        long k = in.nextInt();
        long minSum = (1 + k) * k / 2;
        if (minSum > n) {
            out.println("NO");
            return;
        }
        long rest = n - minSum;
        long inc = rest / k;
        rest -= inc * k;

        long[] ans = new long[(int)k];
        ans[0] = 1 + inc;
        for (int i = 1; i < k; i++) {
            ans[i] = ans[i - 1] + 1;
        }
        int cur = (int)k - 1;
        while (rest > 0) {
            if ((cur == 0 || ans[cur] + 1 <= ans[cur - 1] * 2) && (cur == k - 1 || ans[cur] + 1 < ans[cur + 1])) {
                ans[cur]++;
                rest--;
            } else {
                cur--;
                if (cur == -1) {
                    out.println("NO");
                    return;
                }
            }
        }
        out.println("YES");
        for (int i = 0; i < k; i++) {
            out.print(ans[i]);
            out.print(' ');
        }
    }
}
