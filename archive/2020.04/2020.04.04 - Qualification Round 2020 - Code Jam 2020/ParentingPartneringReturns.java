package task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParentingPartneringReturns {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();

        List<Item> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(new Item(in.nextInt(), in.nextInt(), i));
        }

        a.sort(Comparator.comparing(x -> x.from));
        int last1 = 0;
        int last2 = 0;
        char[] ans = new char[n];
        for (int i = 0; i < a.size(); i++) {
            int st = a.get(i).from;
            int end = a.get(i).to;
            if (last1 > st) {
                if (last2 > st) {
                    out.println(String.format("Case #%d: IMPOSSIBLE", testNumber));
                    return;
                }
                ans[a.get(i).idx] = 'J';
                last2 = end;
                continue;
            }
            ans[a.get(i).idx] = 'C';
            last1 = end;
        }
        out.println(String.format("Case #%d: %s", testNumber, new String(ans)));
    }
    public static class Item {
        int from, to;
        int idx;

        public Item(int from, int to, int idx) {
            this.from = from;
            this.to = to;
            this.idx = idx;
        }
    }
}
