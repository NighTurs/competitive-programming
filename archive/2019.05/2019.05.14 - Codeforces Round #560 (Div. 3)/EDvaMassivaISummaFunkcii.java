package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EDvaMassivaISummaFunkcii {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        List<Pair<Integer, Long>> a = new ArrayList<>();
        List<Pair<Integer, Long>> b = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(new Pair<>(i, in.nextLong() * (i + 1) * (n - i)));
        }
        for (int i = 0; i < n; i++) {
            b.add(new Pair<>(i, in.nextLong()));
        }
        a.sort(Comparator.comparingLong(x -> x.sc));
        b.sort(Comparator.comparingLong(x -> -x.sc));
        long MOD = 998244353;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans = (ans + ((a.get(i).sc % MOD) * b.get(i).sc) % MOD) % MOD;
        }
        out.println(ans);
    }
}
