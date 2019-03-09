package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CEnergyDrinkCollector {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        List<Pair<Long, Long>> stores = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long a = in.nextInt();
            long b = in.nextInt();
            stores.add(new Pair<>(a, b));
        }

        stores.sort(Comparator.comparingLong(a -> a.fs));

        long cur = 0;
        long cost = 0;
        for (int i = 0; i < n; i++) {
            if (cur >= m) {
                break;
            }

            long max = m - cur;
            long take = Math.min(stores.get(i).sc, max);
            cur += take;
            cost += take * stores.get(i).fs;
        }
        out.println(cost);
    }
}
