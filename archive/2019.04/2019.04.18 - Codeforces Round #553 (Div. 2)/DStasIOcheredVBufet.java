package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DStasIOcheredVBufet {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        long[][] m = new long[n][2];
        for (int i = 0; i < n; i++) {
            m[i][0] = in.nextInt();
            m[i][1] = in.nextInt();
        }

        List<Integer> idxs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            idxs.add(i);
        }

        idxs.sort(Comparator.comparingLong(a -> m[a][1] - m[a][0]));
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int idx = idxs.get(i);
            ans += m[idx][0] * i + m[idx][1] * (n - i - 1);
        }
        out.println(ans);

    }
}
