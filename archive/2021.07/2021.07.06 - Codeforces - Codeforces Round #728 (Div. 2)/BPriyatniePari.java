package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BPriyatniePari {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        List<Pair<Long, Long>> a = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            long val = in.nextLong();
            a.add(Pair.of(val, i * 1L));
        }
        a.sort(Comparator.comparingLong(x -> x.fs));
        long ans = 0;
        for (int i = 0; i < a.size(); i++) {
            long ai = a.get(i).fs;
            for (int h = i + 1; h < a.size(); h++) {
                long aj = a.get(h).fs;
                if (ai * aj > n * 2) {
                    break;
                }
                if (ai * aj == a.get(i).sc + a.get(h).sc) {
                    ans++;
                }
            }
        }
        out.println(ans);
    }
}
