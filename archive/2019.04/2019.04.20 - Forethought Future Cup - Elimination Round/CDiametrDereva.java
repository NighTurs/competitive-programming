package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CDiametrDereva {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();

        for (int tt = 0; tt < t; tt++) {
            int n = in.nextInt();

            int max = 0;
            for (int i = 64; i >= 1; i /= 2) {

                List<Integer> first = new ArrayList<>();
                List<Integer> second = new ArrayList<>();

                for (int h = 1; h <= n; h++) {
                    if (((h - 1) / i) % 2 == 0) {
                        first.add(h);
                    } else {
                        second.add(h);
                    }
                }
                if (first.isEmpty() || second.isEmpty()) {
                    continue;
                }

                StringBuilder sb = new StringBuilder();
                sb.append(String.format("%d %d", first.size(), second.size()));
                for (Integer h : first) {
                    sb.append(" ");
                    sb.append(h);
                }
                for (Integer h : second) {
                    sb.append(" ");
                    sb.append(h);
                }
                sb.append('\n');
                out.print(sb);
                out.flush();
                int ans = in.nextInt();
                if (ans == -1) {
                    return;
                }
                max = Math.max(ans, max);
            }
            out.println(String.format("-1 %d", max));
            out.flush();
        }
    }
}
