package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RoundingError {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int l = in.nextInt();

        boolean[] isRounded = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if ((i * 100 * 2 + n) / (2 * n) > i * 100 / n) {
                isRounded[i] = true;
            }
        }

        int[] req = new int[n + 1];
        int last = n + 1;
        for (int i = n; i >= 1; i--) {
            if (isRounded[i]) {
                last = i;
            }
            req[i] = last - i;
        }

        List<Integer> cc = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < l; i++) {
            int c = in.nextInt();
            cc.add(c);
            sum += c;
        }
        cc.sort(Comparator.comparingInt(a -> req[a]));

        int ans = 0;

        for (int i = 0; i < l; i++) {
            int c = cc.get(i);
            if (req[c] <= last && sum + req[c] <= n) {
                int instead = c + req[c];
                ans += percent(n, instead, isRounded);
                sum += req[c];
            } else {
                ans += percent(n, c, isRounded);
            }
        }
        while (sum + last <= n) {
            sum += last;
            ans += percent(n, last, isRounded);
        }
        ans += percent(n, n - sum, isRounded);
        out.println(String.format("Case #%d: %d", testNumber, ans));
    }

    int percent(int n, int i, boolean[] isRounded) {
        return i * 100 / n + (isRounded[i] ? 1 : 0);
    }
}
